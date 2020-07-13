package icu.nescar.armee.jet.jet808.support.dispatcher.impl;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.dispatcher.AbstractRequestMsgDispatcher;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;
import icu.nescar.armee.jet.jet808.support.queue.RequestMsgQueue;
import icu.nescar.armee.jet.jet808.support.support.RequestMsgBodyConverterMapping;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hylexus
 * Created At 2019-08-24 14:10
 */
@Slf4j
@BuiltinComponent
public class LocalEventBusDispatcher extends AbstractRequestMsgDispatcher {

    private final RequestMsgQueue eventBus;

    public LocalEventBusDispatcher(RequestMsgBodyConverterMapping msgConverterMapping, RequestMsgQueue eventBus) {
        super(msgConverterMapping);
        this.eventBus = eventBus;
    }

    @Override
    public void doBroadcast(RequestMsgWrapper wrapper) throws Exception {
        log.debug("[EventBus] receive msg : {}", wrapper);
        eventBus.postMsg(wrapper);
    }
}
