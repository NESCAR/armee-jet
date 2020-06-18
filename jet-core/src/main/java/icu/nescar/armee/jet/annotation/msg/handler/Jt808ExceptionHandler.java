package icu.nescar.armee.jet.annotation.msg.handler;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808ExceptionHandler {

    Class<? extends Throwable>[] value() default {};

}
