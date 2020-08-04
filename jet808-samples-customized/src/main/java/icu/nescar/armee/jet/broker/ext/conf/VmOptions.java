package icu.nescar.armee.jet.broker.ext.conf;

/**
 * 配置中心
 * <pre>
 *     举例
 *     auth.by=mysql
 *     mysql.url=mysql://mysql:3306/test
 *     jdbc.driver=com.mysql.cj.jdbc.Driver
 *     mysql.user=root
 *     mysql.psw=Root123456@
 *     mysql.auth.table=client
 *     validator.pool.core-num=10
 *     produce.to=kafka
 *     kafka.producer.server.url=23.11.11.11 或者 kafka
 *     kafka.producer.server.port=9092
 *     consume.from=kafka
 *     kafka.consumer.server.url=23.11.11.11 或者 kafka
 *     kafka.consumer.server.port=9092
 *
 *
 *     快速使用
 *     -Dbroker.id=1
 *     -Dauth.by=mysql -Dmysql.url=mysql://mysql:3306/test -Djdbc.driver=com.mysql.cj.jdbc.Driver -Dmysql.user=root -Dmysql.psw=Root123456@ -Dmysql.auth.table=client -Dvalidator.pool.core-num=10
 *     -Dproduce.to=kafka -Dkafka.producer.server.url=kafka-1 -Dkafka.producer.server.port=9092
 *     -Dconsume.from=kafka -Dkafka.consumer.server.url=kafka-1 -Dkafka.consumer.server.port=9092
 * </pre>
 * @author neyzoter
 */
public class VmOptions {
    /**
     * 节点的ID，可以是任意字符串。需要保证每个节点唯一。
     */
    public static final String BROKER_ID = "broker.id";

    // 以下是设备验证方式的配置，比如MySQL
    /**
     * 采用哪种存储方式来验证设备信息
     */
    public static final String AUTH_BY = "auth.by";
    /**
     * 验证器的核心连接数
     */
    public static final String AUTH_VALIDATOR_POOL_CORE_NUM = "validator.pool.core-num";
    /**
     * 目前支持的设备信息存储方式
     */
    public static final AuthDb[] AUTH_BY_SUPPORT = {AuthDb.MYSQL};

    /**
     * MySQL地址<br>
     * url参考：mysql://[HOST_NAME:mysql]:[PORT:3306]/[DB:info]
     */
    public static final String MYSQL_URL = "mysql.url";
    /**
     * 默认的URL参数
     */
    public static final String MYSQL_URL_DEFAULT_PARAMS = "useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&allowMultiQueries=true&useSSL=false&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai";
    /**
     * MySQL驱动
     */
    public static final String JDBC_DRIVER = "jdbc.driver";
    /**
     * 支持的JDBC驱动
     */
    public static final String[] JDBC_DRIVER_SUPPORT = {"com.mysql.cj.jdbc.Driver", "com.mysql.jdbc.Driver"};
    /**
     * MySQL用户名
     */
    public static final String MYSQL_USER = "mysql.user";
    /**
     * MySQL用户密码
     */
    public static final String MYSQL_PSW = "mysql.psw";
    /**
     * auth信息存放的表名称
     */
    public static final String MYSQL_AUTH_TABLE = "mysql.auth.table";

    // 以下是消息生产对象的配置，比如配置成Kafka
    /**
     * 生产方向，比如kafka
     */
    public static final String PRODUCE_TO = "produce.to";
    /**
     * 支持的生产者类型
     */
    public static final MsgQueueType[] MSG_QUEUE_SUPPORT = {MsgQueueType.KAFKA};
    /**
     * Kafka服务器生产地址
     */
    public static final String KAFKA_PRODUCER_SERVER_URL = "kafka.producer.server.url";
    /**
     * Kakfa服务器生产端口
     */
    public static final String KAFKA_PRODUCER_SERVER_PORT = "kafka.producer.server.port";

    // 以下是消息消费者的配置，比如配置成Kafka
    /**
     * 支持的消费者类型
     */
    public static final String CONSUME_FROM = "consume.from";
    /**
     * Kafka服务器消费地址
     */
    public static final String KAFKA_CONSUMER_SERVER_URL = "kafka.consumer.server.url";
    /**
     * Kafka服务器消费端口
     */
    public static final String KAFKA_CONSUMER_SERVER_PORT = "kafka.consumer.server.port";


    /**
     * 查询是否支持该DB
     * @param db 数据库类型
     * @return 是否支持
     */
    public static boolean ifDbSupported(String db) {
        boolean supported = false;
        for (AuthDb authDb : AUTH_BY_SUPPORT) {
            String name = authDb.getName();
            if (name.toLowerCase().equals(db.toLowerCase())) {
                supported = true;
            }
        }
        return supported;
    }

    /**
     * 查询支持的DB
     * @return {@link AuthDb}
     */
    public static AuthDb findDbSupported(String db){
        if (db == null) {
            return null;
        }
        for (AuthDb authDb : AUTH_BY_SUPPORT) {
            String name = authDb.getName();
            if (name.toLowerCase().equals(db.toLowerCase())) {
                return authDb;
            }
        }
        return null;
    }
    /**
     * 查询支持的消息队列类型
     * @param mqtp 消息队列类型
     * @return {@link MsgQueueType}
     */
    public static MsgQueueType findMsgQueueSupported(String mqtp){
        if (mqtp == null) {
            return null;
        }
        for (MsgQueueType type : MSG_QUEUE_SUPPORT) {
            String name = type.getName();
            if (name.toLowerCase().equals(mqtp.toLowerCase())) {
                return type;
            }
        }
        return null;
    }
}
