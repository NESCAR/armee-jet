package icu.nescar.armee.jet.jet808.support.converter;

import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.support.OrderedComponent;


import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2018/12/28
 **/
public interface RequestMsgBodyConverter<E extends RequestMsgBody> extends OrderedComponent {

    Optional<E> convert2Entity(RequestMsgMetadata metadata);

}
