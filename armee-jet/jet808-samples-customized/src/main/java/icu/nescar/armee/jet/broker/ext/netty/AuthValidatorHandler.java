//package icu.nescar.armee.jet.broker.ext.netty;
//
//import icu.nescar.armee.jet.broker.ext.auth.service.impl.AuthCodeValidatorImpl;
//import io.github.hylexus.jt808.ext.TerminalValidator;
//import io.github.hylexus.jt808.msg.RequestMsgMetadata;
//import io.github.hylexus.jt808.msg.req.BuiltinAuthRequestMsgBody;
//import io.github.hylexus.jt808.session.Jt808Session;
//import io.netty.channel.ChannelHandler;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @Auther whale
// * @Date 2021/1/22
// */
//
//@ChannelHandler.Sharable
//
//public class AuthValidatorHandler extends ChannelInboundHandlerAdapter {
//        private static final Logger log = LoggerFactory.getLogger("jt-808.channel.handler.adapter");
//        private final AuthCodeValidatorImpl authCodeValidator;
//        BuiltinAuthRequestMsgBody  authRequestMsgBody=new BuiltinAuthRequestMsgBody();
//        @Autowired
//        Jt808Session session;
//
//        public AuthValidatorHandler(AuthCodeValidatorImpl authCodeValidator) {
//            this.authCodeValidator = authCodeValidator;
//        }
//
//        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            if (msg instanceof RequestMsgMetadata) {
//                RequestMsgMetadata metadata = (RequestMsgMetadata)msg;
//
//                if (this.authCodeValidator.validateAuthCode(session,metadata,authRequestMsgBody)) {
//                    log.debug("[AuthCodeValidator] 鉴权通过");
//                    ctx.pipeline().remove("Jt808NettyAuthValidatorHandler");
//                    ctx.fireChannelRead(msg);
//                } else {
//                    log.debug("[AuthCodeValidator] 鉴权失败");
//                }
//            } else {
//                ctx.fireChannelRead(msg);
//            }
//
//        }
//    }
//
//
