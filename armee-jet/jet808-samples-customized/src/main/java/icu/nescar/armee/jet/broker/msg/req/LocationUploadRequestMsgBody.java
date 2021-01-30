package icu.nescar.armee.jet.broker.msg.req;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author hylexus
 * Created At 2020-02-02 7:16 下午
 * 位置上报消息的消息体
 *
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x200)
public class LocationUploadRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID = 5957418490574015693L;

    @BasicField(startIndex = 0,dataType = MsgDataType.DWORD,length = 4)
    private int warningFlag;//报警标志 紧急报警、超速报警、疲劳驾驶等报警位
    @BasicField(startIndex = 4,dataType = MsgDataType.DWORD,length = 4)
    private int status;//状态 acc开关、空车或半载满载、油路是否正常、电路是否正常、车门是否上锁等等

    @BasicField(startIndex = 8,dataType = MsgDataType.DWORD,length = 4)
    private int lat;//纬度latitude 以度为单位的纬度值乘以10的6次方

    @BasicField(startIndex = 12,dataType = MsgDataType.DWORD,length = 4)
    private int lng;//经度longitude 以度为单位的经度值乘以10的6次方

    @BasicField(startIndex = 16,dataType = MsgDataType.WORD,length = 2)
    private short height;//高程 海拔高度，单位为米
    @BasicField(startIndex = 18,dataType = MsgDataType.WORD,length = 2)
    private short speed;//单位为1/10km/h
    @BasicField(startIndex = 20,dataType = MsgDataType.WORD,length = 2)

    private Short direction;//0-359 正北为0，顺时针
    @BasicField(startIndex = 22,dataType = MsgDataType.BYTES,length = 6)

    private byte[] LocationTime;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}
