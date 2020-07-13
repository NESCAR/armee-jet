package icu.nescar.armee.jet.annotation.msg.req;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808ReqMsgBody {

    int[] msgType();

    String desc() default "";

}

