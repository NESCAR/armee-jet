package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl;


import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;

/**
 * @author hylexus
 * Created At 2020-02-02 12:45 下午
 */
public class RequestMsgBodyArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return RequestMsgBody.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ArgumentContext context) throws ArgumentResolveException {
        return context.getMsg();
    }
}
