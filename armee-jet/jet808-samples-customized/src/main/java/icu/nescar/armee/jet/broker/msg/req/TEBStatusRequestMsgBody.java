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
 * TEBS状态上报的消息体
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0F01)
public class TEBStatusRequestMsgBody implements RequestMsgBody, Serializable {

    private static final long serialVersionUID = 6012078749469766047L;
    //tebs状态位
    @BasicField(startIndex = 0,dataType = MsgDataType.WORD,length = 2)
    private int tebStatus;

    //时间 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 2,dataType = MsgDataType.BCD,length = 6)
    private String time;

}


