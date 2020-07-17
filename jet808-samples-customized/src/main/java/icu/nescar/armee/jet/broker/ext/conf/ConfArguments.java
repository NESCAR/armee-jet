package icu.nescar.armee.jet.broker.ext.conf;

/**
 * 配置参数
 * @author neyzoter
 */
public class ConfArguments {
    /**
     * 终端ID在TABLE中的字段名称
     */
    public static final String MYSQL_AUTH_TABLE_SEGMENT_TERMINAL_ID = "terminal_id";
    /**
     * 终端在TABLE中的密码名称
     */
    public static final String MYSQL_AUTH_TABLE_SEGMENT_PASSWORD = "password";
    /**
     * Kafka主题：终端信息<br>
     * Jt808生产到Kafka，由业务消费
     */
    public static final String KAFKA_TOPIC_CLIENT_INFO = "TerminalInfo";
    /**
     * Kafka主题：数据<br>
     * Jt808生产到Kafka，由业务消费
     */
    public static final String KAFKA_TOPIC_DATA = "Data";
    /**
     * Kafka主题：命令<br>
     * Jt808消费，并发送给对应到终端；业务生产到Kafka
     */
    public static final String KAFKA_TOPIC_CMD = "Command";
}
