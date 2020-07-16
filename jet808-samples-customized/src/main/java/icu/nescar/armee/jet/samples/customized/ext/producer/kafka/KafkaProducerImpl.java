package icu.nescar.armee.jet.samples.customized.ext.producer.kafka;

import icu.nescar.armee.jet.samples.customized.ext.conf.VmOptions;
import icu.nescar.armee.jet.samples.customized.ext.producer.Producer;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg.KafkaMsgKeySerializer;
import icu.nescar.armee.jet.samples.customized.util.SerializationUtil;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.ByteArraySerializer;

import java.util.Properties;

/**
 * Kafka生产者
 * @author neyzoter
 */
public class KafkaProducerImpl implements Producer<KafkaMsgKey, Object> {
    /**
     * producer线程安全
     */
    private final KafkaProducer<KafkaMsgKey, Object> producer;
    /**
     * 主题
     */
    private final String topic;
    /**
     * 同步或者异步
     */
    private final Boolean isAsync;

    /**
     * Kafka生产者实例
     * @param topic 主题
     * @param isAsync 是否同步，TODO
     */
    public KafkaProducerImpl(String topic, Boolean isAsync) {
        // TODO 将Kakfka的参数设计为可配置
        String url = System.getProperty(VmOptions.KAFKA_SERVER_URL);
        String port = System.getProperty(VmOptions.KAFKA_SERVER_PORT);
        String broker = System.getProperty(VmOptions.BROKER_ID);
        if (url == null || port == null || broker == null) {
            System.err.println("Kafka Error : No Url or Port or BrokerId");
        }
        Properties props = new Properties();
        // 判别请求是否为完整的条件，是否成功发送
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        // 如果请求失败，生产者自动重试，这里设置重试次数为0
        props.put(ProducerConfig.RETRIES_CONFIG, 0);
        // 设置分区未发送的消息个数，每个分区都会对应一个缓存区来存储这些消息
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 1000);
        // 生产者在发送请求前等待一段时间，希望更多的消息填补到未满得到批中
        // 此处设置500ms发送一次，则在数据存储到缓存区后，会等待500ms
        // 不过也不绝对，如果缓存区中已经有其他的消息，可能跟着其他的消息一块发出去了
        props.put(ProducerConfig.LINGER_MS_CONFIG, 500);
        // 生产者可用的缓存总量，如果消息发送速度比其传输到服务器的快，
        // 将会耗尽这个缓存空间。当缓存空间耗尽，其他发送调用将被阻塞，
        // 阻塞时间的阈值通过max.block.ms设定，之后它将抛出一个TimeoutException。
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 2048000);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, url + ":" + port);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, broker);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaMsgKeySerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "icu.nescar.armee.jet.samples.customized.ext.producer.kafka.partitioner.AllocationPartitioner");
        producer = new KafkaProducer<>(props);

        this.topic = topic;
        this.isAsync = isAsync;
    }
    @Override
    public void send(KafkaMsgKey key, Object value) {
        try {
            byte[] v = SerializationUtil.serialize(value);
            ProducerRecord<KafkaMsgKey, Object> record = new ProducerRecord<>(topic, key, v);
            producer.send(record, new DemoCallBack(System.currentTimeMillis(), key, value));
//            System.out.println("send Key : " + key.toString() + " Value : " + value.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class DemoCallBack implements Callback {

    private final long startTime;
    private final KafkaMsgKey key;
    private final Object message;

    public DemoCallBack(long startTime, KafkaMsgKey key, Object message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
    }

    /**
     * A callback method the user can implement to provide asynchronous handling of request completion. This method will
     * be called when the record sent to the server has been acknowledged. When exception is not null in the callback,
     * metadata will contain the special -1 value for all fields except for topicPartition, which will be valid.
     *
     * @param metadata  The metadata for the record that was sent (i.e. the partition and offset). Null if an error
     *                  occurred.
     * @param exception The exception thrown during processing of this record. Null if no error occurred.
     */
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                    "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                            "), " +
                            "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
        }
    }
}

