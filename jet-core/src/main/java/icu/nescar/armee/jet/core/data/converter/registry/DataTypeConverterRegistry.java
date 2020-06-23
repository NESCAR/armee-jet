package icu.nescar.armee.jet.core.data.converter.registry;

import icu.nescar.armee.jet.core.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.core.data.converter.DataTypeConverter;
import lombok.NonNull;

import java.util.Optional;

/**
 * @author Charles Song
 * @date 2020-6-23
 */
public interface DataTypeConverterRegistry {

    /**
     * 手动添加转化器
     * @param sourceType 指定源类型
     * @param targetType 目标类型
     * @param converter 转化器
     * @param <S> 源类型
     * @param <T> 目标类型
     */
    <S, T> void registerConverter(Class<S> sourceType, Class<T> targetType, DataTypeConverter<? extends S, ? extends T> converter);

    /**
     * 自动添加转化器，和手动添加的区别是，不需要指定源类型和目标类型，将转化器内所有的类型都加入
     * @param converter 转化器
     */
    void registerConverter(@NonNull DataTypeConverter<?, ?> converter);

    /**
     * 获取数据类型转化器
     * @param sourceType 源类型
     * @param targetType 目标类型
     * @param <S> 源类型
     * @param <T> 目标类型
     * @return 数据类型转化器
     */
    <S, T> Optional<DataTypeConverter<S, T>> getConverter(Class<S> sourceType, Class<T> targetType);

    /**
     * 获取数据类型转化器
     * @param convertibleMetadata 转化器元数据，包含1. 目标类，2. 源类{@link Class}或者源数据类型{@link icu.nescar.armee.jet.core.data.MsgDataType}
     * @return 数据类型转化器
     */
    Optional<DataTypeConverter<?, ?>> getConverter(ConvertibleMetadata convertibleMetadata);
}
