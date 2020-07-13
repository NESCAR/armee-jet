package icu.nescar.armee.jet.data.converter;

import java.util.Set;

/**
 * 数据类型转化接口
 * @author Charles Song
 * @date 2020-6-23
 */
public interface DataTypeConverter<S, T> {

    /**
     * 源类型到目标类型的转化
     *
     * @param sourceType     源类型
     * @param targetType     目标类型
     * @param sourceInstance 源数据实例
     * @return target type
     */
    T convert(Class<S> sourceType, Class<T> targetType, S sourceInstance);

    /**
     * 获取可以转化的类型集合
     * @return 可以转化的类型集合
     */
    Set<ConvertibleMetadata> getConvertibleTypes();
}
