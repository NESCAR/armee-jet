package icu.nescar.armee.jet.data.converter.impl;

import icu.nescar.armee.jet.config.JtProtocolConstant;
import icu.nescar.armee.jet.data.MsgDataType;
import icu.nescar.armee.jet.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.data.converter.Jt808MsgDataTypeConverter;

import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;

/**
 * @author hylexus
 * Created At 2019-10-22 12:08 上午
 */
public class ByteArrayToStringDataConverter implements Jt808MsgDataTypeConverter<String> {

    private final Charset charset;

    public ByteArrayToStringDataConverter() {
        this(JtProtocolConstant.JT_808_STRING_ENCODING);
    }

    public ByteArrayToStringDataConverter(Charset charset) {
        this.charset = charset;
    }

    @Override
    public Set<ConvertibleMetadata> getConvertibleTypes() {
        return Collections.singleton(ConvertibleMetadata.forJt808MsgDataType(MsgDataType.STRING, String.class));
    }

    @Override
    public String convert(byte[] bytes, int start, int length) {
        return new String(bytes, start, length, charset);
    }
}
