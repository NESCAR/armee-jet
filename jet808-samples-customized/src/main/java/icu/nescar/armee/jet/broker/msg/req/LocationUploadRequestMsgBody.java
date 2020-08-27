package icu.nescar.armee.jet.broker.msg.req;

import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hylexus
 * Created At 2020-02-02 7:16 下午
 * 位置上报消息的消息体
 *
 */
@Data
@Accessors(chain = true)
public class LocationUploadRequestMsgBody implements RequestMsgBody {
    private int warningFlag;//报警标志 紧急报警、超速报警、疲劳驾驶等报警位

    private int status;//状态 acc开关、空车或半载满载、油路是否正常、电路是否正常、车门是否上锁等等

    private Double lat;//纬度latitude 以度为单位的纬度值乘以10的6次方

    private Double lng;//经度longitude 以度为单位的经度值乘以10的6次方

    private short height;//高程 海拔高度，单位为米

    private short speed;//单位为1/10km/h

    private Short direction;//0-359 正北为0，顺时针

    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}
