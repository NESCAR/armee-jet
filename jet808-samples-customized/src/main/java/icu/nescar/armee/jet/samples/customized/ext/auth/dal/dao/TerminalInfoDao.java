package icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao;

import icu.nescar.armee.jet.samples.customized.ext.auth.dal.domain.TerminalInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 设备信息数据访问对象
 * @author neyzoter
 */
public interface TerminalInfoDao {
    /**
     * fine 终端
     * @param terminalId 终端ID
     * @param password 密码
     * @return 终端列表
     */
    List<TerminalInfo> findTerminal(@Param("terminalId") String terminalId, @Param("password") String password);
}
