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
 * @Date 2020/9/7
 * 报警信息上报的消息体
 * 比如：当需要在规定时间停车时却没有停车，就会进行报警信息上报。进行人工处理
 * 不使用注解转换了，将数据类型的转换功能放在平台端
 * 节点所需要做的就是传递数据，例如BCD格式的时间 17年用0x17 传过来就是 16+7=23
 * 节点不需要将23转换成"17"string类型，只需要将23传到平台，平台进行转换成17就可以。
 *
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0F06)

public class AlarmUploadRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID = -719995758967881209L;
    //未按规定停车报警位
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length=1)
    private byte alarmStatus;

    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BYTES,length = 6)
    private byte[] alarmTime;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

