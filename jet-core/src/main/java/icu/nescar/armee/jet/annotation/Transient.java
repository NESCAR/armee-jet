package icu.nescar.armee.jet.annotation;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Transient {
}
