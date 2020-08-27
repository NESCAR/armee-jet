package icu.nescar.armee.jet.annotation.msg.resp;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 * 标记在响应消息实体类上
 * 当MsgHandler可以返回respMsgBody时，被这个注解标记的响应消息实体类也会被返回
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808RespMsgBody {

    int respMsgId();

    String desc() default "";

}
