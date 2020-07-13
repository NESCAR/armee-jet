package icu.nescar.armee.jet.jet808.support.dispatcher;

import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;

public interface RequestMsgDispatcher {

    void doDispatch(RequestMsgWrapper wrapper) throws Exception;

}
