package icu.nescar.armee.jet.broker.msg.req;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.annotation.msg.req.slice.SlicedFrom;
import io.github.hylexus.jt.data.MsgDataType;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Auther whale
 * @Date 2020/9/7
 * 解锁状态（人为解锁时）上报的消息体
 * 解锁方式有两种 一种是ic卡解锁，一种是密码解锁
 * 需要对解锁是否成功和哪种解锁方式进行上报
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0115)
public class UnlockUploadRequestMsgBody implements RequestMsgBody, Serializable {
    private static final long serialVersionUID = 4777988741696018338L;
    //解锁状态信息
    //第一位：解锁是否成功
    //第二位：解锁方式 0代表ic卡解锁 1代表密码解锁
    //其余位保留
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte unlockStatus;

    @SlicedFrom(sourceFieldName = "unlockStatus",bitIndex = 0)
    private boolean unlockResult;

    @SlicedFrom(sourceFieldName = "unlockStatus",bitIndex = 1)
    private boolean unlockWay;



    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

