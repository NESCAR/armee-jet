package icu.nescar.armee.jet.jet808.support.converter.impl.resp;



import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.VoidRespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;

import java.util.Optional;

/**
 * @author hylexus
 * Created At 2020-02-02 3:21 下午
 */
public class NoOpsRespMsgBodyConverter extends AbstractBuiltinRespBodyConverter {

    @Override
    public boolean supportsMsgBody(Object msgBody) {
        return msgBody != VoidRespMsgBody.NO_DATA_WILL_BE_SENT_TO_CLIENT && msgBody instanceof RespMsgBody;
    }

    @Override
    public Optional<RespMsgBody> convert(Object msgBody, Session session, RequestMsgMetadata metadata) {
        // mosBody 本身就是 RespMsgBody 类型
        return Optional.of((RespMsgBody) msgBody);
    }

}
