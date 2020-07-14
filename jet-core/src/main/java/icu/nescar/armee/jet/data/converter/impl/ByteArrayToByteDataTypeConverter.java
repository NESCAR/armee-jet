package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 字节数组转化为Byte数据类型类型数据的转化器
 * @author Charles Song
 * @date 2020-6-23
 */
public class ByteArrayToByteDataTypeConverter implements Jt808MsgDataTypeConverter<Byte> {

    private static final Set<ConvertibleMetadata> CONVERTIBLE_METADATA_SET;

    static {
        Set<ConvertibleMetadata> tmp = new HashSet<>();
        tmp.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, Byte.class));
        tmp.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, byte.class));
        CONVERTIBLE_METADATA_SET = Collections.unmodifiableSet(tmp);
    }
    /**
     * 将字节数组转化为字符表示，也就是取字符数组的某一字节<br/>
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度，无作用
     * @return 转化后的Byte类型，也就是bytes[start]
     */
    @Override
    public Byte convert(byte[] bytes, int start, int length) {
        return bytes[start];
    }

    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        return CONVERTIBLE_METADATA_SET;
    }
}
