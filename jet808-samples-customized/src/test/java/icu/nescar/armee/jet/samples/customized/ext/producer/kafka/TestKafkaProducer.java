package icu.nescar.armee.jet.samples.customized.ext.producer.kafka;

import icu.nescar.armee.jet.samples.customized.ext.conf.ConfArguments;
import icu.nescar.armee.jet.samples.customized.ext.producer.Producer;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg.KafkaMsgKey;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试Kafka
 * @author neyzoter
 */
public class TestKafkaProducer {
    @Before
    public void init() {
        System.out.println("TerminalInfoDao 开始测试-----------------");
    }
    @Test
    public void testKafkaProducer(){
        Producer<KafkaMsgKey, Object> impl = new KafkaProducerImpl(ConfArguments.KAFKA_TOPIC_DATA, false);
        KafkaMsgKey key = new KafkaMsgKey("client1", 0x8001);
        String msg = "Msg Sended!";
        impl.send(key, msg);
    }
    @After
    public void after() {
        System.out.println("TerminalInfoDao 测试结束-----------------");
    }
}
