package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.LockUploadRequestMsgBody;
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
 * 上锁状态上报的消息处理器
 */
@Slf4j
@Jt808RequestMsgHandler
@Component
public class LockUploadMsgHandler {
    @Jt808RequestMsgHandlerMapping(msgType = 0x0116)
    public RespMsgBody processLockMsg(
            Session session, RequestMsgMetadata metadata,
            RequestMsgHeader header, LockUploadRequestMsgBody msgBody
    ){
        assert header.getMsgId() == Jt808MsgType.CLIENT_LOCK_INFO_UPLOAD.getMsgId();
        assert session.getTerminalId().equals(header.getTerminalId());
        assert session.getTerminalId().equals(metadata.getHeader().getTerminalId());
        assert metadata.getHeader()==header;

        log.info("处理上锁状态上报信息 terminalId = {}, msgBody = {}",header.getTerminalId(),msgBody);
        return CommonReplyMsgBody.success(header.getFlowId(), Jt808MsgType.CLIENT_LOCK_INFO_UPLOAD);

    }
}

