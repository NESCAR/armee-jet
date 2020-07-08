package icu.nescar.armee.jet.jet808.support.support.netty;

import icu.nescar.armee.jet.data.msg.MsgType;
import icu.nescar.armee.jet.jet808.support.codec.BytesEncoder;
import icu.nescar.armee.jet.jet808.support.codec.Decoder;
import icu.nescar.armee.jet.jet808.support.converter.MsgTypeParser;
import icu.nescar.armee.jet.jet808.support.dispatcher.RequestMsgDispatcher;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgHeader;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgWrapper;
import icu.nescar.armee.jet.jet808.support.session.SessionManager;
import icu.nescar.armee.jet.utils.HexStringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static icu.nescar.armee.jet.jet808.support.session.Session.generateSessionId;
import static icu.nescar.armee.jet.jet808.support.session.SessionCloseReason.CHANNEL_INACTIVE;
import static icu.nescar.armee.jet.jet808.support.session.SessionCloseReason.SERVER_EXCEPTION_OCCURRED;
import static io.netty.util.ReferenceCountUtil.release;


/**
 * @Auther whale
 * @Date 2020/6/27
 */

@Slf4j(topic = "jt-808.channel.handler.adapter")
//sharable 标记说明这个实例可以被多个channel共享
@ChannelHandler.Sharable
//inbound这个类用来接收入站事件和数据
public class Jt808ChannelHandlerAdapter extends ChannelInboundHandlerAdapter {

    private final Decoder decoder;
    private final RequestMsgDispatcher msgDispatcher;
    private final MsgTypeParser msgTypeParser;
    private final BytesEncoder bytesEncoder;

    public Jt808ChannelHandlerAdapter(RequestMsgDispatcher msgDispatcher, MsgTypeParser msgTypeParser, BytesEncoder bytesEncoder) {
        this.decoder = new Decoder();
        this.msgDispatcher = msgDispatcher;
        this.msgTypeParser = msgTypeParser;
        this.bytesEncoder = bytesEncoder;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            final ByteBuf buf = (ByteBuf) msg;
            if (buf.readableBytes() <= 0) {
                return;
            }

            final byte[] unescaped = new byte[buf.readableBytes()];
            buf.readBytes(unescaped);
            log.debug("\nreceive msg:");
            log.debug(">>>>>>>>>>>>>>> : {}", HexStringUtils.bytes2HexString(unescaped));
            final byte[] escaped = this.bytesEncoder.doEscapeForReceive(unescaped, 0, unescaped.length);
            log.debug("[escaped] : {}", HexStringUtils.bytes2HexString(escaped));

            final RequestMsgMetadata metadata = decoder.parseMsgMetadata(escaped);
            final RequestMsgHeader header = metadata.getHeader();
            final int msgId = header.getMsgId();
            final Optional<MsgType> msgType = this.msgTypeParser.parseMsgType(msgId);
            if (!msgType.isPresent()) {
                log.warn("received unknown msg, msgId = {}({}). ignore.", msgId, HexStringUtils.int2HexString(msgId, 4));
                return;
            }
            metadata.setMsgType(msgType.get());

            final String terminalId = header.getTerminalId();
            SessionManager.getInstance().persistenceIfNecessary(terminalId, ctx.channel());
            log.debug("[decode] : {}, terminalId={}, msg = {}", msgType.get(), terminalId, metadata);

            RequestMsgWrapper requestMsgWrapper = new RequestMsgWrapper().setMetadata(metadata);
            this.msgDispatcher.doDispatch(requestMsgWrapper);
        } catch (InvocationTargetException e) {
            // TODO exception handler
            log.error("InvocationTargetException", e);
            throw e;
        } catch (Throwable throwable) {
            // TODO exception handler
            log.error(throwable.getMessage(), throwable);
            throw throwable;
        } finally {
            release(msg);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        SessionManager.getInstance().removeBySessionIdAndClose(generateSessionId(ctx.channel()), SERVER_EXCEPTION_OCCURRED);
        super.exceptionCaught(ctx, cause);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.warn("remove session, channelInactive [Jt808ChannelHandlerAdapter]");
        SessionManager.getInstance().removeBySessionIdAndClose(generateSessionId(ctx.channel()), CHANNEL_INACTIVE);
        super.channelInactive(ctx);
    }

}
