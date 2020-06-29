package icu.nescar.armee.jet.jet808.support.msg.resp;


import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import lombok.Data;
import lombok.ToString;

/**
 * @author hylexus
 * Created At 2020-02-02 3:41 下午
 */
@Data
@ToString(of = {"msgType"})
public class AnnotationBasedCommonReplyMsgBodyWrapper implements RespMsgBody {

    private final MsgType msgType;
    private final byte[] bodyBytes;

    public AnnotationBasedCommonReplyMsgBodyWrapper(MsgType msgType, byte[] bodyBytes) {
        this.msgType = msgType;
        this.bodyBytes = bodyBytes;
    }

    @Override
    public byte[] toBytes() {
        return this.bodyBytes;
    }

    @Override
    public MsgType replyMsgType() {
        return this.msgType;
    }
}
