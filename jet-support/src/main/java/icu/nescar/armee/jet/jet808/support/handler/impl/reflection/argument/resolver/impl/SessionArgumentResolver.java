package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl;


import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;
import icu.nescar.armee.jet.jet808.support.session.Session;

/**
 * @author hylexus
 * Created At 2020-02-02 12:45 下午
 */
public class SessionArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Session.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ArgumentContext context) throws ArgumentResolveException {
        return context.getSession();
    }
}
