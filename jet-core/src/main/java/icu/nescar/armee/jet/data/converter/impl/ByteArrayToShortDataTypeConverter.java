package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;
import io.github.hylexus.oaks.utils.IntBitOps;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 字节数组转化为Short类型数据的转化器
 * @author Charles Song
 * @date 2020-6-23
 */
public class ByteArrayToShortDataTypeConverter implements Jt808MsgDataTypeConverter<Short> {

    private static final Set<ConvertibleMetadata> CONVERTIBLE_METADATA_SET;

    static {
        Set<ConvertibleMetadata> set = new HashSet<>(4);

        // WORD可以转化为Short类型
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.WORD, Short.class));
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.WORD, short.class));

        // BYTE可以转化为Short类型
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, Short.class));
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, short.class));

        CONVERTIBLE_METADATA_SET = Collections.unmodifiableSet(set);
    }


    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        return CONVERTIBLE_METADATA_SET;
    }
    /**
     * 将字节数组转化为Integer表示，也就是取字符数组的某一字节转化为Integer类型<br/>
     * <pre>
     *     举例：
     *     bytes = [1, 2]
     *     start = 0
     *     length = 2
     *     result = 1 * 256 + 2 * 1
     * </pre>
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度  ∈ [1,2]
     * @return 转化后的Short
     */
    @Override
    public Short convert(byte[] bytes, int start, int length) {
        // IntBitOps.intFromBytes的length可以输入3和4,但是返回的数值会被强制转化为short
        return (short) IntBitOps.intFromBytes(bytes, start, length);
    }
}
