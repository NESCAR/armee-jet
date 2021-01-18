package icu.nescar.armee.jet.broker.ext.producer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;

/**
 * @Auther whale
 * @Date 2020/12/29
 */
public class KafkaProducerStatic {
    protected static volatile Producer<KafkaMsgKey, Object> producer;
    public static Producer<KafkaMsgKey, Object> getDataInstance() {
        if (producer == null) {
            synchronized (KafkaProducerStatic.class) {
                if(producer == null) {
                    producer = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
                }
            }
        }
        return producer;
    }

    public static Producer<KafkaMsgKey, Object> getDeviceInstance() {
        if (producer == null) {
            synchronized (KafkaProducerStatic.class) {
                if(producer == null) {
                    producer = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DEVICE, false);
                }
            }
        }
        return producer;
    }
}

