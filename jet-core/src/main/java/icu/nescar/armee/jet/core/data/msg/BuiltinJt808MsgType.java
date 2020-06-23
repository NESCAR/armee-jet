package icu.nescar.armee.jet.core.data.msg;

import icu.nescar.armee.jet.core.utils.HexStringUtils;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 固有的JT808消息类型
 * @author Charles Song
 * @date 2020-6-18
 **/
@Getter
public enum BuiltinJt808MsgType implements MsgType {
    /**
     * 终端通用应答
     */
    CLIENT_COMMON_REPLY(0x0001, "终端通用应答"),
    /**
     * 终端心跳
     */
    CLIENT_HEART_BEAT(0x0002, "终端心跳"),
    /**
     * 终端注册
     */
    CLIENT_LOG_OUT(0x0003, "终端注销"),
    /**
     * 平台通用应答
     */
    SERVER_COMMON_REPLY(0x8001, "平台通用应答"),
    /**
     * 终端注册
     */
    CLIENT_REGISTER(0x0100, "终端注册"),
    /**
     * 平台注册应答
     */
    CLIENT_REGISTER_REPLY(0x8100, "平台注册应答"),
    /**
     * 终端鉴权
     */
    CLIENT_AUTH(0x0102, "终端鉴权"),
    /**
     * 位置信息汇报
     */
    CLIENT_LOCATION_INFO_UPLOAD(0x0200, "位置信息汇报"),
    ;

    BuiltinJt808MsgType(int msgId, String desc) {
        this.msgId = msgId;
        this.desc = desc;
    }

    private final int msgId;
    private final String desc;

    private static final Map<Integer, BuiltinJt808MsgType> MAPPING = new HashMap<>(BuiltinJt808MsgType.values().length);

    static {
        for (BuiltinJt808MsgType builtinMsgType : values()) {
            MAPPING.put(builtinMsgType.msgId, builtinMsgType);
        }
    }

    @Override
    public Optional<MsgType> parseFromInt(int msgId) {
        return Optional.ofNullable(MAPPING.get(msgId));
    }

    @Override
    public String toString() {
        return "BuiltInMsgType{"
                + "msgId=" + msgId
                + "(" + HexStringUtils.int2HexString(msgId, 4, true) + "), desc='" + desc + '\''
                + '}';
    }
}
