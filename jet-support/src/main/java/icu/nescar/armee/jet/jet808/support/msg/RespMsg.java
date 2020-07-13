package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.MsgType;

/**
 * @author hylexus
 * Created At 2020-03-25 6:47 下午
 */
public interface RespMsg {
    String getTerminalId();

    MsgType msgType();
}
