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
 * 上锁状态上报的消息体
 * 用来上报当前上锁操作是否成功
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0116)
public class LockUploadRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID = -6818087112114899700L;
    //第一位：上锁是否成功，其余位保留
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte lockStatus;

    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

