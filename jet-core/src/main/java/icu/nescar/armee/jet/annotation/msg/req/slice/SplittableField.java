package icu.nescar.armee.jet.annotation.msg.req.slice;

import java.lang.annotation.*;

/**
 *
 * @author whale
 * @date 2020-8-20
 * 将被修饰的字段拆分之后赋值给另一个bean
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
