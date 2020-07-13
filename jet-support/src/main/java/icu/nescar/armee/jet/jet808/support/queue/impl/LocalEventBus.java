package icu.nescar.armee.jet.jet808.support.queue.impl;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.SubscriberExceptionHandler;
import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;
import icu.nescar.armee.jet.jet808.support.queue.RequestMsgQueue;


import java.util.concurrent.Executor;

/**
 * @author hylexus
 * Created At 2019-08-24 14:17
 */
@BuiltinComponent
public class LocalEventBus extends AsyncEventBus implements RequestMsgQueue {

    public LocalEventBus(String identifier, Executor executor) {
        super(identifier, executor);
    }

    public LocalEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(executor, subscriberExceptionHandler);
    }

    public LocalEventBus(Executor executor) {
        super(executor);
    }

    @Override
    public void postMsg(RequestMsgWrapper wrapper) throws InterruptedException {
        super.post(wrapper);
    }
}
