package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.config.JtProtocolConstant;
import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

/**
 * 字节数组转化为String类型数据的转化器
 * @author Charles Song
 * @date 2020-6-23
 */
public class ByteArrayToStringDataConverter implements Jt808MsgDataTypeConverter<String> {

    /**
     * 编码方式
     */
    private final Charset charset;


    public ByteArrayToStringDataConverter() {
        // 默认采用GBK编码
        this(JtProtocolConstant.JT_808_STRING_ENCODING);
    }

    /**
     * 字节数组转化为String类型数据的转化器
     * @param charset 编码方式
     */
    public ByteArrayToStringDataConverter(Charset charset) {
        this.charset = charset;
    }

    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        // String类型可以转化为String类型
        return Collections.singleton(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.STRING, String.class));
    }
    /**
     * 将字节数组转化为String表示，也就是取字符数组的某一字节转化为Integer类型<br/>
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度  ∈ [1,2]
     * @return 转化后的Short
     */
    @Override
    public String convert(byte[] bytes, int start, int length) {
        return new String(bytes, start, length, charset);
    }
}
