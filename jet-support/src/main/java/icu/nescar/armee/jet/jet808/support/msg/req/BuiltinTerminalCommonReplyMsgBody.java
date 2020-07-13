package icu.nescar.armee.jet.jet808.support.msg.req;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@BuiltinComponent
@Accessors(chain = true)
public class BuiltinTerminalCommonReplyMsgBody implements RequestMsgBody {
    private int replyFlowId;
    private int replyMsgId;
    private byte result;
}
