package icu.nescar.armee.jet.jet808.support.support.entity.scan;

import icu.nescar.armee.jet.annotation.msg.req.Jt808ReqMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;

/**
 * @Auther whale
 * @Date 2020/6/27
 * 目前仅仅适用于 {@link Jt808ReqMsgBody} 修饰的类
 * 每次消息处理时为实体类注入RequestMsgMetadata 的实例
 */
public interface RequestMsgMetadataAware {
    void setRequestMsgMetadata(RequestMsgMetadata metadata);
}
