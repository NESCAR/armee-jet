package icu.nescar.armee.jet.annotation.msg.req.slice;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SlicedFrom {

    int DEFAULT_BIT_INDEX = -1;

    String sourceFieldName();

    int bitIndex() default DEFAULT_BIT_INDEX;

    int startBitIndex() default DEFAULT_BIT_INDEX;

    int endBitIndex() default DEFAULT_BIT_INDEX;
}
