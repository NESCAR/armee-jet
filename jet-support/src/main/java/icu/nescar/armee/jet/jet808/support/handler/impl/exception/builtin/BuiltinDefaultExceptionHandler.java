package icu.nescar.armee.jet.jet808.support.handler.impl.exception.builtin;


import icu.nescar.armee.jet.annotation.msg.handler.Jt808ExceptionHandler;
import icu.nescar.armee.jet.annotation.msg.handler.Jt808RequestMsgHandlerAdvice;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hylexus
 * Created At 2020-02-09 3:11 下午
 */
@Slf4j
@Jt808RequestMsgHandlerAdvice
public class BuiltinDefaultExceptionHandler {

    @Jt808ExceptionHandler({Throwable.class})
    public void processThrowable(Throwable throwable) {
        log.info("BuiltinDefaultExceptionHandler :", throwable);
    }
}
