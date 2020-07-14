package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;
import io.github.hylexus.oaks.utils.BcdOps;

import java.util.Collections;
import java.util.Set;

/**
 * 字节数组转化为BCD格式的String类型数据的转化器
 * @author Charles Song
 * @date 2020-6-23
 */
public class ByteArrayToBcdStringDataTypeConverter implements Jt808MsgDataTypeConverter<String> {

    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        // 源类型为BCD，目标类型为String
        return Collections.singleton(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.BCD, String.class));
    }

    /**
     * 将字节数组转化为字符串表示<br/>
     * <pre>
     *     举例：
     *     Byte Array : [1, 2, 3, 4, 5, 6]
     *     Bcd String : 10203040506
     * </pre>
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度
     * @return BCD的String
     */
    @Override
    public String convert(byte[] bytes, int start, int length) {
        return BcdOps.bytes2BcdString(bytes, start, length);
    }

}
