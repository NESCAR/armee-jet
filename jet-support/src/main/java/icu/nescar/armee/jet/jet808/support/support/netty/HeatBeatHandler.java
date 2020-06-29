package icu.nescar.armee.jet.jet808.support.support.netty;
/**
 *
 * @Auther  whale
 * @Date  2020-6-26
 */

import icu.nescar.armee.jet.jet808.support.session.Session;
import icu.nescar.armee.jet.jet808.support.session.SessionManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

import static icu.nescar.armee.jet.jet808.support.session.SessionCloseReason.IDLE_TIMEOUT;


@Slf4j
public class HeatBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (!(evt instanceof IdleStateEvent)) {
            super.userEventTriggered(ctx, evt);
            return;
        }

        if (((IdleStateEvent) evt).state() == IdleState.READER_IDLE) {
            log.debug("disconnected idle connection");
            SessionManager.getInstance().removeBySessionIdAndClose(Session.generateSessionId(ctx.channel()), IDLE_TIMEOUT);
            ctx.channel().close();
        }
    }
}
