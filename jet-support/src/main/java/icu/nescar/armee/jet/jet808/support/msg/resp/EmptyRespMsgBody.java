package icu.nescar.armee.jet.jet808.support.msg.resp;


import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;

/**
 * @author hylexus
 * 空的应答消息类型
 * Created At 2020-03-11 10:15 下午
 */
public class EmptyRespMsgBody implements RespMsgBody {

    private final MsgType msgType;

    public EmptyRespMsgBody(MsgType msgType) {
        this.msgType = msgType;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }

    @Override
    public MsgType replyMsgType() {
        return msgType;
    }
}
