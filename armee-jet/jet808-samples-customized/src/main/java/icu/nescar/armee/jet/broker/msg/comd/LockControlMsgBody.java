package icu.nescar.armee.jet.broker.msg.comd;

import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.annotation.msg.resp.Jt808RespMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import static io.github.hylexus.jt.data.MsgDataType.BYTE;

/**
 * @Auther whale
 * @Date 2021/1/15
 * 锁控制命令下发
 */
@Data
@Accessors(chain = true)
@Jt808RespMsgBody(respMsgId = 0x8F01, desc = "锁控制命令下发")
public class LockControlMsgBody {
    private static final long serialVersionUID = -78329765887L;
    //车辆的身份信息
    @CommandField(order = 1, targetMsgDataType = BYTE)
    private String lockControl;

}

