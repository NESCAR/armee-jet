package icu.nescar.armee.jet.jet808.support.handler.impl;

import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.ext.AuthCodeValidator;
import icu.nescar.armee.jet.jet808.support.handler.AbstractMsgHandler;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.req.BuiltinAuthRequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.CommonReplyMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static icu.nescar.armee.jet.jet808.support.msg.RespMsgBody.AUTH_CODE_ERROR;
import static java.util.Optional.of;

/**
 * @author hylexus
 * Created At 2019-08-24 15:44
 */
@Slf4j
@BuiltinComponent
public class BuiltinAuthMsgHandler extends AbstractMsgHandler<BuiltinAuthRequestMsgBody> {

    private final AuthCodeValidator authCodeValidator;

    @Override
    public Set<MsgType> getSupportedMsgTypes() {
        return Collections.singleton(BuiltinJt808MsgType.CLIENT_AUTH);
    }

    public BuiltinAuthMsgHandler(AuthCodeValidator authCodeValidator) {
        this.authCodeValidator = authCodeValidator;
    }

    @Override
    public int getOrder() {
        return BUILTIN_COMPONENT_ORDER;
    }

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, BuiltinAuthRequestMsgBody body, Session session) {
        log.debug("receive AuthMsg : {}", body);
        boolean valid = authCodeValidator.validateAuthCode(session, metadata, body);
        if (valid) {
            return of(commonSuccessReply(metadata, BuiltinJt808MsgType.CLIENT_AUTH));
        }
        return of(CommonReplyMsgBody.of(AUTH_CODE_ERROR, metadata.getHeader().getFlowId(), BuiltinJt808MsgType.CLIENT_AUTH));
    }
}
