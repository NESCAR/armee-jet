package icu.nescar.armee.jet.broker.msg.command;

import icu.nescar.armee.jet.broker.msg.resp.RespTerminalSettings;
import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.data.resp.BytesValueWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static io.github.hylexus.jt.data.MsgDataType.BYTE;
import static io.github.hylexus.jt.data.MsgDataType.DWORD;

/**
 * @Auther whale
 * @Date 2020/11/26
 */
@Data
@Accessors(chain = true)
//TODO 具体的终端下发的参数消息体
public class TerminalSettingsMsgBody {


}

