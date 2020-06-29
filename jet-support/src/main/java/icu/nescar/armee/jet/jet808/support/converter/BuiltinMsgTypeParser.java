package icu.nescar.armee.jet.jet808.support.converter;



import icu.nescar.armee.jet.annotation.DebugOnly;
import icu.nescar.armee.jet.data.msg.BuiltinJt808MsgType;
import icu.nescar.armee.jet.data.msg.MsgType;

import java.util.Optional;

/**
 * @author hylexus
 * Created At 2019-08-21 23:16
 */
@DebugOnly
public class BuiltinMsgTypeParser implements MsgTypeParser {

    @Override
    public Optional<MsgType> parseMsgType(int msgType) {
        return BuiltinJt808MsgType.CLIENT_AUTH.parseFromInt(msgType);
    }

}
