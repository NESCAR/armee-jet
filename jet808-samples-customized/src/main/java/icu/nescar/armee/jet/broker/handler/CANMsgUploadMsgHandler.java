package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.msg.req.BrakeEventRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.CANMsgRequestMsgBody;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandler;
import io.github.hylexus.jt.annotation.msg.handler.Jt808RequestMsgHandlerMapping;
import io.github.hylexus.jt808.msg.RequestMsgHeader;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.msg.resp.CommonReplyMsgBody;
import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @Auther whale
 * @Date 2020/11/16
 * 基于注解的handler
 * CAN报文的上报
 * 返回一个终端的通用应答
 */
@Slf4j
@Jt808RequestMsgHandler
@Component
public class CANMsgUploadMsgHandler{
    @Jt808RequestMsgHandlerMapping(msgType = 0x0113)
    public RespMsgBody processCANMsg(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, CANMsgRequestMsgBody msgBody
    ) {
        assert header.getMsgId() == Jt808MsgType.CLIENT_CAN_INFO_UPLOAD.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader() == header;

        log.info("处理CAN数据上报信息 terminalId = {}, msgBody = {}", header.getTerminalId(), msgBody);
        return CommonReplyMsgBody.success(header.getFlowId(), Jt808MsgType.CLIENT_COMMON_REPLY);


    }
}
