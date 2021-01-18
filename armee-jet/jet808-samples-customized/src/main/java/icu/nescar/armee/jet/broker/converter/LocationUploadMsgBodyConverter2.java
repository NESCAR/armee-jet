package icu.nescar.armee.jet.broker.converter;


import icu.nescar.armee.jet.broker.msg.req.LocationUploadRequestMsgBody;
import io.github.hylexus.jt808.converter.RequestMsgBodyConverter;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;

import io.github.hylexus.oaks.utils.BcdOps;

import java.util.Optional;

import static io.github.hylexus.oaks.utils.IntBitOps.intFromBytes;

/**
 * @author hylexus
 * Created At 2019-09-24 11:12 下午
 */
public class LocationUploadMsgBodyConverter2 implements RequestMsgBodyConverter<LocationUploadRequestMsgBody> {
//将客户端请求中的字节数组byte[]转换成对应的请求消息实体类 便于操作
    @Override
    public Optional<LocationUploadRequestMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        byte[] bytes = metadata.getBodyBytes();
        LocationUploadRequestMsgBody body = new LocationUploadRequestMsgBody();
        body.setWarningFlag(intFromBytes(bytes, 0, 4));
        body.setStatus(intFromBytes(bytes, 4, 4));
        body.setLat(intFromBytes(bytes, 8, 4) * 1.0 / 100_0000);//_只是为了更好的可读性，能很快看出这是100万的数值
        body.setLng(intFromBytes(bytes, 12, 4) * 1.0 / 100_0000);
        body.setHeight((short) intFromBytes(bytes, 16, 2));
        body.setSpeed((short) intFromBytes(bytes, 18, 2));
        body.setDirection((short) intFromBytes(bytes, 20, 2));
        body.setLocationTime(BcdOps.bytes2BcdString(bytes, 22, 6));
        return Optional.of(body);//如果传递的参数body是 null，抛出异常 NullPointerException
    }

}
