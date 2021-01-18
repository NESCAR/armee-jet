package icu.nescar.armee.jet.broker.msg.req;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.data.MsgDataType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2021/1/15
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0F06)

public class AuthUpdateSuccessRequestMsgBody {
    private static final long serialVersionUID = -7197293729749L;
    //司机身份信息
    @BasicField(startIndex = 0,dataType = MsgDataType.STRING,length=1)
    private String driverId;

    @BasicField(startIndex =6 ,dataType = MsgDataType.BCD,length = 6)
    private String lockTimeStart;//上锁时间起点 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 12,dataType = MsgDataType.BCD,length = 6)
    private String lockTimeEnd;//上锁时间终点 YY-MM-DD-hh-mm-ss GMT+8时间

}

