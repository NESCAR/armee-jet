package icu.nescar.armee.jet.jet808.support.handler;


import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.codec.BytesEncoder;
import icu.nescar.armee.jet.jet808.support.codec.Encoder;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.CommonReplyMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import icu.nescar.armee.jet.jet808.support.support.handler.scan.BytesEncoderAware;
import icu.nescar.armee.jet.jet808.support.utils.ClientUtils;
import icu.nescar.armee.jet.utils.HexStringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-08-24 15:45
 */
@Slf4j(topic = "jt-808.msg.req.handler.abstract-msg-handler")
public abstract class AbstractMsgHandler<T extends RequestMsgBody> implements MsgHandler<T>, BytesEncoderAware {

    // Lazy-init until BytesEncoderAware.setBytesEncoder() method invoked
    protected Encoder encoder;

    @Override
    public void setBytesEncoder(BytesEncoder bytesEncoder) {
        log.info("Binding BytesEncoder [{}] to MsgHandler [{}]", bytesEncoder, this);
        this.encoder = new Encoder(bytesEncoder);
    }

    @Override
    public void handleMsg(RequestMsgMetadata metadata, T body, Session session) throws IOException, InterruptedException {
        final Optional<RespMsgBody> respInfo = this.doProcess(metadata, body, session);
        if (!respInfo.isPresent()) {
            log.debug("MsgHandler return empty(). [SendResult2Client] canceled.");
            return;
        }

        final RespMsgBody respBody = respInfo.get();
        byte[] respBytes = this.encoder.encodeRespMsg(respBody, session.getCurrentFlowId(), metadata.getHeader().getTerminalId());
        this.send2Client(session, respBytes);

        log.debug("<<<<<<<<<<<<<<< : {}", HexStringUtils.bytes2HexString(respBytes));
    }

    protected void send2Client(Session session, byte[] bytes) throws InterruptedException {
        ClientUtils.sendBytesToClient(session, bytes);
    }

    protected abstract Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, T msg, Session session);

    protected RespMsgBody commonSuccessReply(RequestMsgMetadata metadata, MsgType replyFor) {
        return CommonReplyMsgBody.success(metadata.getHeader().getFlowId(), replyFor);
    }
}
