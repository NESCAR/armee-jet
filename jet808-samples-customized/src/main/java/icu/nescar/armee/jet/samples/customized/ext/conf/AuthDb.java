package icu.nescar.armee.jet.samples.customized.ext.conf;

import lombok.Getter;

/**
 * 支持的数据库类型
 * @author neyzoter
 */
@Getter
public enum AuthDb {
    MYSQL("MySQL", "mysql");

    private final String desc;
    private final String name;

    AuthDb(String desc, String name) {
        this.desc = desc;
        this.name = name;
    }
}
