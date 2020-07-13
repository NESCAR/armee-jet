package icu.nescar.armee.jet.jet808.support.support.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author hylexus
 * createdAt 2018/12/28
 **/
public class Jt808NettyChildHandlerInitializer extends ChannelInitializer<SocketChannel> {

    private final Jt808ChannelHandlerAdapter jt808ChannelHandlerAdapter;
    private final icu.nescar.armee.jet.jet808.support.support.netty.Jt808ServerConfigure serverConfigure;

    public Jt808NettyChildHandlerInitializer(icu.nescar.armee.jet.jet808.support.support.netty.Jt808ServerConfigure serverConfigure, Jt808ChannelHandlerAdapter msgDispatcher) {
        this.serverConfigure = serverConfigure;
        this.jt808ChannelHandlerAdapter = msgDispatcher;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        serverConfigure.configureSocketChannel(ch, jt808ChannelHandlerAdapter);
    }
}
