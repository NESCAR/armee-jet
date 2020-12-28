package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.conf.ConfArguments;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerImpl;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.AxleLoadUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.BrakeEventRequestMsgBody;
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
 * @Date 2020/8/27
 * 基于注解的handler
 * 处理急刹车事件的上报
 * 返回一个终端的通用应答
 */
@Slf4j
@Jt808RequestMsgHandler
//@Component
public class BrakeEventUploadMsgHandler{
    @Jt808RequestMsgHandlerMapping(msgType = 0x0113)
    public RespMsgBody processBrakeMsg(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, BrakeEventRequestMsgBody msgBody
    ) {
        assert header.getMsgId() == Jt808MsgType.CLIENT_BRAKE_INFO_UPLOAD.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader() == header;
        Producer<KafkaMsgKey, Object> implSync = new KafkaProducerImpl<>(ConfArguments.KAFKA_TOPIC_DATA, false);
        try {
            KafkaMsgKey key = new KafkaMsgKey(session.getTerminalId(), Jt808MsgType.CLIENT_BRAKE_INFO_UPLOAD.getMsgId());
            implSync.send(key, msgBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("处理急刹车事件上报信息 terminalId = {}, msgBody = {}", header.getTerminalId(), msgBody);
        return CommonReplyMsgBody.success(header.getFlowId(), Jt808MsgType.CLIENT_BRAKE_INFO_UPLOAD);


    }
}

