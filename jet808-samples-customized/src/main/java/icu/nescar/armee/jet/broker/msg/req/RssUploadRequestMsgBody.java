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
 * rss 是 rollover stability support侧倾稳定性支持的缩写
 * 帮助防止货车翻滚。通过监控拖车的车轮速度、横向加速度和货物负载，以检测危险的驾驶情况，
 * 协助驾驶员保持车辆控制并帮助防止侧倾
 * 侧倾稳定性支持上报的消息体
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0111)

public class RssUploadRequestMsgBody implements RequestMsgBody {
    //横向加速度 信号为一个字节
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte latAcceleration;

    //总货物负载
    @BasicField(startIndex = 1,dataType = MsgDataType.WORD,length = 2)
    private int axleLoadSum;

    //时间 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex =3,dataType = MsgDataType.BCD,length = 6)
    private String time;



}

