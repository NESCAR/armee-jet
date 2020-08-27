package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.msg.req.LocationUploadRequestMsgBody;
import io.github.hylexus.jt808.handler.AbstractMsgHandler;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;

import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-09-19 11:31 下午
 */
@Slf4j
public class LocationInfoUploadMsgHandler extends AbstractMsgHandler<LocationUploadRequestMsgBody> {

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, LocationUploadRequestMsgBody body, Session session) {

        log.info("{}", body);
        return Optional.of(commonSuccessReply(metadata, Jt808MsgType.CLIENT_COMMON_REPLY));//Optional.of（t）返回一个t的非空值
    //to do返回一个通用的针对位置上报信息的成功回答
    }
}
