package icu.nescar.armee.jet.jet808.support.support.exception.scan;


import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.HandlerMethod;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author hylexus
 * Created At 2020-02-08 4:07 下午
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
@Slf4j
public class ExceptionHandlerMethod extends HandlerMethod {

    private final Set<Class<? extends Throwable>> supportedExceptionTypes;

    public ExceptionHandlerMethod(Object beanInstance, Method method, boolean isVoidReturnType, Set<Class<? extends Throwable>> supportedExceptionTypes) {
        super(beanInstance, method, isVoidReturnType);
        this.supportedExceptionTypes = supportedExceptionTypes;
    }
}
