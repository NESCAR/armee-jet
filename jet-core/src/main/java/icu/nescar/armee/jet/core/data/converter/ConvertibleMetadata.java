package icu.nescar.armee.jet.core.data.converter;

import icu.nescar.armee.jet.core.data.MsgDataType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 可转化的元数据
 * @author Charles Song
 * @date 2020-6-23
 */
@Data
@ToString(of = {"sourceClass","targetClass","sourceDataType"})
@EqualsAndHashCode(of = {"sourceClass", "sourceDataType", "targetClass"})
public class ConvertibleMetadata {

    /**
     * 源类
     */
    private Class<?> sourceClass;
    /**
     * 源数据类型
     */
    private MsgDataType sourceDataType;
    /**
     * 目标类
     */
    private Class<?> targetClass;

    public ConvertibleMetadata(Class<?> sourceClass, Class<?> targetClass) {
        this.sourceClass = sourceClass;
        this.targetClass = targetClass;
    }

    /**
     * 可转换的元数据
     * @param sourceType 源类
     * @param targetDataType 目标数据类型
     * @return 可转换的元数据
     */
    public static ConvertibleMetadata forJt808EncodeMsgDataType(Class<?> sourceType, MsgDataType targetDataType) {
        ConvertibleMetadata convertibleMetadata = new ConvertibleMetadata(sourceType, byte[].class);
        return convertibleMetadata;
    }

    /**
     * JT808的消息类型元数据
     * @param sourceDataType 源数据类型
     * @param targetType 目标类型
     * @return 消息类型元数据
     */
    public static ConvertibleMetadata forJt808MsgDataType(MsgDataType sourceDataType, Class<?> targetType) {
        ConvertibleMetadata instance = new ConvertibleMetadata(byte[].class, targetType);
        instance.setSourceDataType(sourceDataType);
        return instance;
    }
}
