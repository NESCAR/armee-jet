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
 * @Date 2020/11/16
 * CAN总线数据上传  直接将can总线报文上传
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0705)
public class CANMsgRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID=12323;

    //数据项个数
    @BasicField(startIndex = 0, dataType = MsgDataType.WORD, length = 2)
    private int msgItem;

    //CAN总线数据接收时间
    @BasicField(startIndex = 2, dataType = MsgDataType.BCD, length = 5)
    private String time;//第 1 条 CAN 总线数据的接收时间，hh-mm-ss-msms

    //bit31 表示 CAN 通道号，0：CAN1，1：CAN2；
    //bit30 表示帧类型，0：标准帧，1：扩展帧；
    //bit29 表示数据采集方式，0：原始数据，1：采
    //集区间的平均值；
    //bit28-bit0 表示 CAN 总线 ID。
    @BasicField(startIndex = 7,dataType = MsgDataType.BYTE,length = 4)
    private int canID;

    //CAN 数据
    @BasicField(startIndex = 11,dataType = MsgDataType.BYTE,length = 8)
    private int canData;


}
