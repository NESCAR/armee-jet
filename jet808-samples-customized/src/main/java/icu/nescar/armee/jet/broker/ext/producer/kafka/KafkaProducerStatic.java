package icu.nescar.armee.jet.broker.ext.producer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.consumer.kafka.KafkaConsumerImpl;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;

public class KafkaProducerStatic {
    protected static volatile Producer<KafkaMsgKey, Object> producer;
    public static Producer<KafkaMsgKey, Object> getInstance() {
        if (producer == null) {
            synchronized (KafkaProducerStatic.class) {
                if(producer == null) {
                    producer = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
                }
            }
        }
        return producer;
    }
}
