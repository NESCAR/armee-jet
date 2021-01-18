package icu.nescar.armee.jet.broker.ext.producer.kafka;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.util.TimeConverter;

/**
 * Main函数发送Kafka消息
 * @author neyzoter
 */
public class TestKafkaProducerMain {
    public static void main(String[] args){
        Producer<KafkaMsgKey, Object> implSync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
        Producer<KafkaMsgKey, Object> implAsync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, true);
        int step = 0;
        while (true) {
            try {
                Thread.sleep(5000);
                KafkaMsgKey key = new KafkaMsgKey("client1", Jt808MsgType.CLIENT_ALARM_INFO_UPLOAD.getMsgId());
                AlarmUploadRequestMsgBody msg = new AlarmUploadRequestMsgBody();
                msg.setAlarmStatus((byte) (1 << step));
                msg.setAlarmTime(TimeConverter.longToRFC3339(System.currentTimeMillis()));
                // 异步测试
                implAsync.send(key, msg);
                // 同步测试
//                implSync.send(key, msg);
                step = (step + 1) % 8;//啥意思？这个取余操作是用来干什么 step0表示未按规定停车
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
