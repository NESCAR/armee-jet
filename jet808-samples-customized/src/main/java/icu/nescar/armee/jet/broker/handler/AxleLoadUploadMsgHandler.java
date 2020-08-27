package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.annotation.msg.handler.Jt808RequestMsgHandler;
import icu.nescar.armee.jet.annotation.msg.handler.Jt808RequestMsgHandlerMapping;
import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.msg.req.AxleLoadUploadRequestMsgBody;
import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgHeader;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.CommonReplyMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Auther whale
 * @Date 2020/8/26
 * 基于注解的请求消息处理器注册
 * 轴负载信息的消息处理器
 * 目前返回的都是终端通用应答
 */
@Slf4j
@Jt808RequestMsgHandler
@Component
public class AxleLoadUploadMsgHandler {
    @Jt808RequestMsgHandlerMapping(msgType = 0x0109)
    public RespMsgBody processAxleMsg(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, AxleLoadUploadRequestMsgBody msgBody
            ){
        assert header.getMsgId() == Jt808MsgType.CLIENT_AXLE_LOAD_INFO_UPLOAD.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader()==header;

        log.info("处理轴负载上报信息 terminalId = {}, msgBody = {}",header.getTerminalId(),msgBody);
        return CommonReplyMsgBody.success(header.getFlowId(), Jt808MsgType.CLIENT_COMMON_REPLY);
//这为什么会出错呢 虽然要求的是msgtype但是jt808是实现msgtype的一类呀

    }
}

