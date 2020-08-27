package icu.nescar.armee.jet.broker.msg.req;


import icu.nescar.armee.jet.annotation.msg.req.Jt808ReqMsgBody;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/8/13
 * 里程信息上报的消息体 一个里程数值，一个当前时间
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0112)

public class MileageUploadRequestMsgBody implements RequestMsgBody {
    private int mileage; // 4个字节 1/10km，对应车上里程表读数
    private String time;
}

