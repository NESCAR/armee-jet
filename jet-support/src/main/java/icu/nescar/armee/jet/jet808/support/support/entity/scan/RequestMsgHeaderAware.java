package icu.nescar.armee.jet.jet808.support.support.entity.scan;

import icu.nescar.armee.jet.annotation.msg.req.Jt808ReqMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgHeader;

/**
 * 目前仅仅适用于 {@link Jt808ReqMsgBody} 修饰的类
 * @Auther whale
 * @Date 2020/6/27
 */
public interface RequestMsgHeaderAware {

    void setRequestMsgHeader(RequestMsgHeader header);

}