package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerStatic;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.LocationUploadRequestMsgBody;
import io.github.hylexus.jt808.handler.AbstractMsgHandler;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.session.Jt808Session;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-09-19 11:31 下午
 * 位置上报信息的消息处理器
 * 会返回一个通用应答
 */
@Slf4j
public class LocationInfoUploadMsgHandler extends AbstractMsgHandler<LocationUploadRequestMsgBody> {

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, LocationUploadRequestMsgBody body, Jt808Session session) {

        Producer<KafkaMsgKey, Object> implSync = KafkaProducerStatic.getDataInstance();
                        try {
                KafkaMsgKey key = new KafkaMsgKey(session.getTerminalId(), Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD.getMsgId());
                implSync.send(key, body);
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info("处理位置上报信息 terminalId = {}, msgBody = {}", session.getTerminalId(), body);
            //返回平台通用应答 ：结果（成功即结果为0） 流水号（终端号）和发来的对应消息id三个数据}
            return Optional.of(commonSuccessReply(metadata, Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD));//Optional.of（t）返回一个t的非空值
            }
}
