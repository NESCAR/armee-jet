package icu.nescar.armee.jet.broker.msg.command;

import io.github.hylexus.jt.annotation.msg.req.basic.BasicField;
import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.data.MsgDataType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

import static io.github.hylexus.jt.data.MsgDataType.BCD;
import static io.github.hylexus.jt.data.MsgDataType.STRING;

/**
 * @Auther whale
 * @Date 2020/11/26
 */
@Data
@Accessors(chain = true)
public class LockInfoSettingsMsgBody implements Serializable {
    private static final long serialVersionUID=145778999L;

    //车辆的身份信息
    @BasicField(startIndex = 0,dataType = STRING,length =4 )
    private String carID;

    //司机身份信息(显示在页面上的员工号）
    @BasicField(startIndex = 4,dataType = STRING,length = 4)
    private String driverID;

    //即ic卡信息 终端通过ic卡刷卡解锁
    @BasicField(startIndex = 8,dataType = STRING,length=4)
    private String ICID;

    //上锁时间起点 服务端设置的上锁时间范围 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 12,dataType = BCD,length = 6)
    private String lockTimeStart;

    //上锁时间终点 YY-MM-DD-hh-mm-ss GMT+8时间
    @BasicField(startIndex = 18,dataType = BCD,length = 6)
    private String lockTimeEnd;
}

