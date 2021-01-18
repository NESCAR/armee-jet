package icu.nescar.armee.jet.broker.handler.upload;

import icu.nescar.armee.jet.broker.config.Jt808MsgType;
import icu.nescar.armee.jet.broker.ext.producer.Producer;
import icu.nescar.armee.jet.broker.ext.producer.kafka.KafkaProducerStatic;
import icu.nescar.armee.jet.broker.ext.producer.kafka.msg.KafkaMsgKey;
import icu.nescar.armee.jet.broker.msg.req.MileageUploadRequestMsgBody;

import io.github.hylexus.jt808.handler.AbstractMsgHandler;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.RespMsgBody;
import io.github.hylexus.jt808.session.Jt808Session;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;

/**
 * @Auther whale
 * 里程信息上报处理器
 * @Date 2020/8/13
 * 目前返回的都是终端通用应答
 */


@Slf4j
public class MileageInfoUploadMsgHandler extends AbstractMsgHandler<MileageUploadRequestMsgBody>  {

    @Override
    protected Optional<RespMsgBody> doProcess(RequestMsgMetadata metadata, MileageUploadRequestMsgBody body, Jt808Session session) {
        Producer<KafkaMsgKey, Object> implSync = KafkaProducerStatic.getDataInstance();
                try {
            KafkaMsgKey key = new KafkaMsgKey(session.getTerminalId(), Jt808MsgType.CLIENT_MILEAGE_INFO_UPLOAD.getMsgId());
            implSync.send(key, body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("处理里程上报信息 terminalId = {}, msgBody = {}", session.getTerminalId(), body);
        return Optional.of(commonSuccessReply(metadata, Jt808MsgType.CLIENT_MILEAGE_INFO_UPLOAD));
    }
}
