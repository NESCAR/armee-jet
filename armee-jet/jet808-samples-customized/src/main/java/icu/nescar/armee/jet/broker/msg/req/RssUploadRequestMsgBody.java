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
 * rss 是 rollover stability support侧倾稳定性支持的缩写
 * 帮助防止货车翻滚。通过监控拖车的车轮速度、横向加速度和货物负载，以检测危险的驾驶情况，
 * 协助驾驶员保持车辆控制并帮助防止侧倾
 * 侧倾稳定性支持上报的消息体
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0F02)
public class RssUploadRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID = -3688883555773127021L;
    //横向加速度 信号为一个字节
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte latAcceleration;

    //总货物负载
    @BasicField(startIndex = 1,dataType = MsgDataType.WORD,length = 2)
    private int axleLoadSum;

    //左第一个车轮速度
    @BasicField(startIndex = 3,dataType = MsgDataType.WORD,length=2)
    private short leftFirstWheelSpeed;

    //左第二个车轮速度
    @BasicField(startIndex = 5,dataType = MsgDataType.WORD,length=2)
    private short leftSecondWheelSpeed;

    //左第三个车轮速度
    @BasicField(startIndex = 7,dataType = MsgDataType.WORD,length=2)
    private short leftThirdWheelSpeed;

    //右第一个车轮速度
    @BasicField(startIndex = 9,dataType = MsgDataType.WORD,length=2)
    private short rightFirstWheelSpeed;

    //右第二个车轮速度
    @BasicField(startIndex = 11,dataType = MsgDataType.WORD,length=2)
    private short rightSecondWheelSpeed;

    //右第三个车轮速度
    @BasicField(startIndex = 13,dataType = MsgDataType.WORD,length=2)
    private short rightThirdWheelSpeed;


    //时间 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex =15,dataType = MsgDataType.BCD,length = 6)
    private String rssTime;



}

