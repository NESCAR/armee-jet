package icu.nescar.armee.jet.jet808.support.dispatcher;

import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.converter.RequestMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;
import icu.nescar.armee.jet.jet808.support.support.RequestMsgBodyConverterMapping;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/1/24
 **/
@Slf4j
public abstract class AbstractRequestMsgDispatcher implements RequestMsgDispatcher {

    private final RequestMsgBodyConverterMapping msgConverterMapping;

    public AbstractRequestMsgDispatcher(RequestMsgBodyConverterMapping msgConverterMapping) {
        this.msgConverterMapping = msgConverterMapping;
    }

    public void doDispatch(RequestMsgWrapper wrapper) throws Exception {

        final Optional<RequestMsgBody> subMsg = tryParseMsgBody(wrapper);
        if (!subMsg.isPresent()) {
            return;
        }

        wrapper.setBody(subMsg.get());
        this.doBroadcast(wrapper);
    }

    private Optional<RequestMsgBody> tryParseMsgBody(RequestMsgWrapper wrapper) {
        final MsgType msgType = wrapper.getMetadata().getMsgType();
        final Optional<RequestMsgBodyConverter<? extends RequestMsgBody>> converterInfo = this.msgConverterMapping.getConverter(msgType);
        if (!converterInfo.isPresent()) {
            log.error("No [MsgConverter] found for msgType {}", msgType);
            return Optional.empty();
        }

        final RequestMsgBodyConverter<? extends RequestMsgBody> msgBodyConverter = converterInfo.get();
        final Optional<? extends RequestMsgBody> subMsg = msgBodyConverter.convert2Entity(wrapper.getMetadata());
        if (!subMsg.isPresent()) {
            log.debug("[MsgConverter] return empty(). converter:{}", msgBodyConverter.getClass());
            return Optional.empty();
        }

        final RequestMsgBody requestMsgBody = subMsg.get();
        return Optional.of(requestMsgBody);
    }

    public abstract void doBroadcast(RequestMsgWrapper wrapper) throws Exception;
}
