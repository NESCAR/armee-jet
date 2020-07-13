package icu.nescar.armee.jet.codec.decode;

import icu.nescar.armee.jet.annotation.msg.req.AdditionalField;
import icu.nescar.armee.jet.data.msg.AdditionalFieldInfo;
import icu.nescar.armee.jet.data.msg.AdditionalItemEntity;
import icu.nescar.armee.jet.mata.JavaBeanFieldMetadata;
import io.github.hylexus.oaks.utils.Bytes;
import io.github.hylexus.oaks.utils.IntBitOps;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author hylexus
 * Created At 2019-09-28 11:41 下午
 */
@Slf4j
public class AdditionalFieldDecoder {

    private static final ConcurrentMap<Field, Map<Integer, AdditionalFieldInfo>> FIELD_INFO_CACHE = new ConcurrentHashMap<>();
    private final SplittableFieldDecoder splittableFieldDecoder = new SplittableFieldDecoder();

    public void decodeAdditionalField(
            Object instance, byte[] bytes, int startIndex, int totalLength,
            JavaBeanFieldMetadata fieldMetadata) throws IllegalAccessException, InstantiationException {

        AdditionalField annotation = fieldMetadata.getAnnotation(AdditionalField.class);

        Map<Integer, AdditionalFieldInfo> typeMapping = getTypeMapping(fieldMetadata, annotation);

        AdditionalFieldInfo rootGroupInfo = typeMapping.values().stream()
                .filter(e -> e.getGroupMsgId() == AdditionalField.ROOT_GROUP_MSG_ID)
                .findFirst().orElseGet(() -> {
                    log.debug("Use DEFAULT_ROOT_GROUP");
                    return AdditionalFieldInfo.DEFAULT_ROOT_GROUP;
                });

        List<AdditionalItemEntity> result = new ArrayList<>();
        parseExtraMsg(result, typeMapping, bytes, startIndex, totalLength,
                rootGroupInfo.getByteCountOfMsgId(), rootGroupInfo.getByteCountOfContentLength(), AdditionalField.ROOT_GROUP_MSG_ID);

        fieldMetadata.setFieldValue(instance, result);

        splittableFieldDecoder.processSplittableField(instance, fieldMetadata, result);
    }

    private Map<Integer, AdditionalFieldInfo> getTypeMapping(JavaBeanFieldMetadata fieldMetadata, AdditionalField annotation) {
        Map<Integer, AdditionalFieldInfo> typeMapping = FIELD_INFO_CACHE.get(fieldMetadata.getField());
        if (typeMapping != null) {
            return typeMapping;
        }

        typeMapping = buildTypeMapping(annotation);
        FIELD_INFO_CACHE.put(fieldMetadata.getField(), typeMapping);
        return typeMapping;
    }

    private Map<Integer, AdditionalFieldInfo> buildTypeMapping(AdditionalField annotation) {
        Map<Integer, AdditionalFieldInfo> typeMapping = new HashMap<>();
        // <groupMsgId, info>
        for (AdditionalField.MsgTypeMapping mapping : annotation.msgTypeMappings()) {
            AdditionalFieldInfo mappingInfo = new AdditionalFieldInfo();
            mappingInfo.setGroupMsgId(mapping.groupMsgId());
            mappingInfo.setNestedAdditionalField(mapping.isNestedAdditionalField());
            mappingInfo.setByteCountOfMsgId(mapping.byteCountOfMsgId());
            mappingInfo.setByteCountOfContentLength(mapping.byteCountOfContentLength());

            typeMapping.put(mapping.groupMsgId(), mappingInfo);
        }
        return typeMapping;
    }

    private List<AdditionalItemEntity> parseExtraMsg(
            List<AdditionalItemEntity> result, Map<Integer, AdditionalFieldInfo> groupMapping,
            byte[] bytes, int startIndex, int totalLength, int byteCountOfMSgId, int byteCountOfContentLength, int groupMsgId) {

        int readerIndex = startIndex;
        while (readerIndex < totalLength) {
            final int msgId = IntBitOps.intFromBytes(bytes, readerIndex, byteCountOfMSgId);
            readerIndex += byteCountOfMSgId;

            final int contentLength = IntBitOps.intFromBytes(bytes, readerIndex, byteCountOfContentLength);
            readerIndex += byteCountOfContentLength;

            final byte[] contentBytes = Bytes.subSequence(bytes, readerIndex, contentLength);
            readerIndex += contentLength;

            AdditionalItemEntity additionalItemEntity = new AdditionalItemEntity();
            additionalItemEntity.setMsgId(msgId);
            additionalItemEntity.setGroupMsgId(groupMsgId);
            additionalItemEntity.setLength(contentLength);
            additionalItemEntity.setRawBytes(contentBytes);

            result.add(additionalItemEntity);

            AdditionalFieldInfo groupInfo;
            if (msgId != AdditionalField.ROOT_GROUP_MSG_ID
                    && (groupInfo = groupMapping.get(msgId)) != null
                    && groupInfo.isNestedAdditionalField()) {

                return parseExtraMsg(result, groupMapping, contentBytes, 0, contentBytes.length,
                        groupInfo.getByteCountOfMsgId(),
                        groupInfo.getByteCountOfContentLength(), groupInfo.getGroupMsgId());
            }

        }

        return result;
    }

}
