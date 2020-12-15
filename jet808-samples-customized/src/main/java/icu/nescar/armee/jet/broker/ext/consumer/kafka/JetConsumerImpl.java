package icu.nescar.armee.jet.broker.ext.consumer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.conf.VmOptions;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import icu.nescar.armee.jet.broker.msg.command.LockInfoSettingsMsgBody;
import icu.nescar.armee.jet.broker.msg.resp.RespTerminalSettings;
import icu.nescar.armee.jet.broker.util.SerializationUtil;
import io.github.hylexus.jt808.codec.Encoder;
import io.github.hylexus.jt808.converter.impl.resp.CommandMsgBodyConverter;
import io.github.hylexus.jt808.converter.impl.resp.VoidRespMsgBodyConverter;
import io.github.hylexus.jt808.dispatcher.CommandSender;
import io.github.hylexus.jt808.dispatcher.impl.DefaultCommandSender;
import io.github.hylexus.jt808.msg.resp.CommandMsg;
import io.github.hylexus.jt808.session.Jt808SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static icu.nescar.armee.jet.broker.config.Jt808MsgType.CLIENT_COMMON_REPLY;

/**
 * @Auther whale
 * @Date 2020/11/26
 * jet协议的kafka消费者实例
 * 重写run函数 在里面进行数据的下发
 */
@Slf4j
@Component
public class JetConsumerImpl extends KafkaConsumerImpl<ConsumerRecord<MsgKey, byte[]>> {

    /**
     * Kafka消费者实例
     *
     * @param t topic
     */
    public JetConsumerImpl(String t) {
        super(t);
    }
    public JetConsumerImpl() {
        super(ConfArguments.KAFKA_TOPIC_CMD);
    }
    private CommandSender commandSender;
    public Long timeout;
    private CommandMsgBodyConverter commandMsgBodyConverter;

    @Autowired
    private Encoder encoder;
    @Autowired
    private Jt808SessionManager sessionManager;



    @Override
    public void run(){
        while (true) {
            long startMs = System.currentTimeMillis() / 1000;
            //log.info("start : " + startMs);
            //TODO 消费者消费到的信息测试下发是否可以成功
            commandSender=new DefaultCommandSender(commandMsgBodyConverter,encoder,sessionManager);

            ConsumerRecords<MsgKey, byte[]> records = (ConsumerRecords<MsgKey, byte[]>) receive(Duration.ofSeconds(1));
            timeout=VmOptions.TIME_OUT;

            for (ConsumerRecord<MsgKey, byte[]> record:records){
                String terminalId=record.key().getTerminalId();

                if(record.key().getMsgId()==0x8114){//msgid是上锁消息下发
                    LockInfoSettingsMsgBody lockInfo = (LockInfoSettingsMsgBody) SerializationUtil.deserialize(record.value());//设置具体的下发信息内容
                    CommandMsg commandMsg = CommandMsg.of(terminalId, CLIENT_COMMON_REPLY, lockInfo);
                    log.info("收到上锁消息"+commandMsg.toString());
                    final Object resp;
                    try {
                        resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
                        log.info("resp: {}", resp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

                if(record.key().getMsgId()==0x8103){
                    // 【下发消息】的消息类型为: RESP_TERMINAL_PARAM_SETTINGS (0x8103)  --> RespTerminalSettings的类注解上指定了下发类型
                    // 客户端对该【下发消息】的回复消息类型为: CLIENT_COMMON_REPLY (0x0001)
                    RespTerminalSettings param = (RespTerminalSettings)SerializationUtil.deserialize(record.value());

                    //具体的param设置
                    CommandMsg commandMsg = CommandMsg.of(terminalId, CLIENT_COMMON_REPLY, param);
                    log.info("收到终端设置消息"+commandMsg.toString());
                    final Object resp;
                    try {
                        resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
                        log.info("resp: {}", resp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

            }
            /**
             * 手动控制offset
             * 当宕机后，等待consumer重启，则通过seek(TopicPartition, long)来恢复到之前的offset
             */
            // 分别对每个partition进行操作
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<MsgKey, byte[]>> partitionRecords = records.records(partition);
                for (ConsumerRecord<MsgKey, byte[]> record : partitionRecords) {
                    System.out.println("[ " + broker + " ] Received message: (" + record.key() + ", " + Arrays.toString(record.value()) + ") at offset " + record.offset() + " from partition " + record.partition() +
                            " in " + (int) (System.currentTimeMillis() / 1000 - startMs) + " ms");
                }
                // 获取最近的offset
                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                // 提交offset
                try {
                    System.out.println("Start sending offset [ " + lastOffset + " ]");
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)), Duration.ofSeconds(2));
                    System.out.println("Sent offset successfully");
                } catch (TimeoutException te) {
                    te.printStackTrace();
                }
            }

        }


    }
}

