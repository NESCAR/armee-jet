package icu.nescar.armee.jet.jet808.support.converter.impl.resp;



import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.VoidRespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;

import java.util.Optional;

/**
 * 不给客户端回复消息
 *
 * @author hylexus
 * Created At 2020-02-02 2:50 下午
 */
public class VoidRespMsgBodyConverter extends AbstractBuiltinRespBodyConverter {


    public boolean supportsMsgBody(Object msgBody) {
        return msgBody == VoidRespMsgBody.NO_DATA_WILL_BE_SENT_TO_CLIENT || msgBody == null;
    }


    public Optional<RespMsgBody> convert(Object msgBody, Session session, RequestMsgMetadata metadata) {
        return Optional.empty();
    }

}
