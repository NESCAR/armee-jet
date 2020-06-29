package icu.nescar.armee.jet.jet808.support.handler.impl;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.handler.AbstractMsgHandler;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

/**
 * 无需回复的消息处理器
 *
 * @author hylexus
 * Created At 2019-08-26 8:54 下午
 */
@Slf4j
@BuiltinComponent
public class BuiltInNoReplyMsgHandler extends AbstractMsgHandler<RequestMsgBody> {

    @Override
    public int getOrder() {
        return BUILTIN_COMPONENT_ORDER;
    }

    @Override
    public Set<MsgType> getSupportedMsgTypes() {
        return Collections.singleton(BuiltinJt808MsgType.CLIENT_COMMON_REPLY);
    }

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, RequestMsgBody msg, Session session) {
        log.debug("No reply for {}", metadata.getMsgType());
        return Optional.empty();
    }
}
