package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.MsgType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/6/27
 */

@Data
@Accessors(chain = true)
public class RequestMsgMetadata {
    protected RequestMsgHeader header;
    protected byte[] bodyBytes;
    protected byte checkSum;
    protected MsgType msgType;

    @Override
    public String toString() {
        return "RequestMsgMetadata{"
                + "msgType=" + msgType
                + ", header=" + header
                //+ ", bodyBytes=" + Arrays.toString(bodyBytes)
                + ", checkSum=" + checkSum
                + '}';
    }
}