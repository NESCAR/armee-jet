package icu.nescar.armee.jet.jet808.support.converter;

import icu.nescar.armee.jet.data.msg.MsgType;

import java.util.Optional;

/**
 * @Auther whale
 * @Date 2020/6/27
 */
public interface MsgTypeParser {
    Optional<MsgType> parseMsgType(int msgType);
}
