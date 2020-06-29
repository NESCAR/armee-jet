package icu.nescar.armee.jet.jet808.support.handler;



import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.session.Session;
import icu.nescar.armee.jet.jet808.support.support.OrderedComponent;

import java.util.Collections;
import java.util.Set;

/**
 * @author hylexus
 * createdAt 2019/2/1
 **/
public interface MsgHandler<T extends RequestMsgBody> extends OrderedComponent {

    default Set<MsgType> getSupportedMsgTypes() {
        return Collections.emptySet();
    }

    void handleMsg(RequestMsgMetadata metadata, T body, Session session) throws Throwable;

}
