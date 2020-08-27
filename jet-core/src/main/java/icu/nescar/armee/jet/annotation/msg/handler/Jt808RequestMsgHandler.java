package icu.nescar.armee.jet.annotation.msg.handler;

import java.lang.annotation.*;

/**
 *
 * @author whale
 * @date 2020-8-20
 * 该注解只能标记在请求消息实体类上，并且指定msgType=808报文类型 示例：0x0200
 *
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Jt808RequestMsgHandler {

}
