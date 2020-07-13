package icu.nescar.armee.jet.jet808.support.converter;

import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import icu.nescar.armee.jet.jet808.support.support.OrderedComponent;


import javax.annotation.Nullable;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2020-02-02 3:19 下午
 */
public interface ResponseMsgBodyConverter extends OrderedComponent {

    boolean supportsMsgBody(Object msgBody);

    Optional<RespMsgBody> convert(Object msgBody, @Nullable Session session, @Nullable RequestMsgMetadata metadata);

    default Optional<RespMsgBody> convert(Object msgBody) {
        return this.convert(msgBody, null, null);
    }

}
