package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.MsgType;

/**
 * @author hylexus
 * Created At 2019-08-20 22:01
 */
public interface RequestMsg {
    RequestMsgHeader getHeader();

    byte[] getBodyBytes();

    byte getCheckSum();

    MsgType getMsgType();

}
