package icu.nescar.armee.jet.broker.handler;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.msg.req.LocationUploadRequestMsgBody;
import io.github.hylexus.jt808.handler.AbstractMsgHandler;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;

import io.github.hylexus.jt808.session.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-09-19 11:31 下午
 * 位置上报信息的消息处理器
 * 会返回一个通用应答
 */
@Slf4j
public class LocationInfoUploadMsgHandler extends AbstractMsgHandler<LocationUploadRequestMsgBody>  {

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, LocationUploadRequestMsgBody body, Session session) {

        log.info("{}", body);
        return Optional.of(commonSuccessReply(metadata, Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD));//Optional.of（t）返回一个t的非空值
    //返回通用应答 包含result 流水号和消息类型三个数据
    }
}
