package icu.nescar.armee.jet.jet808.support.converter.impl;



import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.jet808.support.converter.RequestMsgBodyConverter;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.req.BuiltinEmptyRequestMsgBody;

import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/2/5
 * @see BuiltinJt808MsgType#CLIENT_HEART_BEAT
 **/
@BuiltinComponent
public class BuiltinEmptyRequestMsgBodyConverter implements RequestMsgBodyConverter<BuiltinEmptyRequestMsgBody> {

    @Override
    public Optional<BuiltinEmptyRequestMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        return Optional.of(new BuiltinEmptyRequestMsgBody());
    }
}
