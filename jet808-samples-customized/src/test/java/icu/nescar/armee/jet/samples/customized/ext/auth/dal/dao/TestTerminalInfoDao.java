package icu.nescar.armee.jet.samples.customized.ext.auth.dal.dao;

import icu.nescar.armee.jet.samples.customized.Jt808ServerSampleCustomizedApplication;
import icu.nescar.armee.jet.samples.customized.ext.auth.dal.domain.TerminalInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Jt808ServerSampleCustomizedApplication.class)
@EnableAutoConfiguration
public class TestTerminalInfoDao {
    @Autowired
    TerminalInfoDao terminalInfoDao;
    @Before
    public void init() {
        System.out.println("开始测试-----------------");
    }
    @Test
    public void testFindTerminal() {
        List<TerminalInfo> list = terminalInfoDao.findTerminal("client1", "123456");
        TerminalInfo info = list.get(0);
        System.out.println(String.format("Terminal Id : %s , PSW : %s", info.getTerminalId(), info.getPassword()));
    }
    @After
    public void after() {
        System.out.println("测试结束-----------------");
    }
}