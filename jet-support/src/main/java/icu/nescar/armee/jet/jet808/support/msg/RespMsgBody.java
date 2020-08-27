package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.data.msg.MsgType;

/**
 * @author hylexus
 * 应答消息结构
 * createdAt 2018/12/29
 **/
public interface RespMsgBody {
    byte SUCCESS = 0;
    byte FAILURE = 1;
    byte AUTH_CODE_ERROR = 2;

    byte[] toBytes();

//返回固有的平台通用应答  default是用来在接口中写默认方法的一个修饰符
    default MsgType replyMsgType() {
        return BuiltinJt808MsgType.SERVER_COMMON_REPLY;
    }
}
