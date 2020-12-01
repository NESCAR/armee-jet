package icu.nescar.armee.jet.broker.ext.consumer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.consumer.Consumer;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试kafka消费者
 * @author neyzoter
 */
public class TestKafkaConsumerMain {
    public static void main(String[] args) {
        Consumer<ConsumerRecord<MsgKey, byte[]>> consumer = new KafkaConsumerImpl<>(ConfArguments.KAFKA_TOPIC_CMD);
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(consumer, 0, 8, TimeUnit.SECONDS);
    }
}
