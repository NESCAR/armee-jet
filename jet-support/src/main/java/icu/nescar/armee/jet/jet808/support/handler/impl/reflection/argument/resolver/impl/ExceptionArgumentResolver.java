package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl;


import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;

/**
 * @author hylexus
 * Created At 2020-02-09 12:55 下午
 */
public class ExceptionArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Throwable.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ArgumentContext context) throws ArgumentResolveException {
        return context.getThrowable();
    }
}
