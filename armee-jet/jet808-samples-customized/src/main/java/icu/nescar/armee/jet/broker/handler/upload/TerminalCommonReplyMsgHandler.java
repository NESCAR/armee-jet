package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerStatic;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.TEBStatusRequestMsgBody;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandler;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandlerMapping;
import io.github.hylexus.jt.command.CommandWaitingPool;
import io.github.hylexus.jt.command.Jt808CommandKey;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import io.github.hylexus.jt808.msg.RequestMsgHeader;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.msg.resp.CommonReplyMsgBody;
import io.github.hylexus.jt808.session.Jt808Session;
import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Auther whale
 * @Date 2021/3/10
 */
@Slf4j
@Jt808RequestMsgHandler
@Component
public class TerminalCommonReplyMsgHandler {
    @Jt808RequestMsgHandlerMapping(msgType = 0x0001)
    public Optional<RespMsgBody> doProcess(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, RequestMsgBody msgBody
    ) {
        assert header.getMsgId() == Jt808MsgType.CLIENT_COMMON_REPLY.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader() == header;
        log.info("收到终端通用应答 terminalId = {}, msgBody = {}", session.getTerminalId(),msgBody);
        Jt808CommandKey commandKey=Jt808CommandKey.of(metadata.getMsgType(), metadata.getHeader().getTerminalId(), metadata.getHeader().getFlowId());
//        commandKey.setFlowId(requestMsgMetadata.getHeader().getFlowId());
//        commandKey.setMsgType(requestMsgMetadata.getMsgType());
//        commandKey.setTerminalId(requestMsgMetadata.getHeader().getTerminalId());
        CommandWaitingPool.getInstance().putIfNecessary(commandKey, "result for " + commandKey.getKeyAsString());
        return Optional.empty();



    }

}

