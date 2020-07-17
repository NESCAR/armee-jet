package icu.nescar.armee.jet.broker.ext.conf;

import lombok.Getter;

/**
 * 生产者类型
 * @author neyzoter
 */
@Getter
public enum ProducerType {
    /**
     * 存储在MySQL
     */
    KAFKA("Kafka", "kafka");

    private final String desc;
    private final String name;

    ProducerType(String desc, String name) {
        this.desc = desc;
        this.name = name;
    }
}
