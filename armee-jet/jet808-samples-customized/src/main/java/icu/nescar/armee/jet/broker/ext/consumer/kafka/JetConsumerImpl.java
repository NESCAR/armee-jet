package icu.nescar.armee.jet.broker.ext.consumer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.conf.VmOptions;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import icu.nescar.armee.jet.broker.msg.comd.AuthInfoSettingsMsgBody;
import icu.nescar.armee.jet.broker.msg.comd.TerminalSettingsMsgBody;
import icu.nescar.armee.jet.broker.util.SerializationUtil;
import io.github.hylexus.jt808.dispatcher.CommandSender;
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
import java.util.Optional;
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
    public Long timeout;

    @Autowired
    private CommandSender commandSender;

    @Autowired
    private Jt808SessionManager sessionManager;



    @Override
    public void run(){
        while (true) {
            long startMs = System.currentTimeMillis() / 1000;
            //log.info("start : " + startMs);
            //TODO 当终端存在连接时，能够下发成功。但目前终端无法返回值，所以resp是null
            //当终端没有连接时，程序卡死，因为找不到对应session，即不知道往哪里发。

            ConsumerRecords<MsgKey, byte[]> records = (ConsumerRecords<MsgKey, byte[]>) receive(Duration.ofSeconds(1));
            timeout=VmOptions.TIME_OUT;

            for (ConsumerRecord<MsgKey, byte[]> record:records){
                String terminalId=record.key().getTerminalId();
                if(sessionManager.findByTerminalId(terminalId).isPresent()){
                    if(record.key().getMsgId()==0x8F00){//msgid是授权消息下发
                    AuthInfoSettingsMsgBody lockInfo = (AuthInfoSettingsMsgBody) SerializationUtil.deserialize(record.value());//设置具体的下发信息内容
                    CommandMsg commandMsg = CommandMsg.of(terminalId, CLIENT_COMMON_REPLY, lockInfo);
                    log.info("收到平台的授权消息"+commandMsg.toString()+"body"+commandMsg.getBody());
                    final Object resp;

                    try {

                        resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
                        for(int maxTry=2;maxTry>0;maxTry--){
                            if(resp!=null){
                              log.info("下发授权消息成功，并收到回复resp: {}", resp);
                          }
                          else{
                              commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
                              log.info("下发授权信息失败，并重新下发一次");
                          }
                    }
                        log.info("下发授权信息失败");


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }

                    if(record.key().getMsgId()==0x8103){
                        // 【下发消息】的消息类型为: RESP_TERMINAL_PARAM_SETTINGS (0x8103)  --> RespTerminalSettings的类注解上指定了下发类型
                        // 客户端对该【下发消息】的回复消息类型为: CLIENT_COMMON_REPLY (0x0001)
                        TerminalSettingsMsgBody param = (TerminalSettingsMsgBody)SerializationUtil.deserialize(record.value());

                        //具体的param设置
                        CommandMsg commandMsg = CommandMsg.of(terminalId, CLIENT_COMMON_REPLY, param);
                        log.info("收到终端设置消息"+commandMsg.toString());
                        final Object resp;
                        try {
                            resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
                            log.info("下发终端设置消息成功，并收到回复resp: {}", resp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                }
                else {
                    log.info("收到平台下发信息，但无法发送，由于该终端:{},未连接",terminalId);
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

