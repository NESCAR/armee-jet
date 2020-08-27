package icu.nescar.armee.jet.jet808.support.converter;

import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.support.OrderedComponent;


import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2018/12/28
 * 请求消息的消息转换器接口 功能：从客户端接收消息时，将字节数组映射成成消息实体类
 **/
public interface RequestMsgBodyConverter<E extends RequestMsgBody> extends OrderedComponent {

    Optional<E> convert2Entity(RequestMsgMetadata metadata);

}
