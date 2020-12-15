package icu.nescar.armee.jet.broker.msg.resp;

import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.annotation.msg.resp.Jt808RespMsgBody;
import io.github.hylexus.jt.data.resp.BytesValueWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

import static io.github.hylexus.jt.data.MsgDataType.*;
import static io.github.hylexus.jt.data.MsgDataType.BCD;

/**
 * @author whale
 * Created At 2020-12-10 10:43 下午
 * 设置上锁消息的下发消息体
 */
@Data
@Accessors(chain = true)
@Jt808RespMsgBody(respMsgId = 0x8114, desc = "上锁信息下发")
public class LockInfoSettings implements Serializable {
    private static final long serialVersionUID = -7832986449797L;
    //车辆的身份信息
    @CommandField(order = 1, targetMsgDataType = STRING)
    private String carID;


    //即ic卡信息 终端通过ic卡刷卡解锁
    @CommandField(order = 2, targetMsgDataType = STRING)
    private String ICID;

    //上锁时间起点 服务端设置的上锁时间范围 YY-MM-DD-hh-mm-ss GMT+8时间
    @CommandField(order = 3, targetMsgDataType = STRING)
    private String lockTimeStart;

    //上锁时间终点 YY-MM-DD-hh-mm-ss GMT+8时间
    @CommandField(order = 4, targetMsgDataType = STRING)
    private String lockTimeEnd;


}
