package icu.nescar.armee.jet.annotation.msg.req.basic;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.req.entity.ReqMsgFieldConverter;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-18
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BasicField {

    /**
     * @return 赋值顺序，值越小优先级越高
     */
    int order() default Integer.MIN_VALUE;

    int startIndex() default 0;

    String startIndexMethod() default "";

    MsgDataType dataType();

    /**
     * 1. {@link #dataType()}
     * 2. {@code length()}
     * 3. {@link #byteCountMethod()}
     *
     * @return 该字段的字节数
     *
     */
    int length() default 0;

    /**
     * 1. {@link #dataType()}
     * 2. {@link #length()}
     * 3. {@code byteCountMethod()}
     *
     * @return 表示字节数长度的字段, 必须为int或Integer
     */
    String byteCountMethod() default "";

    Class<? extends ReqMsgFieldConverter> customerDataTypeConverterClass() default ReqMsgFieldConverter.NoOpsConverter.class;

}
