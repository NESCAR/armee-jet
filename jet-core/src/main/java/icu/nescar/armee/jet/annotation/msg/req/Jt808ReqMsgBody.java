package icu.nescar.armee.jet.annotation.msg.req;

import java.lang.annotation.*;

/**
 *该注解只能标记于请求消息体实体类上
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

