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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * 基于MySQL的鉴权
 * @author neyzoter
 */
public class AuthCodeValidatorImpl implements AuthCodeValidatorService {

    /**
     * 连接池
     */
    private final LinkedBlockingQueue<TerminalInfoDao> daos;
    /**
     * 连接池的信号量
     */
    private final Semaphore smph;
    public AuthCodeValidatorImpl() {
        String db = System.getProperty(VmOptions.AUTH_BY);
        int poolCore = Integer.parseInt(System.getProperty(VmOptions.AUTH_VALIDATOR_POOL_CORE_NUM));
        daos = new LinkedBlockingQueue<>(poolCore);
        smph = new Semaphore(poolCore);
        try {
            for (;poolCore > 0;poolCore--) {
                daos.add(TerminalInfoDaoFactory.createTerminalInfoDao(db));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    public boolean validateAuthCode(Session session, RequestMsgMetadata requestMsgMetadata, BuiltinAuthRequestMsgBody builtinAuthRequestMsgBody) {
        try {
            // 阻塞获取资源
            smph.acquire();
            // 从阻塞队列中获取dao资源
            TerminalInfoDao dao = daos.poll();
            if (dao == null) {
                throw new Exception("Dao Is Null");
            }
            List<TerminalInfo> list = dao.findLimitTerminal(session.getTerminalId(), builtinAuthRequestMsgBody.getAuthCode(), 1);
            if (list == null) {
                return false;
            }
            return !list.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            smph.release();
        }
        return false;
    }
}
