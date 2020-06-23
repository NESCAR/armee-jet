package icu.nescar.armee.jet.core.data.converter;

/**
 * JT808消息类型转化接口
 * @author Charles Song
 * @date 2020-6-23
 */
public interface Jt808MsgDataTypeConverter<T> extends DataTypeConverter<byte[], T> {

    /**
     * 将字符数组转化为特定的消息类型
     * @param bytes 字符数组
     * @param start 开始下标
     * @param length 长度
     * @return 消息
     */
    T convert(byte[] bytes, int start, int length);

    @Override
    @Deprecated
    default T convert(Class<byte[]> sourceType, Class<T> targetType, byte[] sourceInstance) {
        return convert(sourceInstance, 0, sourceInstance.length);
    }
}
