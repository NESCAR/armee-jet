package icu.nescar.armee.jet.samples.customized.ext.producer.kafka.msg;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Kafka消息的key
 * @author neyzoter
 */
@Getter
@Setter
@ToString
public class KafkaMsgKey implements Serializable {
    private static final long serialVersionUID = 8445397571181027199L;
    private String terminalId;
    private int msgId;
}
