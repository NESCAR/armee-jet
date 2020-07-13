package icu.nescar.armee.jet.utils;

import icu.nescar.armee.jet.annotation.msg.req.basic.BasicField;
import icu.nescar.armee.jet.annotation.msg.req.extra.ExtraField;
import icu.nescar.armee.jet.annotation.msg.req.slice.SlicedFrom;
import icu.nescar.armee.jet.annotation.msg.resp.CommandField;
import icu.nescar.armee.jet.mata.JavaBeanFieldMetadata;
import icu.nescar.armee.jet.mata.JavaBeanMetadata;
import com.google.common.collect.Sets;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author hylexus
 * Created At 2019-09-28 8:53 下午
 */
public class JavaBeanMetadataUtils {

    private static final ConcurrentMap<Class<?>, JavaBeanMetadata> CLASS_METADATA_CACHE = new ConcurrentHashMap<>();
    /**
     * 支持按bit拆分的类型
     */
    public static final Set<Class<?>> SLICED_TYPE;

    static {
        final Set<Class<?>> set = Sets.newHashSet(
                Byte.class, byte.class,
                Short.class, short.class,
                Integer.class, int.class
        );
        SLICED_TYPE = Collections.unmodifiableSet(set);
    }

    public static boolean isSlicedType(Class<?> cls) {
        return SLICED_TYPE.contains(cls);
    }

    public static JavaBeanMetadata getBeanMetadata(Class<?> cls) {
        JavaBeanMetadata javaBeanMetadata = CLASS_METADATA_CACHE.get(cls);
        if (javaBeanMetadata != null) {
            return javaBeanMetadata;
        }

        javaBeanMetadata = buildClassMetadata(cls);

        CLASS_METADATA_CACHE.put(cls, javaBeanMetadata);
        return javaBeanMetadata;
    }

    private static JavaBeanMetadata buildClassMetadata(Class<?> cls) {
        JavaBeanMetadata javaBeanMetadata = new JavaBeanMetadata();
        javaBeanMetadata.setOriginalClass(cls);
        javaBeanMetadata.setFieldMetadataList(new ArrayList<>());
        javaBeanMetadata.setFieldMapping(new HashMap<>());
        javaBeanMetadata.setSliceFromSupportedFieldList(new ArrayList<>());

        for (Field field : cls.getDeclaredFields()) {
            Class<?> fieldType = field.getType();

            JavaBeanFieldMetadata javaBeanFieldMetadata = buildFieldMetadata(field, fieldType);
            javaBeanFieldMetadata.setRawBeanMetadata(javaBeanMetadata);
            if (javaBeanFieldMetadata.isAnnotationPresent(BasicField.class)) {
                javaBeanFieldMetadata.setOrder(javaBeanFieldMetadata.getAnnotation(BasicField.class).order());
            } else if (javaBeanFieldMetadata.isAnnotationPresent(ExtraField.class)) {
                javaBeanFieldMetadata.setOrder(javaBeanFieldMetadata.getAnnotation(ExtraField.class).order());
            } else if (javaBeanFieldMetadata.isAnnotationPresent(CommandField.class)) {
                javaBeanFieldMetadata.setOrder(javaBeanFieldMetadata.getAnnotation(CommandField.class).order());
            }

            javaBeanMetadata.getFieldMetadataList().add(javaBeanFieldMetadata);
            javaBeanMetadata.getFieldMapping().put(field.getName(), javaBeanFieldMetadata);
            if (field.getAnnotation(SlicedFrom.class) != null) {
                javaBeanMetadata.getSliceFromSupportedFieldList().add(javaBeanFieldMetadata);
            }
        }

        javaBeanMetadata.getFieldMetadataList().sort(Comparator.comparing(JavaBeanFieldMetadata::getOrder));

        return javaBeanMetadata;
    }

    private static JavaBeanFieldMetadata buildFieldMetadata(Field field, Class<?> fieldType) {
        JavaBeanFieldMetadata javaBeanFieldMetadata = new JavaBeanFieldMetadata();
        javaBeanFieldMetadata.setField(field);
        javaBeanFieldMetadata.setFieldType(fieldType);
        javaBeanFieldMetadata.setGenericType(new ArrayList<>());

        Type genericType = field.getGenericType();

        if (genericType instanceof ParameterizedType) {
            for (Type actualTypeArgument : ((ParameterizedType) genericType).getActualTypeArguments()) {
                // ignore WildcardType
                // ?, ? extends Number, or ? super Integer
                if (actualTypeArgument instanceof WildcardType) {
                    continue;
                }
                javaBeanFieldMetadata.getGenericType().add((Class<?>) actualTypeArgument);
            }
        }
        return javaBeanFieldMetadata;
    }
}
