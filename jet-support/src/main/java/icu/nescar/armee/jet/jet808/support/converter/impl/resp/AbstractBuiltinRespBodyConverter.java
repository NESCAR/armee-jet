package icu.nescar.armee.jet.jet808.support.converter.impl.resp;


import icu.nescar.armee.jet.jet808.support.converter.ResponseMsgBodyConverter;

/**
 * @author hylexus
 * Created At 2020-02-02 4:08 下午
 */
public abstract class AbstractBuiltinRespBodyConverter implements ResponseMsgBodyConverter {

    @Override
    public int getOrder() {
        return ANNOTATION_BASED_DEV_COMPONENT_ORDER;
    }

}
