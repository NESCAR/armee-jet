package icu.nescar.armee.jet.broker.converter;

import icu.nescar.armee.jet.broker.msg.comd.AuthInfoSettingsMsgBody;
import icu.nescar.armee.jet.broker.msg.req.LocationUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.msg.req.MileageUploadRequestMsgBody;
import icu.nescar.armee.jet.broker.util.SerializationUtil;
import io.github.hylexus.jt808.converter.RequestMsgBodyConverter;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.oaks.utils.BcdOps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Arrays;
import java.util.Optional;

import static io.github.hylexus.oaks.utils.IntBitOps.intFromBytes;

/**
 * @Auther whale
 * 自定义的消息处理器
 * 处理消息转换之后的请求消息实体类
 * 消息处理完成后对客户端的响应就是一个byte[] 可以通过handleMsg()方法的session参数拿到netty的channel，
 * 然后通过channel发送给客户端
 * 里程信息上报目前就设置了两个属性 一个里程 一个时间
 * @Date 2020/8/13
 */
public class MileageMsgBodyConverter implements RequestMsgBodyConverter<MileageUploadRequestMsgBody> {

    @Override
    public Optional<MileageUploadRequestMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        // 使用ByteBuf读取字节的时候注意顺序！！！ 先是里程后是BCD时间
        byte[] bytes = metadata.getBodyBytes();
        MileageUploadRequestMsgBody body = new MileageUploadRequestMsgBody();
        body.setMileage(intFromBytes(bytes, 0, 4));
        body.setMileageTime(Arrays.copyOfRange(bytes,4,10));
//        MileageUploadRequestMsgBody body = (MileageUploadRequestMsgBody) SerializationUtil.deserialize(metadata.getBodyBytes());

        return Optional.of(body);
    }

}

