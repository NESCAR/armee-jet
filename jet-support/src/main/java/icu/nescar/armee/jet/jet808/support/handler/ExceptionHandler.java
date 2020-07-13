package icu.nescar.armee.jet.jet808.support.handler;



import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;
import icu.nescar.armee.jet.jet808.support.support.OrderedComponent;

import java.util.Collections;
import java.util.Set;

/**
 * @author hylexus
 * Created At 2020-02-08 5:39 下午
 */
public interface ExceptionHandler extends OrderedComponent {

    default Set<Class<? extends Throwable>> getSupportedExceptionTypes() {
        return Collections.emptySet();
    }

    Object handleException(Object handler, ArgumentContext argumentContext) throws Throwable;

}
