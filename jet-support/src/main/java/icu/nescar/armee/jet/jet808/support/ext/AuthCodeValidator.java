package icu.nescar.armee.jet.jet808.support.ext;


import icu.nescar.armee.jet.annotation.DebugOnly;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.msg.req.BuiltinAuthRequestMsgBody;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hylexus
 * Created At 2019-08-26 8:19 下午
 * 内置的鉴权逻辑是永远返回true 需自行实现
 */
@FunctionalInterface
public interface AuthCodeValidator {

    boolean validateAuthCode(Session session, RequestMsgMetadata requestMsgMetadata, BuiltinAuthRequestMsgBody authRequestMsgBody);

    @Slf4j
    @DebugOnly
    class BuiltinAuthCodeValidatorForDebugging implements icu.nescar.armee.jet.jet808.support.ext.AuthCodeValidator {
        @Override
        public boolean validateAuthCode(Session session, RequestMsgMetadata metadata, BuiltinAuthRequestMsgBody body) {
            log.info("[AuthCodeValidator] Always return true, authCode : {}", body.getAuthCode());
            return true;
        }
    }

}
