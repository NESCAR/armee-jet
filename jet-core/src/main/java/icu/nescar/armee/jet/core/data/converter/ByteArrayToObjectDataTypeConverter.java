package icu.nescar.armee.jet.core.data.converter;

import icu.nescar.armee.jet.core.exception.JtDataTypeConvertException;

/**
 * @author hylexus
 * Created At 2019-10-22 12:16 上午
 */
public interface ByteArrayToObjectDataTypeConverter<T> extends DataTypeConverter<byte[], T> {

    T convert(Class<byte[]> sourceType, Class<T> targetType, byte[] bytes, int startIndex, int length);

    @Override
    default T convert(Class<byte[]> sourceType, Class<T> targetType, byte[] bytes) {
        if (bytes == null) {
            throw new JtDataTypeConvertException("Can not convert " + sourceType + " to " + targetType + ", [bytes] is null");
        }
        return convert(sourceType, targetType, bytes, 0, bytes.length);
    }
}
