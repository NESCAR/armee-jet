package icu.nescar.armee.jet.jet808.support.queue.listener;

import com.google.common.eventbus.Subscribe;

import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.codec.Encoder;
import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.handler.ExceptionHandler;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;
import icu.nescar.armee.jet.jet808.support.queue.impl.LocalEventBus;
import icu.nescar.armee.jet.jet808.support.support.MsgHandlerMapping;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author hylexus
 * Created At 2019-08-24 16:44
 */
@Slf4j
@BuiltinComponent
public class LocalEventBusListener extends AbstractRequestMsgQueueListener<LocalEventBus> {


    public LocalEventBusListener(
            MsgHandlerMapping msgHandlerMapping, LocalEventBus queue, ExceptionHandler exceptionHandler,
            ResponseMsgBodyConverter responseMsgBodyConverter, Encoder encoder) {

        super(msgHandlerMapping, queue, exceptionHandler, responseMsgBodyConverter, encoder);
    }

    @PostConstruct
    public void init() {
        //noinspection UnstableApiUsage
        queue.register(this);
    }

    @Subscribe
    public void listen(RequestMsgWrapper wrapper) throws IOException, InterruptedException {
        consumeMsg(wrapper.getMetadata(), wrapper.getBody());
    }

}
