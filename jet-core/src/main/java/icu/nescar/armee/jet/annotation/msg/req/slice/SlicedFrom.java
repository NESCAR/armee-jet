package icu.nescar.armee.jet.annotation.msg.req.slice;

import java.lang.annotation.*;

/**
 * @author hylexus
 * Created At 2019-10-04 9:01 下午
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
