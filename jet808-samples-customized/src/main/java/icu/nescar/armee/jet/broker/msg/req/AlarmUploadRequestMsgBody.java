package icu.nescar.armee.jet.broker.msg.req;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/9/7
 * 报警信息上报的消息体
 * 比如：当需要在规定时间停车时却没有停车，就会进行报警信息上报。进行人工处理
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0117)

public class AlarmUploadRequestMsgBody implements RequestMsgBody {
    //未按规定停车报警位
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length=1)
    private byte alarmStatus;

    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

