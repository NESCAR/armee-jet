package icu.nescar.armee.jet.broker.msg.req;


import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther whale
 * @Date 2020/8/25
 * 急刹车事件上报 刹车信号不明确以什么形式传输
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0F04)
public class BrakeEventRequestMsgBody implements RequestMsgBody, Serializable {

    private static final long serialVersionUID = 1128780475774199113L;
    //TODO 暂定用一个字节值来表示急刹车事件
    @BasicField(startIndex = 0, dataType = MsgDataType.BYTE, length = 1)
    private byte brakeSignal;

    //事件发生时的时间
    @BasicField(startIndex = 1, dataType = MsgDataType.BYTES, length = 6)
    private byte[] brakeTime;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

