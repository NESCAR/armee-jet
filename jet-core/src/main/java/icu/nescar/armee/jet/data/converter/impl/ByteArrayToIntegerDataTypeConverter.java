package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;
import io.github.hylexus.oaks.utils.IntBitOps;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 字节数组转化为整数数据类型的转化器
 * @author Charles Song
 * @date 2020-6-23
 */
public class ByteArrayToIntegerDataTypeConverter implements Jt808MsgDataTypeConverter<Integer> {

    private static final Set<ConvertibleMetadata> CONVERTIBLE_METADATA_SET;

    static {
        Set<ConvertibleMetadata> set = new HashSet<>(6);

        // DWORD转化为Integer类型
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.DWORD, Integer.class));
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.DWORD, int.class));

        // WORD转化为Integer类型
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.WORD, Integer.class));
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.WORD, int.class));

        // BYTE转化为Integer类型
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, Integer.class));
        set.add(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BYTE, int.class));

        // 返回set的不可更改视图
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
     * @param length 长度  ∈ [1,4]
     * @return 转化后的Integer类型
     */
    @Override
    public Integer convert(byte[] bytes, int start, int length) {
        return IntBitOps.intFromBytes(bytes, start, length);
    }
}
