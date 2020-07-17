package icu.nescar.armee.jet.samples.customized.ext.producer;

import icu.nescar.armee.jet.samples.customized.ext.conf.ProducerType;
import icu.nescar.armee.jet.samples.customized.ext.conf.VmOptions;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.KafkaProducerImpl;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg.KafkaMsgKey;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者工厂
 * @author neyzoter
 */
public class ProducerFactory {
    /**
     * 创建生产者
     * @param producer 生产者，比如kafka
     * @param topic 主题
     * @param isAsync 是否异步
     * @return 生产者
     */
    public static Producer<MsgKey, Object> createProducer(String producer, String topic, boolean isAsync) {
        ProducerType type = VmOptions.findProducerSupported(producer);
        if (type == null) {
            return null;
        }
        switch (type) {
            case KAFKA:
                return new KafkaProducerImpl<>(topic, isAsync);
            default:
                return null;
        }
    }

    /**
     * 创建VM参数设置的生产者
     * @param topic 主题
     * @param isAsync 是否异步
     * @return 生产者
     */
    public static Producer<MsgKey, Object> createVmSetedProducer(String topic, boolean isAsync) {
        return createProducer(System.getProperty(VmOptions.PRODUCE_TO), topic, isAsync);
    }
}
