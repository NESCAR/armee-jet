package icu.nescar.armee.jet.jet808.support.utils;

import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.HandlerMethod;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.MethodParameter;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;
import icu.nescar.armee.jet.jet808.support.msg.resp.VoidRespMsgBody;

/**
 * @author hylexus
 * Created At 2020-02-08 9:55 下午
 */
public class ArgumentUtils {

    public static boolean isNullReturnValue(Object result) {
        if (result == null) {
            return true;
        }

        return VoidRespMsgBody.class.isAssignableFrom(result.getClass());
    }

    public static Object[] resolveArguments(
            HandlerMethod handlerMethod, ArgumentContext context, HandlerMethodArgumentResolver argumentResolver) {

        final Object[] args = new Object[handlerMethod.getParameters().length];
        for (int i = 0; i < handlerMethod.getParameters().length; i++) {
            final MethodParameter parameter = handlerMethod.getParameters()[i];
            args[i] = argumentResolver.resolveArgument(parameter, context);
        }
        return args;
    }

}
