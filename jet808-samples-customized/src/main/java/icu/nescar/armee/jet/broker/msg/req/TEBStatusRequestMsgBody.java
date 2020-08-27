package icu.nescar.armee.jet.broker.msg.req;

import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.annotation.msg.req.Jt808ReqMsgBody;
import icu.nescar.armee.jet.annotation.msg.req.basic.BasicField;
import icu.nescar.armee.jet.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/8/25
 * TEBS状态上报的消息体
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0110)

public class TEBStatusRequestMsgBody implements RequestMsgBody {

    //tebs状态位
    @BasicField(startIndex = 0,dataType = MsgDataType.WORD,length = 2)
    private int tebStatus;

    //时间 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 2,dataType = MsgDataType.BCD,length = 6)
    private String time;

}

