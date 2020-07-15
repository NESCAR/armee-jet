package icu.nescar.armee.jet.samples.customized.ext.auth.service.impl;

import icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao.TerminalInfoDao;
import icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao.TerminalInfoDaoFactory;
import icu.nescar.armee.jet.samples.customized.ext.auth.dal.domain.TerminalInfo;
import icu.nescar.armee.jet.samples.customized.ext.auth.service.AuthCodeValidatorService;
import icu.nescar.armee.jet.samples.customized.ext.conf.VmOptions;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import io.github.hylexus.jt808.msg.req.BuiltinAuthRequestMsgBody;
import io.github.hylexus.jt808.session.Session;

import java.util.List;

/**
 * 基于MySQL的鉴权
 * @author neyzoter
 */
public class AuthCodeValidatorImpl implements AuthCodeValidatorService {

    private TerminalInfoDao dao;
    public AuthCodeValidatorImpl() {
        String db = System.getProperty(VmOptions.AUTH_BY);
        try {
            dao = TerminalInfoDaoFactory.createTerminalInfoDao(db);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean validateAuthCode(Session session, RequestMsgMetadata requestMsgMetadata, BuiltinAuthRequestMsgBody builtinAuthRequestMsgBody) {
        List<TerminalInfo> list = dao.findLimitTerminal(session.getTerminalId(), builtinAuthRequestMsgBody.getAuthCode(), 1);
        if (list == null) {
            return false;
        }
        return !list.isEmpty();
    }
}
