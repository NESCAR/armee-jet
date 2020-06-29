package icu.nescar.armee.jet.jet808.support.queue;



import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;

import java.io.IOException;

/**
 * @author hylexus
 * Created At 2019-08-25 18:29
 */
public interface RequestMsgQueueListener {

    void consumeMsg(RequestMsgMetadata metadata, RequestMsgBody body) throws IOException, InterruptedException;

}
