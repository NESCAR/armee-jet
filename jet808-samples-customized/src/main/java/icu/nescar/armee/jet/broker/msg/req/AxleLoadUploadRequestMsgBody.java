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
 * 轴负载信息的消息体
 * 用注解方式 效果相当于实现了对应的消息转换器
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0109)


public class AxleLoadUploadRequestMsgBody implements RequestMsgBody {
    //轴负载总和 起始位置 两个字节
    @BasicField(startIndex = 0,dataType = MsgDataType.WORD,length = 2)
    private int axleLoadSum;

    //左边第一轴负载 int值
    @BasicField(startIndex = 2,dataType = MsgDataType.WORD,length=2)
    private int leftFirstAxleLoad;

    //左边第二轴
    @BasicField(startIndex = 4,dataType = MsgDataType.WORD,length=2)
    private int leftSecondAxleLoad;

    @BasicField(startIndex = 6,dataType = MsgDataType.WORD,length=2)
    private int leftThirdAxleLoad;

    @BasicField(startIndex = 8,dataType = MsgDataType.WORD,length=2)
    private int rightFirstAxleLoad;

    @BasicField(startIndex = 10,dataType = MsgDataType.WORD,length=2)
    private int rightSecondAxleLoad;

    @BasicField(startIndex = 12,dataType = MsgDataType.WORD,length=2)
    private int rightThirdAxleLoad;

    //BCD 长度6字节
    //时间 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 14,dataType = MsgDataType.BCD,length=6)
    private String time;


}

