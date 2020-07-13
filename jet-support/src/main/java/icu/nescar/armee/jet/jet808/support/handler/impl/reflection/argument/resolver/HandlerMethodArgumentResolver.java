package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver;


import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;

/**
 * 抄袭自 org.springframework.web.method.support.HandlerMethodArgumentResolver
 *
 * @author hylexus
 * Created At 2020-02-02 12:38 下午
 */
public interface HandlerMethodArgumentResolver {

    boolean supportsParameter(MethodParameter methodParameter);

    Object resolveArgument(MethodParameter methodParameter, ArgumentContext context) throws ArgumentResolveException;

}
