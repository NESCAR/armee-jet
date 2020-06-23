package icu.nescar.armee.jet.core.annotation.msg.resp;

import icu.nescar.armee.jet.core.data.MsgDataType;
import icu.nescar.armee.jet.core.data.converter.resp.entity.RespMsgFieldConverter;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommandField {

    /**
     * @return 字段的处理顺序，值越小优先级越高
     */
    int order();

    MsgDataType targetMsgDataType() default MsgDataType.UNKNOWN;

    boolean isNestedCommandField() default false;

    Class<? extends RespMsgFieldConverter> customerDataTypeConverterClass() default RespMsgFieldConverter.NoOpsConverter.class;
}