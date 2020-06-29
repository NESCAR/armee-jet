package icu.nescar.armee.jet.jet808.support.converter.impl.resp;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.converter.MsgTypeParser;
import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hylexus
 * Created At 2020-02-02 6:16 下午
 */
@BuiltinComponent
public class DelegateRespMsgBodyConverter extends AbstractBuiltinRespBodyConverter {

    private final MsgTypeParser msgTypeParser;
    private final List<ResponseMsgBodyConverter> converters = new ArrayList<>();
    private final Map<Class<?>, ResponseMsgBodyConverter> mapping = new ConcurrentHashMap<>();

    public DelegateRespMsgBodyConverter(MsgTypeParser msgTypeParser) {
        this.msgTypeParser = msgTypeParser;
        this.addDefaultConverters();
    }

    private void addDefaultConverters() {

        this.addConverter(new NoOpsRespMsgBodyConverter());
        this.addConverter(new VoidRespMsgBodyConverter());

        final ReflectionBasedRespMsgBodyConverter reflectionBasedRespMsgBodyConverter = new ReflectionBasedRespMsgBodyConverter(this.msgTypeParser);
        this.addConverter(reflectionBasedRespMsgBodyConverter);
        this.addConverter(new CommandMsgBodyConverter(reflectionBasedRespMsgBodyConverter));
    }

    @Override
    public boolean supportsMsgBody(Object msgBody) {
        return getConverter(msgBody) != null;
    }

    @Override
    public Optional<RespMsgBody> convert(Object msgBody, Session session, RequestMsgMetadata metadata) {
        final ResponseMsgBodyConverter converter = this.getConverter(msgBody);
        if (converter != null) {
            return converter.convert(msgBody, session, metadata);
        }
        return Optional.empty();
    }


    private ResponseMsgBodyConverter getConverter(Object msgBody) {
        final ResponseMsgBodyConverter converter = mapping.get(msgBody.getClass());
        if (converter != null) {
            return converter;
        }
        for (ResponseMsgBodyConverter responseMsgBodyConverter : converters) {
            if (responseMsgBodyConverter.supportsMsgBody(msgBody)) {
                this.mapping.put(msgBody.getClass(), responseMsgBodyConverter);
                return responseMsgBodyConverter;
            }
        }
        return null;
    }

    public DelegateRespMsgBodyConverter addConverter(ResponseMsgBodyConverter converter) {
        this.converters.add(converter);
        return this;
    }
}
