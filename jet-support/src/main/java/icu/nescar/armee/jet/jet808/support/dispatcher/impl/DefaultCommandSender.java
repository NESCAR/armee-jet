package icu.nescar.armee.jet.jet808.support.dispatcher.impl;



import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.codec.Encoder;
import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;

import java.io.IOException;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2020-03-11 22:09
 */
@BuiltinComponent
public class DefaultCommandSender extends AbstractCommandSender {

    private final ResponseMsgBodyConverter respMsgBodyConverter;
    private final Encoder encoder;

    public DefaultCommandSender(ResponseMsgBodyConverter respMsgBodyConverter, Encoder encoder) {
        this.respMsgBodyConverter = respMsgBodyConverter;
        this.encoder = encoder;
    }

    @Override
    protected byte[] encode(Object object, String terminalId, int flowId) throws IOException {
        final Optional<RespMsgBody> bodyInfo = respMsgBodyConverter.convert(object);
        if (bodyInfo.isPresent()) {
            return this.encoder.encodeRespMsg(bodyInfo.get(), flowId, terminalId);
        }
        return new byte[0];
    }
}
