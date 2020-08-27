package icu.nescar.armee.jet.jet808.support.converter.impl;


import icu.nescar.armee.jet.annotation.BuiltinComponent;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.req.BuiltinTerminalCommonReplyMsgBody;
import io.github.hylexus.oaks.utils.IntBitOps;

import java.util.Optional;

/**
 * 请求消息转换器接口的其中一个实现
 * 面对的请求消息主要是终端通用应答
 *
 * @author hylexus
 * Created At 2020-02-02 11:19 上午
 */
@BuiltinComponent
public class BuiltinTerminalCommonReplyRequestMsgBodyConverter extends AbstractBuiltinRequestMsgBodyConverter<BuiltinTerminalCommonReplyMsgBody> {

    // 0,成功/确认;其他,失败;
    public static final byte RESULT_SUCCESS = 0;

    @Override
    public Optional<BuiltinTerminalCommonReplyMsgBody> convert2Entity(RequestMsgMetadata metadata) {
        final byte[] bytes = metadata.getBodyBytes();

        final BuiltinTerminalCommonReplyMsgBody body = new BuiltinTerminalCommonReplyMsgBody();
        body.setReplyFlowId(IntBitOps.intFrom2Bytes(bytes, 0));
        body.setReplyMsgId(IntBitOps.intFrom2Bytes(bytes, 2));
        body.setResult(bytes[4]);

        return Optional.of(body);
    }
}
