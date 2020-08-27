package icu.nescar.armee.jet.broker.config;


import icu.nescar.armee.jet.broker.converter.LocationUploadMsgBodyConverter2;
import icu.nescar.armee.jet.broker.converter.MileageMsgBodyConverter;
import icu.nescar.armee.jet.broker.handler.LocationInfoUploadMsgHandler;
import icu.nescar.armee.jet.broker.handler.MileageInfoUploadMsgHandler;
import io.github.hylexus.jt.exception.MsgEscapeException;
import io.github.hylexus.jt808.codec.BytesEncoder;
import io.github.hylexus.jt808.converter.MsgTypeParser;
import io.github.hylexus.jt808.ext.AuthCodeValidator;

import io.github.hylexus.jt808.support.MsgHandlerMapping;
import io.github.hylexus.jt808.support.RequestMsgBodyConverterMapping;
import io.github.hylexus.jt808.support.netty.Jt808ChannelHandlerAdapter;
import io.github.hylexus.jt808.support.netty.Jt808ServerConfigure;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author hylexus
 * Created At 2019-09-22 3:43 下午
 * 添加消息转换器、处理器
 */
@Slf4j//生成log日志的注解
@Configuration//spring框架的注解 说明这个类作为一个IoC容器
public class Jt808Config extends Jt808ServerConfigure {
//覆盖默认逻辑 netty的相关配置
@Override
/*ServerBootstrap服务器端的引导类，绑定到一个本地端口，且需要两组不同的channel
* 一组用来代表服务器自身的已绑定到某个本地端口的正在监听的套接字
* 第二组包含所有已创建的用来处理传入客户端连接的channel*/
    public void configureServerBootstrap(ServerBootstrap serverBootstrap) {
        super.configureServerBootstrap(serverBootstrap);
    }

    @Override
    public void configureSocketChannel(SocketChannel ch, Jt808ChannelHandlerAdapter jt808ChannelHandlerAdapter) {
        super.configureSocketChannel(ch, jt808ChannelHandlerAdapter);
    }

    @Override
    /*手动将解析位置消息的转换器注册进去,还注册了新的里程信息转换器*/
    public void configureMsgConverterMapping(RequestMsgBodyConverterMapping mapping) {
        super.configureMsgConverterMapping(mapping);
        mapping.registerConverter(Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD, new LocationUploadMsgBodyConverter2());
        mapping.registerConverter(Jt808MsgType.CLIENT_MILEAGE_INFO_UPLOAD, new MileageMsgBodyConverter());
    }

    @Override
    /*手动将解析xx消息的处理器注册进去，同上
    * 如果自定义了鉴权消息处理器，并在此处注册，那么AuthCodeValidator也不需要提供了
    * 也可以从Spring容器中获取bean来注入，不一定要手动new一个handler注册*/
    public void configureMsgHandlerMapping(MsgHandlerMapping mapping) {
        super.configureMsgHandlerMapping(mapping);
        mapping.registerHandler(Jt808MsgType.CLIENT_LOCATION_INFO_UPLOAD, new LocationInfoUploadMsgHandler());
        mapping.registerHandler(Jt808MsgType.CLIENT_MILEAGE_INFO_UPLOAD, new MileageInfoUploadMsgHandler());
    }

    @Override
    public BytesEncoder supplyBytesEncoder() {
        return new BytesEncoder() {

            private final BytesEncoder bytesEncoder = new BytesEncoder.DefaultBytesEncoder();

            @Override
            public byte[] doEscapeForReceive(byte[] bytes, int start, int end) throws MsgEscapeException {
                return bytesEncoder.doEscapeForReceive(bytes, start, end);
            }

            @Override
            public byte[] doEscapeForSend(byte[] bytes, int start, int end) throws MsgEscapeException {
                return bytesEncoder.doEscapeForSend(bytes, start, end);
            }
        };
    }

    @Override
    public AuthCodeValidator supplyAuthCodeValidator() {
        return (session, requestMsgMetadata, authRequestMsgBody) -> {
            final String terminalId = session.getTerminalId();
            final String authCode = authRequestMsgBody.getAuthCode();
            // 从其他服务验证鉴权码是否正确
            log.info("AuthCode validate for terminal : {} with authCode : {}, result: {}", terminalId, authCode, true);
            return true;
        };
    }

    @Override
    public MsgTypeParser supplyMsgTypeParser() {
        return new Jt808MsgTypeParser();
    }

}
