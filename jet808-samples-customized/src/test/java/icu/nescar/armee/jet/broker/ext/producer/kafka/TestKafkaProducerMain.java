package icu.nescar.armee.jet.broker.ext.producer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;

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
                Thread.sleep(1000);
                KafkaMsgKey key = new KafkaMsgKey("client1", 0x8001);
                String msg = "Msg Sended!";
                implAsync.send(key, msg);
                implSync.send(key, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
