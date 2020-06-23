package icu.nescar.armee.jet.core.annotation.msg.req.slice;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SplittableField {

    /**
     * @return 字段名(将属性拆分到嵌套的bean中)
     */
    String splitPropertyValueIntoNestedBeanField();

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface BitAt {
        int bitIndex();
    }

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface BitAtRange {

        int startIndex();

        int endIndex();
    }
}
