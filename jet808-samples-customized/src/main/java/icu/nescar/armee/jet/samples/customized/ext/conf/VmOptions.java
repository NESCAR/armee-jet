package icu.nescar.armee.jet.samples.customized.ext.conf;

/**
 * 配置中心
 * <pre>
 *     距离
 *     auth.by=mysql
 *     mysql.url=mysql://mysql:3306/test
 *     jdbc.driver=com.mysql.cj.jdbc.Driver
 *     mysql.user=root
 *     mysql.psw=Root123456@
 *     mysql.auth.table=client
 *     快速使用
 *     -Dauth.by=mysql -Dmysql.url=mysql://mysql:3306/test -Djdbc.driver=com.mysql.cj.jdbc.Driver -Dmysql.user=root -Dmysql.psw=Root123456@ -Dmysql.auth.table=client
 * </pre>
 * @author neyzoter
 */
public class VmOptions {
    /**
     * 采用哪种存储方式来验证设备信息
     */
    public static final String AUTH_BY = "auth.by";
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
    /**
     * 终端ID在TABLE中的字段名称
     */
    public static final String MYSQL_AUTH_TABLE_SEGMENT_TERMINAL_ID = "terminal_id";
    /**
     * 终端在TABLE中的密码名称
     */
    public static final String MYSQL_AUTH_TABLE_SEGMENT_PASSWORD = "password";

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
}
