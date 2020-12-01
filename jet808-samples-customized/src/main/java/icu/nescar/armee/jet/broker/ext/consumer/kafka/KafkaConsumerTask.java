package icu.nescar.armee.jet.broker.ext.consumer.kafka;

import icu.nescar.armee.jet.broker.ext.consumer.Consumer;
import icu.nescar.armee.jet.broker.ext.producer.MsgKey;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Auther whale
 * @Date 2020/11/26
 * 利用周期性执行任务的线程池执行consumer的任务
 */
//@Component
public class KafkaConsumerTask {
    Consumer<ConsumerRecord<MsgKey, byte[]>> consumer;
    ScheduledExecutorService ses;
    @Autowired
    public KafkaConsumerTask(JetConsumerImpl jetConsumerImpl) {
        consumer = jetConsumerImpl;
        ses = Executors.newSingleThreadScheduledExecutor();
        ses.scheduleAtFixedRate(jetConsumerImpl,0,8,TimeUnit.SECONDS);
    }
}

