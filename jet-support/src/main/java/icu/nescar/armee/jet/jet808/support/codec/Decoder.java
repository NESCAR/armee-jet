package icu.nescar.armee.jet.jet808.support.codec;

import icu.nescar.armee.jet.codec.decode.FieldDecoder;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgHeader;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.support.entity.scan.RequestMsgHeaderAware;
import icu.nescar.armee.jet.jet808.support.support.entity.scan.RequestMsgMetadataAware;
import icu.nescar.armee.jet.utils.ProtocolUtils;
import io.github.hylexus.oaks.utils.BcdOps;
import io.github.hylexus.oaks.utils.Bytes;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

import static io.github.hylexus.oaks.utils.IntBitOps.intFromBytes;


/**
 * @Auther whale
 * @Date 2020/6/27
 */

@Slf4j
public class Decoder {

    public RequestMsgMetadata parseMsgMetadata(byte[] bytes) {
        final RequestMsgMetadata ret = new RequestMsgMetadata();

        // 1. 消息头 16byte 或 12byte
        final RequestMsgHeader msgHeader = this.parseMsgHeaderFromBytes(bytes);
        ret.setHeader(msgHeader);

        // 2. 消息体
        // 有子包信息,消息体起始字节后移四个字节:消息包总数(word(16))+包序号(word(16))
        final int msgBodyByteStartIndex = msgHeader.isHasSubPackage() ? 16 : 12;
        // ret.setBodyBytes(Bytes.subSequence(bytes, msgBodyByteStartIndex, msgHeader.getMsgBodyLength()));
        ret.setBodyBytes(Bytes.range(bytes, msgBodyByteStartIndex, bytes.length - 1));
        if (msgHeader.getMsgBodyLength() != ret.getBodyBytes().length) {
            log.error("parse MsgHeader error, expected(byte[2,3]) bodyLength is {}, actual : {}", msgHeader.getMsgBodyLength(), ret.getBodyBytes().length);
        }

        // 3. 去掉分隔符之后，最后一位就是校验码
        final byte checkSumInPkg = bytes[bytes.length - 1];
        ret.setCheckSum(checkSumInPkg);
        validateCheckSum(bytes, msgHeader, checkSumInPkg);
        return ret;
    }

    private void validateCheckSum(byte[] bytes, RequestMsgHeader msgHeader, byte checkSumInPkg) {
        final int calculatedCheckSum = ProtocolUtils.calculateCheckSum4Jt808(bytes, 0, bytes.length - 1);
        if (checkSumInPkg != calculatedCheckSum) {
            log.warn("检验码不一致,msgId:{},expected : {},calculated : {}", msgHeader.getMsgId(), checkSumInPkg, calculatedCheckSum);
        }
    }

    private RequestMsgHeader parseMsgHeaderFromBytes(byte[] bytes) {
        final RequestMsgHeader header = new RequestMsgHeader();

        // 1. byte[0-1]   消息ID word(16)
        header.setMsgId(intFromBytes(bytes, 0, 2));

        // 2. byte[2-3]   消息体属性 word(16)
        final int bodyProps = intFromBytes(bytes, 2, 2);
        header.setMsgBodyPropsField(bodyProps);
        // [ 0-9 ] 0000,0011,1111,1111(3FF)(消息体长度)
        header.setMsgBodyLength(bodyProps & 0x3ff);
        // [10-12] 0001,1100,0000,0000(1C00)(加密类型)
        header.setEncryptionType((bodyProps & 0x1c00) >> 10);
        // [ 13_ ] 0010,0000,0000,0000(2000)(是否有子包)
        header.setHasSubPackage(((bodyProps & 0x2000) >> 13) == 1);
        // [14-15] 1100,0000,0000,0000(C000)(保留位)
        header.setReservedBit(((bodyProps & 0xc000) >> 14));

        // 3. byte[4-9]   终端手机号或设备ID bcd[6]
        header.setTerminalId(BcdOps.bytes2BcdString(bytes, 4, 6));

        // 4. byte[10-11]     消息流水号 word(16)
        header.setFlowId(intFromBytes(bytes, 10, 2));

        // 5. byte[12-15]     消息包封装项
        if (header.isHasSubPackage()) {
            // byte[0-1]   消息包总数(word(16))
            header.setTotalSubPackage(intFromBytes(bytes, 12, 2));
            // byte[2-3]   包序号(word(16))
            header.setSubPackageSeq(intFromBytes(bytes, 14, 2));
        }
        return header;
    }

    private final FieldDecoder fieldDecoder = new FieldDecoder();

    public <T> T decodeRequestMsgBody(Class<T> cls, byte[] bytes, RequestMsgMetadata metadata)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {

        T instance = cls.newInstance();

        processAwareMethod(cls, instance, metadata);

        fieldDecoder.decode(instance, bytes);

        return instance;
    }

    private <T> void processAwareMethod(Class<T> cls, Object instance, RequestMsgMetadata metadata) {
        if (instance instanceof RequestMsgHeaderAware) {
            ((RequestMsgHeaderAware) instance).setRequestMsgHeader(metadata.getHeader());
        }

        if (instance instanceof RequestMsgMetadataAware) {
            ((RequestMsgMetadataAware) instance).setRequestMsgMetadata(metadata);
        }
    }

}
