package icu.nescar.armee.jet.jet808.support.converter.impl;


import icu.nescar.armee.jet.jet808.support.converter.RequestMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;

/**
 * @author hylexus
 * Created At 2020-02-02 4:31 下午
 */
public abstract class AbstractBuiltinRequestMsgBodyConverter<E extends RequestMsgBody> implements RequestMsgBodyConverter<E> {
    @Override
    public int getOrder() {
        return BUILTIN_COMPONENT_ORDER;
    }
}
