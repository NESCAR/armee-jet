package icu.nescar.armee.jet.broker.converter;

import io.github.hylexus.jt808.converter.RequestMsgBodyConverter;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.oaks.utils.BcdOps;

import java.util.Optional;

import static io.github.hylexus.oaks.utils.IntBitOps.intFromBytes;

/**
 * @Auther whale
 * @Date 2021/3/17
 */

public class LocationMsgBodyConverterimplements RequestMsgBodyConverter<LocationUploadRequestMsgBody> {

    @Override
    public Optional<LocationUploadRequestMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        byte[] bytes = metadata.getBodyBytes();
        LocationUploadRequestMsgBody body = new LocationUploadRequestMsgBody();
        body.setWarningFlag(intFromBytes(bytes, 0, 4));
        body.setStatus(intFromBytes(bytes, 4, 4));
        body.setLat(intFromBytes(bytes, 8, 4) * 1.0 / 100_0000);
        body.setLng(intFromBytes(bytes, 12, 4) * 1.0 / 100_0000);
        body.setHeight((short) intFromBytes(bytes, 16, 2));
        body.setSpeed((short) intFromBytes(bytes, 18, 2));
        body.setDirection((short) intFromBytes(bytes, 20, 2));
        body.setTime(BcdOps.bytes2BcdString(bytes, 22, 6));
        return Optional.of(body);
    }

}

