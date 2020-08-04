package icu.nescar.armee.jet.broker.ext.consumer.kafka;

import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.consumer.Consumer;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerImpl;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试kafka消费者
 * @author neyzoter
 */
public class TestKafkaConsumer {
    @Before
    public void init() {
        System.out.println("KafkaConsumer 开始测试-----------------");
    }
    @Test
    public void testKafkaConsumer(){
        Consumer<ConsumerRecord<MsgKey, byte[]>> consumer = new KafkaConsumerImpl<>(ConfArguments.KAFKA_TOPIC_DATA);
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ses.schedule(consumer, 5, TimeUnit.SECONDS);
    }
    @After
    public void after() {
        System.out.println("KafkaConsumer 测试结束-----------------");
    }
}
