package icu.nescar.armee.jet.core.annotation.msg.handler;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808RequestMsgHandlerMapping {

    int[] msgType();

    String desc() default "";

}
