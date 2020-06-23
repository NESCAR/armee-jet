package icu.nescar.armee.jet.core.data.converter.registry;

import icu.nescar.armee.jet.core.data.converter.ConvertibleMetadata;
import icu.nescar.armee.jet.core.data.converter.impl.*;
import icu.nescar.armee.jet.core.data.converter.DataTypeConverter;
import lombok.NonNull;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
/**
 * @author hylexus
 * Created At 2019-10-28 7:52 下午
 */
public class DefaultDataTypeConverterRegistry implements DataTypeConverterRegistry {

    private final Map<ConvertibleMetadata, DataTypeConverter<?, ?>> converterMap = new ConcurrentHashMap<>();

    public DefaultDataTypeConverterRegistry() {
        registerDefaultConverter(this);
    }

    static void registerDefaultConverter(DefaultDataTypeConverterRegistry registry) {

        registry.registerConverter(new ByteArrayToIntegerDataTypeConverter());

        registry.registerConverter(new ByteArrayToShortDataTypeConverter());

        registry.registerConverter(new ByteArrayToBcdStringDataTypeConverter());

        registry.registerConverter(new ByteArrayToStringDataConverter());

        registry.registerConverter(new ByteArrayToByteDataTypeConverter());

        registry.registerConverter(new NoOpsByteArrayDataTypeConverter());

    }

    @Override
    public <S, T> void registerConverter(Class<S> sourceType, Class<T> targetType, DataTypeConverter<? extends S, ? extends T> converter) {
        ConvertibleMetadata key = new ConvertibleMetadata(sourceType, targetType);
        converterMap.put(key, converter);
    }

    @Override
    public void registerConverter(@NonNull DataTypeConverter<?, ?> converter) {
        for (ConvertibleMetadata convertibleType : converter.getConvertibleTypes()) {
            converterMap.put(convertibleType, converter);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S, T> Optional<DataTypeConverter<S, T>> getConverter(Class<S> sourceType, Class<T> targetType) {
        DataTypeConverter<S, T> converter = (DataTypeConverter<S, T>) this.converterMap.get(new ConvertibleMetadata(sourceType, targetType));
        return Optional.ofNullable(converter);
    }

    @Override
    public Optional<DataTypeConverter<?, ?>> getConverter(ConvertibleMetadata convertibleMetadata) {
        return Optional.ofNullable(this.converterMap.get(convertibleMetadata));
    }
}
