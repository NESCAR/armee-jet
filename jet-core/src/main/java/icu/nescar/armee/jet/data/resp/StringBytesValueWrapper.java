package icu.nescar.armee.jet.data.resp;

import icu.nescar.armee.jet.config.JtProtocolConstant;
import lombok.Value;

@Value
public class StringBytesValueWrapper implements BytesValueWrapper<String> {

    String value;

    private StringBytesValueWrapper(String value) {
        this.value = value;
    }

    public static StringBytesValueWrapper of(String value) {
        return new StringBytesValueWrapper(value);
    }

    @Override
    public byte[] getAsBytes() {
        return value.getBytes(JtProtocolConstant.JT_808_STRING_ENCODING);
    }
}