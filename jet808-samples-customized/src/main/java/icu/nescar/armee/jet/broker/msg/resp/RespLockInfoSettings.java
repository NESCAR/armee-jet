package icu.nescar.armee.jet.broker.msg.resp;

import io.github.hylexus.jt.annotation.msg.resp.CommandField;
import io.github.hylexus.jt.annotation.msg.resp.Jt808RespMsgBody;
import io.github.hylexus.jt.data.resp.BytesValueWrapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

import static io.github.hylexus.jt.data.MsgDataType.BYTE;
import static io.github.hylexus.jt.data.MsgDataType.DWORD;

/**
 * @Auther whale
 * @Date 2020/9/7
 * @Jt808RespMsgBody 注解的作用就是类似于该实体类实现了RespMsgBody
 * 主动下发的上锁消息（设置）
 */
@Data
@Accessors(chain = true)
@Jt808RespMsgBody(respMsgId = 0x8114,desc = "上锁信息下发（设置）")
public class RespLockInfoSettings {

    @CommandField(order = 2)
    private List<RespTerminalSettings.ParamItem> paramList;

    @CommandField(order = 1, targetMsgDataType = BYTE)
    private int totalParamCount;

    @Data
    @Accessors(chain = true)
    @SuppressWarnings("rawtypes")
    public static class ParamItem {
        @CommandField(order = 1, targetMsgDataType = DWORD)
        private int msgId;

        @CommandField(order = 2, targetMsgDataType = BYTE)
        private int bytesCountOfContentLength;

        @CommandField(order = 3)
        private BytesValueWrapper msgContent;

        public ParamItem(int msgId, BytesValueWrapper msgContent) {
            this.msgId = msgId;
            this.msgContent = msgContent;
            this.bytesCountOfContentLength = msgContent.getAsBytes().length;
        }
    }

}

