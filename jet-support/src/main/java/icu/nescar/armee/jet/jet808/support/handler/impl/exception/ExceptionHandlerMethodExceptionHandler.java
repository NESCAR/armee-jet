package icu.nescar.armee.jet.jet808.support.handler.impl.exception;


import icu.nescar.armee.jet.jet808.support.handler.ExceptionHandler;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.HandlerMethod;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;
import icu.nescar.armee.jet.jet808.support.msg.resp.VoidRespMsgBody;
import icu.nescar.armee.jet.jet808.support.support.exception.scan.ExceptionHandlerMethod;
import icu.nescar.armee.jet.jet808.support.utils.ArgumentUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author hylexus
 * Created At 2020-02-08 5:56 下午
 */
@Slf4j
public class ExceptionHandlerMethodExceptionHandler implements ExceptionHandler {

    @Getter
    private final int order;

    private final ExceptionHandlerMethod exceptionHandlerMethod;
    private final HandlerMethodArgumentResolver argumentResolver;

    public ExceptionHandlerMethodExceptionHandler(ExceptionHandlerMethod exceptionHandlerMethod, HandlerMethodArgumentResolver argumentResolver, int order) {
        this.exceptionHandlerMethod = exceptionHandlerMethod;
        this.argumentResolver = argumentResolver;
        this.order = order;
    }

    @Override
    public Set<Class<? extends Throwable>> getSupportedExceptionTypes() {
        return exceptionHandlerMethod.getSupportedExceptionTypes();
    }

    @Override
    public Object handleException(Object handler, ArgumentContext argumentContext) throws Throwable {
        final Object beanInstance = this.exceptionHandlerMethod.getBeanInstance();
        final Method method = this.exceptionHandlerMethod.getMethod();
        final Object[] args = this.resolveArgs(this.exceptionHandlerMethod, argumentContext);

        if (this.exceptionHandlerMethod.isVoidReturnType()) {
            method.invoke(beanInstance, args);
            return VoidRespMsgBody.NO_DATA_WILL_BE_SENT_TO_CLIENT;
        }

        return method.invoke(beanInstance, args);
    }

    private Object[] resolveArgs(HandlerMethod handlerMethod, ArgumentContext argumentContext) {
        //ArgumentContext argumentContext = new ArgumentContext(metadata, session, msg, throwable);
        return ArgumentUtils.resolveArguments(handlerMethod, argumentContext, this.argumentResolver);
    }
}
