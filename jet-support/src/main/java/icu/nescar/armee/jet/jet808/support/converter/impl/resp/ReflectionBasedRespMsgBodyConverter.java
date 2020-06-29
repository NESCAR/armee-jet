package icu.nescar.armee.jet.jet808.support.converter.impl.resp;


import icu.nescar.armee.jet.annotation.msg.resp.Jt808RespMsgBody;
import icu.nescar.armee.jet.codec.encode.RespMsgEncoder;
import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.exception.JtIllegalArgumentException;
import icu.nescar.armee.jet.jet808.support.converter.MsgTypeParser;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RespMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.resp.AnnotationBasedCommonReplyMsgBodyWrapper;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;

import java.util.Optional;

/**
 * @author hylexus
 * Created At 2020-02-02 3:26 下午
 */
@Slf4j
public class ReflectionBasedRespMsgBodyConverter extends AbstractBuiltinRespBodyConverter {

    private final RespMsgEncoder respMsgEncoder;
    private final MsgTypeParser msgTypeParser;

    public ReflectionBasedRespMsgBodyConverter(MsgTypeParser msgTypeParser) {
        this.msgTypeParser = msgTypeParser;
        this.respMsgEncoder = new RespMsgEncoder();
    }

    @Override
    public boolean supportsMsgBody(Object msgBody) {
        final Jt808RespMsgBody annotation = AnnotationUtils.findAnnotation(msgBody.getClass(), Jt808RespMsgBody.class);
        return annotation != null;
    }

    @Override
    public Optional<RespMsgBody> convert(Object msgBody, Session session, RequestMsgMetadata metadata) {

        final MsgType respMsgType = getRespMsgType(msgBody);
        final byte[] bodyBytes;
        try {
            bodyBytes = this.respMsgEncoder.encodeRespMsgBody(msgBody);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Optional.empty();
        }

        final AnnotationBasedCommonReplyMsgBodyWrapper wrapper = new AnnotationBasedCommonReplyMsgBodyWrapper(respMsgType, bodyBytes);
        log.debug("@Jt808RespMsgBody based RespMsgBody --> {}", wrapper);
        return Optional.of(wrapper);
    }

    private MsgType getRespMsgType(Object msgBody) {
        final Jt808RespMsgBody annotation = AnnotationUtils.findAnnotation(msgBody.getClass(), Jt808RespMsgBody.class);
        assert annotation != null;

        final int msgId = annotation.respMsgId();
        return msgTypeParser.parseMsgType(msgId)
                .orElseThrow(() -> new JtIllegalArgumentException("Can not parse msgType with msgId " + msgId));
    }
}
