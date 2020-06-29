package icu.nescar.armee.jet.jet808.support.converter.impl;



import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.req.BuiltinAuthRequestMsgBody;
import icu.nescar.armee.jet.utils.ProtocolUtils;

import java.util.Optional;

/**
 * @author hylexus
 * createdAt 2019/2/1
 * @see BuiltinAuthRequestMsgBody
 **/
@BuiltinComponent
public class BuiltinAuthRequestMsgBodyConverter extends AbstractBuiltinRequestMsgBodyConverter<BuiltinAuthRequestMsgBody> {

    @Override
    public Optional<BuiltinAuthRequestMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        byte[] bytes = metadata.getBodyBytes();
        BuiltinAuthRequestMsgBody body = new BuiltinAuthRequestMsgBody().setAuthCode(ProtocolUtils.bytes2String(bytes, 0, bytes.length));
        body.setHeader(metadata.getHeader());
        return Optional.of(body);
    }
}
