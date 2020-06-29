package icu.nescar.armee.jet.jet808.support.handler.impl.reflection;


import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.handler.AbstractMsgHandler;
import icu.nescar.armee.jet.jet808.support.handler.ExceptionHandler;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.VoidRespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import icu.nescar.armee.jet.jet808.support.utils.ArgumentUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author hylexus
 * Created At 2020-02-01 5:58 下午
 */
@Slf4j
public class CustomReflectionBasedRequestMsgHandler extends AbstractMsgHandler<RequestMsgBody> {

    private final Set<MsgType> supportedMsgTypes = new HashSet<>();
    private final ConcurrentMap<MsgType, HandlerMethod> mapping = new ConcurrentHashMap<>();
    private final HandlerMethodArgumentResolver argumentResolver;
    private final ResponseMsgBodyConverter responseMsgBodyConverter;
    private final ExceptionHandler exceptionHandler;

    public CustomReflectionBasedRequestMsgHandler(
            HandlerMethodArgumentResolver argumentResolver,
            ResponseMsgBodyConverter responseMsgBodyConverter,
            ExceptionHandler exceptionHandler) {

        this.argumentResolver = argumentResolver;
        this.responseMsgBodyConverter = responseMsgBodyConverter;
        this.exceptionHandler = exceptionHandler;
    }

    public Map<MsgType, HandlerMethod> getHandlerMethodMapping() {
        return Collections.unmodifiableMap(mapping);
    }

    @Override
    public int getOrder() {
        return ANNOTATION_BASED_DEV_COMPONENT_ORDER;
    }

    @Override
    public Set<MsgType> getSupportedMsgTypes() {
        return supportedMsgTypes;
    }

    public void addSupportedMsgType(MsgType msgType, HandlerMethod handlerMethod) {
        this.supportedMsgTypes.add(msgType);
        this.mapping.put(msgType, handlerMethod);
    }

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, RequestMsgBody msg, Session session) {

        final MsgType msgType = metadata.getMsgType();
        final HandlerMethod handlerMethod = mapping.get(msgType);
        if (handlerMethod == null) {
            log.warn("No HandlerMethod found for msgType {}, ReflectionBasedRequestMsgHandler return empty().", msgType);
            return Optional.empty();
        }

        Object result;
        try {
            result = this.invokeHandlerMethod(handlerMethod, metadata, msg, session);
        } catch (Throwable e) {
            try {
                ArgumentContext argumentContext = new ArgumentContext(metadata, session, msg, e);
                result = this.exceptionHandler.handleException(handlerMethod, argumentContext);
            } catch (Throwable throwable) {
                log.error("An unexpected exception occurred while invoke ExceptionHandler", throwable);
                return Optional.of(VoidRespMsgBody.NO_DATA_WILL_BE_SENT_TO_CLIENT);
            }
        }

        // [userType] --> RespMsgBody
        return this.responseMsgBodyConverter.convert(result, session, metadata);
    }

    private Object invokeHandlerMethod(HandlerMethod handlerMethod, RequestMsgMetadata metadata, RequestMsgBody msg, Session session) throws Throwable {
        final Object[] args = this.resolveArgs(handlerMethod, metadata, msg, session);
        return doInvoke(handlerMethod, args);
    }

    private Object[] resolveArgs(HandlerMethod handlerMethod, RequestMsgMetadata metadata, RequestMsgBody msg, Session session) {
        final ArgumentContext argumentContext = new ArgumentContext(metadata, session, msg, null);
        return ArgumentUtils.resolveArguments(handlerMethod, argumentContext, this.argumentResolver);
    }


    private Object doInvoke(HandlerMethod handlerMethod, Object[] args) throws Throwable {
        final Object result;
        try {
            result = handlerMethod.getMethod().invoke(handlerMethod.getBeanInstance(), args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }

        if (result == null && handlerMethod.isVoidReturnType()) {
            return VoidRespMsgBody.NO_DATA_WILL_BE_SENT_TO_CLIENT;
        }
        return result;
    }

}
