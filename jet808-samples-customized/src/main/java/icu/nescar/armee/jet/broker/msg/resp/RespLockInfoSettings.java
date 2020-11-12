package icu.nescar.armee.jet.broker.msg.resp;

import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.annotation.msg.resp.Jt808RespMsgBody;
import io.github.hylexus.jt.data.resp.BytesValueWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

import static io.github.hylexus.jt.data.MsgDataType.*;

/**
 * @Auther whale
 * @Date 2020/9/7
 * @Jt808RespMsgBody 注解的作用就是类似于该实体类实现了RespMsgBody
 * 主动下发的上锁消息（设置）
 */
@Data
@Accessors(chain = true)
@Jt808RespMsgBody(respMsgId = 0x8114,desc = "上锁信息下发（设置）")
public class RespLockInfoSettings implements Serializable {
    private static final long serialVersionUID = -6472219189781375967L;
    //车辆的身份信息
    @CommandField(order = 1, targetMsgDataType = STRING)
    private String carID;

    //司机身份信息
    @CommandField(order = 2,targetMsgDataType = STRING)
    private String driverID;

    //解锁密码 云端还需将此解锁密码通过短信方式发送给司机
    @CommandField(order=3,targetMsgDataType = STRING)
    private String password;

    //上锁时间起点 服务端设置的上锁时间范围
    @CommandField(order = 4,targetMsgDataType = BCD)
    private String lockTimeStart;

    //上锁时间终点
    @CommandField(order=5,targetMsgDataType = BCD)
    private String lockTimeEnd;

    

}

