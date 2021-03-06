package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerImpl;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.TEBSAcceptRequestMsgBody;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandler;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandlerMapping;
import io.github.hylexus.jt808.msg.RequestMsgHeader;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.msg.resp.CommonReplyMsgBody;
import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Auther whale
 * @Date 2020/9/7
 */
@Slf4j
@Jt808RequestMsgHandler
@Component
public class TEBSAcceptReplyMsgHandler {

    @Jt808RequestMsgHandlerMapping(msgType = 0x0114)
    public RespMsgBody processTEBSAcceptMsg(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, TEBSAcceptRequestMsgBody msgBody
    ){
        assert header.getMsgId() == Jt808MsgType.CLIENT_TEBS_ACCEPT_REPLY.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader()==header;
        Producer<KafkaMsgKey, Object> implSync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
        try {
            KafkaMsgKey key = new KafkaMsgKey(session.getTerminalId(), Jt808MsgType.CLIENT_TEBS_ACCEPT_REPLY.getMsgId());
            implSync.send(key, msgBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("处理TEBS接收应答上报信息 terminalId = {}, msgBody = {}",header.getTerminalId(),msgBody);
        return CommonReplyMsgBody.success(header.getFlowId(), Jt808MsgType.CLIENT_TEBS_ACCEPT_REPLY);

    }
}

