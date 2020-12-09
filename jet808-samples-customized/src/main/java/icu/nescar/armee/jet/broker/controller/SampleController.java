package icu.nescar.armee.jet.broker.controller;

import com.google.common.collect.Lists;

import icu.nescar.armee.jet.broker.msg.resp.RespLockInfoSettings;
import icu.nescar.armee.jet.broker.msg.resp.RespTerminalSettings;
import io.github.hylexus.jt.command.CommandWaitingPool;
import io.github.hylexus.jt.command.Jt808CommandKey;
import io.github.hylexus.jt.data.resp.DwordBytesValueWrapper;
import io.github.hylexus.jt.exception.JtSessionNotFoundException;
import io.github.hylexus.jt808.dispatcher.CommandSender;
import io.github.hylexus.jt808.msg.resp.CommandMsg;

import io.github.hylexus.jt808.session.Jt808Session;
import io.github.hylexus.jt808.session.Jt808SessionManager;
import io.github.hylexus.jt808.session.Session;
import io.github.hylexus.jt808.session.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static icu.nescar.armee.jet.broker.config.Jt808MsgType.*;



/**
 * @author hylexus
 * Created At 2019-10-06 9:12 下午
 * 使用CommandSender将响应消息下发给终端
 */
@Slf4j
@RestController
//@RequestMapping("/demo")
public class SampleController {

    // private Encoder encoder = new Encoder(new BytesEncoder.DefaultBytesEncoder());
    //原本是autowired 因为其是spring实现的注解。所以改成了java实现的resource注解 功能类似
    @Resource
    private CommandSender commandSender;
    @Resource
    private Jt808SessionManager jt808SessionManager;


    //下发设置终端参数命令
    @GetMapping("/send-msg")
    public Object sendMsg(
            //请求参数为终端参数和超时时间
            @RequestParam(required = false, name = "terminalId", defaultValue = "13717861955") String terminalId,
            @RequestParam(required = false, name = "timeout", defaultValue = "5") Long timeout) throws Exception {

        RespTerminalSettings param = new RespTerminalSettings();
        List<RespTerminalSettings.ParamItem> paramList = Lists.newArrayList(
                new RespTerminalSettings.ParamItem(0x0029, DwordBytesValueWrapper.of(100))//下发了一个参数项id为0x0029 代表缺省时间汇报间隔，值为100单位为秒
        );
        //设置好要下发的参数项内容 目前只有0x0029的参数项，如果需要的话可以再加
        param.setParamList(paramList);
        param.setTotalParamCount(paramList.size());

        // simulatePutResultByAnotherThread(commandKey);

        // 【下发消息】的消息类型为: RESP_TERMINAL_PARAM_SETTINGS (0x8103)  --> RespTerminalSettings的类注解上指定了下发类型
        // 客户端对该【下发消息】的回复消息类型为: CLIENT_COMMON_REPLY (0x0001)
        CommandMsg commandMsg = CommandMsg.of(terminalId, CLIENT_COMMON_REPLY, param);
        final Object resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
        log.info("resp: {}", resp);
        return resp;
    }


    //下发消息体为空的查询终端属性命令
    @GetMapping("/query-terminal-properties")
    public Object queryTerminalProperties(
            @RequestParam(required = false, name = "terminalId", defaultValue = "13717861955") String terminalId,
            @RequestParam(required = false, name = "timeout", defaultValue = "5") Long timeout) throws Exception {

        // 【下发消息】的消息体为空
        // 【下发消息】消息的类型为: RESP_QUERY_TERMINAL_PROPERTIES (0x8107)
        // 客户端对该【下发消息】的回复消息类型为: CLIENT_QUERY_TERMINAL_PROPERTIES_REPLY (0x0107)
        CommandMsg commandMsg = CommandMsg.emptyRespMsgBody(terminalId, CLIENT_QUERY_TERMINAL_PROPERTIES_REPLY, RESP_QUERY_TERMINAL_PROPERTIES);
        return commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
    }

    private Jt808Session getSession(String terminalId) {
        return jt808SessionManager.findByTerminalId(terminalId)
                .orElseThrow(() -> new JtSessionNotFoundException(terminalId));
    }


    //下发上锁信息设置
    //【下发消息】的消息类型为: RESP_LOCK_INFO_SETTINGS(0x8114,"上锁信息下发（设置）"),RespLockInfoSettings类注解上指定了
    // 客户端对该【下发消息】的回复消息类型为: CLIENT_COMMON_REPLY (0x0001)
    @GetMapping("/lock-info-settings")
    public Object sendLockInfo(
            @RequestParam(required = false, name = "terminalId", defaultValue = "13717861955") String terminalId,
            @RequestParam(required = false, name = "timeout", defaultValue = "5") Long timeout) throws Exception{
            RespLockInfoSettings lockInfo =new RespLockInfoSettings();//设置具体的下发信息内容
            lockInfo.setCarID("浙A0925H");
            lockInfo.setDriverID("21960114");
            lockInfo.setLockTimeStart("2009011208");
            lockInfo.setLockTimeEnd("2009101408");


            CommandMsg commandMsg=CommandMsg.of(terminalId,CLIENT_COMMON_REPLY,lockInfo);
        final Object resp = commandSender.sendCommandAndWaitingForReply(commandMsg, timeout, TimeUnit.SECONDS);
        log.info("resp: {}", resp);
        return resp;

    }


    private void simulatePutResultByAnotherThread(Jt808CommandKey commandKey) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                CommandWaitingPool.getInstance().putIfNecessary(commandKey, "result for " + commandKey.getKeyAsString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
