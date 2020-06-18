package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;
import io.github.hylexus.oaks.utils.Bytes;

import java.util.Collections;
import java.util.Set;

/**
 * @author hylexus
 * Created At 2019-10-28 10:27 下午
 */
public class NoOpsByteArrayDataTypeConverter implements Jt808MsgDataTypeConverter<byte[]> {

    @Override
    public byte[] convert(byte[] bytes, int start, int length) {
        if (start == 0 && length == bytes.length) {
            return bytes;
        }
        return Bytes.subSequence(bytes, start, length);
    }

    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        return Collections.singleton(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTES, byte[].class));
    }
}
