package icu.nescar.armee.jet.jet808.support.queue;


import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;

/**
 * @author hylexus
 * Created At 2019-08-24 16:38
 */
public interface RequestMsgQueue {

    void postMsg(RequestMsgWrapper wrapper) throws InterruptedException;

    default RequestMsgWrapper takeMsg() throws InterruptedException {
        return null;
    }
}

