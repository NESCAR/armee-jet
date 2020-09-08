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
 * TEBS接收上锁信息应答上报的消息体
 * 上锁信息下发是发到tbox，还需要传递给TEBS的ecu
 * 一些设置需要判断其是否成功接收 再进行下一步操作
 */
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0114)
public class TEBSAcceptRequestMsgBody implements RequestMsgBody {
    //上锁信息接收结果的应答上报
    //第一位：车辆识别号是否成功  0否1是
    //第二位：司机身份信息存储本地是否成功
    //第三位：解锁密码是否接收成功
    //第四位：上锁时间起点是否存储成功
    //第五位：上锁时间终点是否存储成功
    //第6.7.8位： 保留给不同的错误类型
    @BasicField(startIndex = 0,dataType = MsgDataType.BYTE,length = 1)
    private byte tebsAcceptResult;

    //事件发生时的时间
    @BasicField(startIndex = 1,dataType = MsgDataType.BCD,length = 6)
    private String time;//时间 YY-MM-DD-hh-mm-ss GMT+8时间
}

