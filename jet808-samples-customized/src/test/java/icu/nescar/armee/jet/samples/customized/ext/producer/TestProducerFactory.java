package icu.nescar.armee.jet.samples.customized.ext.producer;

import icu.nescar.armee.jet.samples.customized.ext.conf.ConfArguments;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg.KafkaMsgKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试ProducerFactory
 * @author neyzoter
 */
public class TestProducerFactory {
    @Before
    public void init() {
        System.out.println("KafkaProducer 开始测试-----------------");
    }
    @Test
    public void testProducerFactory(){
        Producer<MsgKey, Object> producer = ProducerFactory.createVmSetedProducer(ConfArguments.KAFKA_TOPIC_DATA, false);
        KafkaMsgKey key = new KafkaMsgKey("client1", 0x8001);
        String msg = "Msg Sended!";
        producer.send(key, msg);
    }
    @After
    public void after() {
        System.out.println("KafkaProducer 测试结束-----------------");
    }
}
