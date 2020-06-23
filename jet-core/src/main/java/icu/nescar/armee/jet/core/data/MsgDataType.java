package icu.nescar.armee.jet.core.data;

import lombok.Getter;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

/**
 * 消息类型
 * @author Charles Song
 * @date 2020-6-18
 */
@Getter
//@SuppressWarnings("unchecked")
public enum MsgDataType {
    /**
     * 无符号单字节整型(字节，8 位)
     */
    BYTE(1, "无符号单字节整型(字节，8 位)", newHashSet(byte.class, Byte.class, int.class, Integer.class, Short.class, short.class)),
    /**
     * 字节数组
     */
    BYTES(0, "",  newHashSet(byte[].class)),
    /**
     * 无符号双字节整型(字，16 位)
     */
    WORD(2, "无符号双字节整型(字，16 位)", newHashSet(short.class, Short.class, int.class, Integer.class)),
    /**
     * 无符号四字节整型(双字，32 位)
     */
    DWORD(4, "无符号四字节整型(双字，32 位)", newHashSet(int.class, Integer.class)),
    /**
     * 8421 码，n 字节
     */
    BCD(0, "8421 码，n 字节", newHashSet(String.class)),
    /**
     * GBK 编码，若无数据，置空
     */
    STRING(0, "GBK 编码，若无数据，置空", newHashSet(String.class)),
    /**
     * List
     */
    LIST(0, "List", newHashSet(List.class)),
    /**
     * 未知类型，用于占位符或默认值
     */
    UNKNOWN(0, "未知类型，用于占位符或默认值", newHashSet(String.class)),
    ;

    /**
     * 字节数
     * 为零表示使用外部指定的长度
     */
    private final int byteCount;

    private final String desc;

    private final Set<Class<?>> expectedTargetClassType;

    MsgDataType(int byteCount, String desc, Set<Class<?>> expectedTargetClassType) {
        this.byteCount = byteCount;
        this.desc = desc;
        this.expectedTargetClassType = expectedTargetClassType;
    }

}