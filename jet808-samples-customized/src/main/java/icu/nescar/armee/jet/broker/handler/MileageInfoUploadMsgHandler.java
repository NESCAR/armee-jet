package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;

import icu.nescar.armee.jet.broker.msg.req.MileageUploadRequestMsgBody;

import io.github.hylexus.jt808.handler.AbstractMsgHandler;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @Auther whale
 * 里程信息上报处理器
 * @Date 2020/8/13
 * 目前返回的都是终端通用应答
 */


@Slf4j
public class MileageInfoUploadMsgHandler extends AbstractMsgHandler<MileageUploadRequestMsgBody> {

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, MileageUploadRequestMsgBody body, Session session) {

        log.info("{}", body);
        return Optional.of(commonSuccessReply(metadata, Jt808MsgType.CLIENT_COMMON_REPLY));
    }
}
