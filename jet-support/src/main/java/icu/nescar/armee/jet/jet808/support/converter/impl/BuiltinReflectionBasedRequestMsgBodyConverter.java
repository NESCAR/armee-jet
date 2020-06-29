package icu.nescar.armee.jet.jet808.support.converter.impl;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hylexus
 * Created At 2019-09-19 10:35 下午
 */
@Slf4j
@BuiltinComponent
public class BuiltinReflectionBasedRequestMsgBodyConverter extends CustomReflectionBasedRequestMsgBodyConverter {

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
