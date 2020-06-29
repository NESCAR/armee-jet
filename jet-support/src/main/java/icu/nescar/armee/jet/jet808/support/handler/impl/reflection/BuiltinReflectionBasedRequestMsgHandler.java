package icu.nescar.armee.jet.jet808.support.handler.impl.reflection;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.handler.ExceptionHandler;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.HandlerMethodArgumentResolver;

/**
 * @author hylexus
 * Created At 2020-02-02 2:05 下午
 */
@BuiltinComponent
public class BuiltinReflectionBasedRequestMsgHandler extends CustomReflectionBasedRequestMsgHandler {

    public BuiltinReflectionBasedRequestMsgHandler(
            HandlerMethodArgumentResolver argumentResolver, ResponseMsgBodyConverter responseMsgBodyConverter, ExceptionHandler exceptionHandler) {

        super(argumentResolver, responseMsgBodyConverter, exceptionHandler);
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
