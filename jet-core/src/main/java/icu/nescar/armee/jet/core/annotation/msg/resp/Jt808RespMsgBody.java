package icu.nescar.armee.jet.core.annotation.msg.resp;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808RespMsgBody {

    int respMsgId();

    String desc() default "";

}
