package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver;


import icu.nescar.armee.jet.jet808.support.exception.ArgumentResolveException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;

/**
 * 抄袭自 org.springframework.web.method.support.HandlerMethodArgumentResolver
 * 方法参数解析器 用来处理方法参数，包含方法supportsParameter（满足某种要求，返回true，方可进入resolveArgument做参数处理）
 * 和方法resolveArgument
 * 用来将参数注入底层 参数如：msgbody msgheader msgmetadata session等
 *
 * @author hylexus
 * Created At 2020-02-02 12:38 下午
 */
public interface HandlerMethodArgumentResolver {

    boolean supportsParameter(MethodParameter methodParameter);

    Object resolveArgument(MethodParameter methodParameter, ArgumentContext context) throws ArgumentResolveException;

}
