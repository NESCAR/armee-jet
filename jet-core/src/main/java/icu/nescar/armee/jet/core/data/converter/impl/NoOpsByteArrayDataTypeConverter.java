package icu.nescar.armee.jet.core.data.converter.impl;

import icu.nescar.armee.jet.core.data.MsgDataType;
import icu.nescar.armee.jet.core.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.core.data.converter.Jt808MsgDataTypeConverter;
import io.github.hylexus.oaks.utils.Bytes;

import java.util.Collections;
import java.util.Set;

/**
 * 字节数组的子数组
 * @author Charles Song
 * @date 2020-6-23
 */
public class NoOpsByteArrayDataTypeConverter implements Jt808MsgDataTypeConverter<byte[]> {

    /**
     * 字符数组截取子数组
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度
     * @return 子字节数组
     */
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
