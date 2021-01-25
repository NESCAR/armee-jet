package icu.nescar.armee.jet.broker.msg.comd;

import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.annotation.msg.resp.Jt808RespMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static io.github.hylexus.jt.data.MsgDataType.*;

/**
 * @author whale
 * Created At 2020-12-10 10:43 下午
 * 设置授权信息的下发消息体
 */
@Data
@Accessors(chain = true)
//@Jt808RespMsgBody(respMsgId = 0x8F00, desc = "授权信息下发")
public class AuthInfoSettingsMsgBody implements Serializable {
    private static final long serialVersionUID = -7832986449797L;
    //车辆的身份信息
//    @CommandField(order = 1, targetMsgDataType = BYTES)
    private String driverID;


//    //终端号信息 在msgkey里面有终端信息 所以不多余设置了
//    @CommandField(order = 2, targetMsgDataType = BYTES)
//    private String TerminalID;

    //上锁时间起点 服务端设置的上锁时间范围 YY-MM-DD-hh-mm-ss GMT+8时间
//    @CommandField(order = 2, targetMsgDataType =BCD)
    private byte[] lockTimeStart;

    //上锁时间终点 YY-MM-DD-hh-mm-ss GMT+8时间
//    @CommandField(order = 3, targetMsgDataType = BCD)
    private byte[] lockTimeEnd;


}
