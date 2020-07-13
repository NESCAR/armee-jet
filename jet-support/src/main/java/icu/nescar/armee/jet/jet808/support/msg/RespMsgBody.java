package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.data.msg.MsgType;

/**
 * @author hylexus
 * createdAt 2018/12/29
 **/
public interface RespMsgBody {
    byte SUCCESS = 0;
    byte FAILURE = 1;
    byte AUTH_CODE_ERROR = 2;

    byte[] toBytes();

    default MsgType replyMsgType() {
        return BuiltinJt808MsgType.SERVER_COMMON_REPLY;
    }
}
