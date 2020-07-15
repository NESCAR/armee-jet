package icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao;

import icu.nescar.armee.jet.samples.customized.ext.conf.AuthDb;
import icu.nescar.armee.jet.samples.customized.ext.conf.VmOptions;
import lombok.extern.slf4j.Slf4j;

/**
 * 终端设备信息查询工厂
 * @author neyzoter
 */
@Slf4j
public class TerminalInfoDaoFactory {
    public static TerminalInfoDao createTerminalInfoDao(String dao) throws Exception{
        AuthDb authDb = VmOptions.findDbSupported(dao);
        if (authDb == null) {
            return null;
        }
        switch (authDb) {
            case MYSQL:
                return new MysqlTerminalInfoDao.MysqlTerminalInfoDaoBuilder().defaultDao();
            default:
                return null;
        }
    }
}
