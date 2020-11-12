package icu.nescar.armee.jet.broker.config;

import io.github.hylexus.jt.data.msg.MsgType;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-09-22 3:37 下午
 */
@Getter
@ToString
public enum Jt808MsgType implements MsgType {
    //上报第一位为0
    CLIENT_COMMON_REPLY(0x0001, "终端通用应答"),
    CLIENT_AUTH(0x0102, "终端鉴权"),
    CLIENT_LOCATION_INFO_UPLOAD(0x0200, "位置上报"),
    CLIENT_EVENT_UPLOAD(0x0301, "事件报告"),
    CLIENT_QUERY_TERMINAL_PROPERTIES_REPLY(0x0107, "查询终端属性应答"),
    CLIENT_AXLE_LOAD_INFO_UPLOAD(0x0109,"轴负载信息上报"),
    CLIENT_TEBS_STATUS_INFO_UPLOAD(0x0110,"TEBS状态上报"),
    CLIENT_RSSEVENT_INFO_UPLOAD(0x0111,"侧倾稳定性支持信息上报"),
    CLIENT_MILEAGE_INFO_UPLOAD(0x0112, "里程信息上报"),
    CLIENT_BRAKE_INFO_UPLOAD(0x0113,"急刹车事件上报"),
    CLIENT_TEBS_ACCEPT_REPLY(0x0114, "TEBS接收应答"),
    CLIENT_UNLOCK_INFO_UPLOAD(0x0115,"解锁状态上报（人为解锁时）"),
    CLIENT_LOCK_INFO_UPLOAD(0x0116,"上锁状态上报"),
    CLIENT_ALARM_INFO_UPLOAD(0x0117,"报警信息上报"),
    CLIENT_BINDING_INFO_UPLOAD(0x0118,"绑定信息上报"),

    //下发 id第一位均为8
    RESP_TERMINAL_PARAM_SETTINGS(0x8103, "设置终端参数"),
    RESP_QUERY_TERMINAL_PROPERTIES(0x8107, "查询终端属性(EMPTY)"),
    RESP_LOCK_INFO_SETTINGS(0x8114,"上锁信息下发（设置）"),


    ;
    private final int msgId;
    private final String desc;

    Jt808MsgType(int msgId, String desc) {
        this.msgId = msgId;
        this.desc = desc;//description描述简写
    }

    private static final Map<Integer, Jt808MsgType> mapping = new HashMap<>(values().length);

    static {
        for (Jt808MsgType type : values()) {
            mapping.put(type.msgId, type);
        }
    }

    @Override
    public Optional<MsgType> parseFromInt(int msgId) {
        return Optional.ofNullable(mapping.get(msgId));
    }
    //指定msgId返回mapping中存储的对应id对象，如果是null的话返回空值
}
