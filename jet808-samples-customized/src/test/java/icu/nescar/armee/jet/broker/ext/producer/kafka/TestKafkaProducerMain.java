package icu.nescar.armee.jet.broker.ext.producer.kafka;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;

/**
 * Main函数发送Kafka消息
 * @author neyzoter
 */
public class TestKafkaProducerMain {
    public static void main(String[] args){
        Producer<KafkaMsgKey, Object> implSync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
        Producer<KafkaMsgKey, Object> implAsync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, true);
        while (true) {
            try {
                Thread.sleep(5000);
                KafkaMsgKey key = new KafkaMsgKey("client1", Jt808MsgType.CLIENT_ALARM_INFO_UPLOAD.getMsgId());
                AlarmUploadRequestMsgBody msg = new AlarmUploadRequestMsgBody();
                msg.setAlarmStatus((byte)1);
                msg.setTime("2019-08-09T12:03:02Z");
                implAsync.send(key, msg);
//                implSync.send(key, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
