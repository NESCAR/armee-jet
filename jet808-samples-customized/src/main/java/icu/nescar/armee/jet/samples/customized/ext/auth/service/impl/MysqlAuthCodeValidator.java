package icu.nescar.armee.jet.samples.customized.ext.auth.service.impl;

import icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao.TerminalInfoDao;
import icu.nescar.armee.jet.samples.customized.ext.auth.dal.domain.TerminalInfo;
import icu.nescar.armee.jet.samples.customized.ext.auth.service.AuthCodeValidatorService;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.req.BuiltinAuthRequestMsgBody;
import io.github.hylexus.jt808.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 基于MySQL的鉴权
 * @author neyzoter
 */
public class MysqlAuthCodeValidator implements AuthCodeValidatorService {
    // ClientDao通过@MapperScan 来实现
    @Autowired
    TerminalInfoDao terminalInfoDao;
    @Override
    public boolean validateAuthCode(Session session, RequestMsgMetadata requestMsgMetadata, BuiltinAuthRequestMsgBody builtinAuthRequestMsgBody) {
        List<TerminalInfo> list = terminalInfoDao.findTerminal(session.getTerminalId(), builtinAuthRequestMsgBody.getAuthCode());
        return !list.isEmpty();
    }
}
