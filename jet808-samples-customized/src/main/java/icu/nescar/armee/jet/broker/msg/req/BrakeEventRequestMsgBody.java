package icu.nescar.armee.jet.broker.msg.req;


import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/8/25
 * 急刹车事件上报 刹车信号不明确以什么形式传输
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0113)
public class BrakeEventRequestMsgBody implements RequestMsgBody {
    //to do暂定用一个字节值来表示急刹车事件
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte brakeSignal;

    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

