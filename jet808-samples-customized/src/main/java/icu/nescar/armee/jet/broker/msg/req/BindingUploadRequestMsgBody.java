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
 * 绑定消息上报的消息体
 * 当拖车和挂车分离的时候，重新绑定需上报新的车辆和对应司机的绑定消息。
 * 只在拖车和挂车分离和连接状态发生改变时，进行上报。以便于下一步操作
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0118)
public class BindingUploadRequestMsgBody implements RequestMsgBody {
    //拖车的身份信息
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte trailerId;

    //绑定的司机身份信息
    @BasicField(startIndex = 1,dataType = MsgDataType.WORD,length = 2)
    private short driverId;

    //拖车和挂车处于分离还是连接状态
    @BasicField(startIndex = 3,dataType = MsgDataType.BYTE,length=1)
    private byte bindingStatus;

    //事件发生时的时间
    @BasicField(startIndex = 4,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

