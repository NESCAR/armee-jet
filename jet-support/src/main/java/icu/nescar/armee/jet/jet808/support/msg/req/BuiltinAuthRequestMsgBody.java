package icu.nescar.armee.jet.jet808.support.msg.req;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.annotation.msg.req.Jt808ReqMsgBody;
import icu.nescar.armee.jet.annotation.msg.req.basic.BasicField;
import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgHeader;
import icu.nescar.armee.jet.jet808.support.support.entity.scan.RequestMsgHeaderAware;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author hylexus
 * createdAt 2018/12/28
 **/
@Data
@Accessors(chain = true)
@Jt808ReqMsgBody(msgType = 0x0102)
@BuiltinComponent
public class BuiltinAuthRequestMsgBody implements RequestMsgBody, RequestMsgHeaderAware {

    private RequestMsgHeader header;

    /**
     * 鉴权码
     * 长度：
     * <pre>
     * --> 1. {@link MsgDataType#getByteCount()} ==0
     * --> 2. {@link BasicField#length()} == 0
     * --> 3. {@link BasicField#byteCountMethod()}
     * </pre>
     */
    @BasicField(startIndex = 0, dataType = MsgDataType.STRING, byteCountMethod = "getAuthCodeByteCount")
    private String authCode;

    public int getAuthCodeByteCount() {
        return header.getMsgBodyLength();
    }

    @Override
    public void setRequestMsgHeader(RequestMsgHeader header) {
        this.header = header;
    }
}
