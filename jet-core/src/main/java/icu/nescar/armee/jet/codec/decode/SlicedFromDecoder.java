package icu.nescar.armee.jet.codec.decode;

import icu.nescar.armee.jet.annotation.msg.req.slice.SlicedFrom;
import icu.nescar.armee.jet.mata.JavaBeanFieldMetadata;
import icu.nescar.armee.jet.mata.JavaBeanMetadata;
import icu.nescar.armee.jet.utils.JavaBeanMetadataUtils;
import io.github.hylexus.oaks.utils.Numbers;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;


/**
 * @author hylexus
 * Created At 2019-10-06 5:18 下午
 */
@Slf4j
public class SlicedFromDecoder {
    /**
     * 一次性处理当前instance的所有被 {@link SlicedFrom} 标记的Field
     * @param instance 实例
     * @throws InstantiationException 实例化错误
     * @throws IllegalAccessException 非法访问
     */
    public void processAllSlicedFromField(@NonNull Object instance) throws InstantiationException, IllegalAccessException {
        Class<?> cls = instance.getClass();
        JavaBeanMetadata beanMetadata = JavaBeanMetadataUtils.getBeanMetadata(cls);
        for (JavaBeanFieldMetadata fieldMetadata : beanMetadata.getSliceFromSupportedFieldList()) {
            SlicedFrom annotation = fieldMetadata.getAnnotation(SlicedFrom.class);
            if (annotation == null) {
                continue;
            }

            Optional<JavaBeanFieldMetadata> sourceFieldInfo = beanMetadata.findFieldMedataByName(annotation.sourceFieldName());
            if (!sourceFieldInfo.isPresent()) {
                log.error("No field found with name {} on {}", annotation.sourceFieldName(), cls);
                continue;
            }

            JavaBeanFieldMetadata sourceFieldMetadata = sourceFieldInfo.get();
            if (!JavaBeanMetadataUtils.isSlicedType(sourceFieldMetadata.getFieldType())) {
                log.error("A field that to be spliced should have a type in {} --> {} ", JavaBeanMetadataUtils.SLICED_TYPE, sourceFieldMetadata.getField());
                continue;
            }
            Object sourceValue = sourceFieldMetadata.getFieldValue(instance, false);
            if (sourceValue == null) {
                log.debug("source value is null, skip @SlicedFrom processing on {}", fieldMetadata.getField());
                continue;
            }
            int intValue = ((Number) sourceValue).intValue();

            setFieldValue(instance, fieldMetadata, annotation, intValue);
        }
    }

    private void setFieldValue(Object instance, JavaBeanFieldMetadata fieldMetadata, SlicedFrom annotation, int intValue) throws IllegalAccessException {
        if (annotation.bitIndex() != SlicedFrom.DEFAULT_BIT_INDEX) {
            int sliceValue = Numbers.getBitAt(intValue, annotation.bitIndex());
            setValue(instance, fieldMetadata, sliceValue);
        } else {
            if (annotation.startBitIndex() == SlicedFrom.DEFAULT_BIT_INDEX || annotation.endBitIndex() == SlicedFrom.DEFAULT_BIT_INDEX) {
                log.error("SliceFrom.startBitIndex() or SliceFrom.endBitIndex() is null, skip @SliceFrom processing on {}", fieldMetadata.getField());
                return;
            }
            int sliceValue = Numbers.getBitRangeAsInt(intValue, annotation.startBitIndex(), annotation.endBitIndex());
            setValue(instance, fieldMetadata, sliceValue);
        }
    }

    private void setValue(Object instance, JavaBeanFieldMetadata fieldMetadata, int targetValue) throws IllegalAccessException {
        if (fieldMetadata.isIntType()) {
            fieldMetadata.setFieldValue(instance, targetValue);
        } else if (fieldMetadata.isBooleanType()) {
            fieldMetadata.setFieldValue(instance, targetValue == 1);
        }
    }
}
