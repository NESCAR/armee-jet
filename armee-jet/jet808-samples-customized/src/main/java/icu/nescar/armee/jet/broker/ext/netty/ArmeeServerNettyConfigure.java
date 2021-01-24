//package icu.nescar.armee.jet.broker.ext.netty;
//
//import io.github.hylexus.jt808.support.netty.*;
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.buffer.ByteBuf;
//import io.netty.buffer.Unpooled;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.handler.codec.DelimiterBasedFrameDecoder;
//import io.netty.handler.timeout.IdleStateHandler;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @Auther whale
// * @Date 2021/1/22
// * 在netty中加了鉴权验证handler 但因为和原有的逻辑有点混乱 所以暂且不用
// */
//public class ArmeeServerNettyConfigure implements Jt808ServerNettyConfigure {
//
//    private final HeatBeatHandler heatBeatHandler;
//    private final Jt808DecodeHandler decodeHandler;
//    private final TerminalValidatorHandler terminalValidatorHandler;
//    private final AuthValidatorHandler authValidatorHandler;
//    private final Jt808ChannelHandlerAdapter jt808ChannelHandlerAdapter;
//
//    public ArmeeServerNettyConfigure(HeatBeatHandler heatBeatHandler, Jt808DecodeHandler decodeHandler, TerminalValidatorHandler terminalValidatorHandler,AuthValidatorHandler authValidatorHandler, Jt808ChannelHandlerAdapter jt808ChannelHandlerAdapter) {
//        this.heatBeatHandler = heatBeatHandler;
//        this.decodeHandler = decodeHandler;
//        this.terminalValidatorHandler = terminalValidatorHandler;
//        this.authValidatorHandler=authValidatorHandler;
//        this.jt808ChannelHandlerAdapter = jt808ChannelHandlerAdapter;
//    }
//
//    @Override
//    public void configureServerBootstrap(ServerBootstrap serverBootstrap) {
//        ((ServerBootstrap)((ServerBootstrap)serverBootstrap.option(ChannelOption.SO_BACKLOG, 2048)).option(ChannelOption.SO_REUSEADDR, true)).childOption(ChannelOption.SO_KEEPALIVE, true);
//    }
//
//    @Override
//    public void configureSocketChannel(SocketChannel ch) {
//            ch.pipeline().addLast("Jt808NettyIdleStateHandler", new IdleStateHandler(20L, 20L, 20L, TimeUnit.MINUTES));
//            ch.pipeline().addLast("Jt808NettyHeartBeatHandler", this.heatBeatHandler);
//            ch.pipeline().addLast("Jt808NettyHandler", new DelimiterBasedFrameDecoder(1024, new ByteBuf[]{Unpooled.copiedBuffer(new byte[]{126}), Unpooled.copiedBuffer(new byte[]{126, 126})}));
//            ch.pipeline().addLast("Jt808NettyDecodeHandler", this.decodeHandler);
//            ch.pipeline().addLast("Jt808NettyTerminalValidatorHandler", this.terminalValidatorHandler);
//            ch.pipeline().addLast("Jt808NettyAuthValidatorHandler", this.authValidatorHandler);
//            ch.pipeline().addLast("Jt808NettyHandlerAdapter", this.jt808ChannelHandlerAdapter);
//
//    }
//}
//
