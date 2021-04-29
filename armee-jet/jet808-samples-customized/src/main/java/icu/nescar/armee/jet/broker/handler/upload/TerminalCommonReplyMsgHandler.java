package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerStatic;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.MileageUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.TEBStatusRequestMsgBody;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandler;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandlerMapping;
import io.github.hylexus.jt.command.CommandWaitingPool;
import io.github.hylexus.jt.command.Jt808CommandKey;
import io.github.hylexus.jt808.handler.AbstractMsgHandler;

import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.msg.req.BuiltinTerminalCommonReplyMsgBody;
import io.github.hylexus.jt808.msg.resp.CommonReplyMsgBody;
import io.github.hylexus.jt808.session.Jt808Session;

import lombok.extern.slf4j.Slf4j;


import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @Auther whale
 * @Date 2021/3/10
 */
@Slf4j

public class TerminalCommonReplyMsgHandler extends AbstractMsgHandler<BuiltinTerminalCommonReplyMsgBody> {



    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, BuiltinTerminalCommonReplyMsgBody body, Jt808Session session) {
        log.info("收到终端通用应答 terminalId = {}, msgBody = {}", session.getTerminalId(),body);
        Jt808CommandKey commandKey=Jt808CommandKey.of(metadata.getMsgType(), metadata.getHeader().getTerminalId(), metadata.getHeader().getFlowId());
        commandKey.setFlowId(metadata.getHeader().getFlowId());
        commandKey.setMsgType(metadata.getMsgType());
        commandKey.setTerminalId(metadata.getHeader().getTerminalId());
        CommandWaitingPool.getInstance().putIfNecessary(commandKey, "result for " + commandKey.getKeyAsString());

        return Optional.empty();
    }

}

