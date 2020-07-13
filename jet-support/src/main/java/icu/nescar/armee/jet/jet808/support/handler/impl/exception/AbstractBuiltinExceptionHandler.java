package icu.nescar.armee.jet.jet808.support.handler.impl.exception;


import icu.nescar.armee.jet.jet808.support.handler.ExceptionHandler;

//修改:这个变量是我添加的，原代码中跳转就是这个位置 但并没有这个import
import static icu.nescar.armee.jet.jet808.support.support.OrderedComponent.BUILTIN_COMPONENT_ORDER;

/**
 * @author hylexus
 * Created At 2020-02-09 3:00 下午
 */
public abstract class AbstractBuiltinExceptionHandler implements ExceptionHandler {

    @Override
    public int getOrder() {
        return BUILTIN_COMPONENT_ORDER;
    }

}
