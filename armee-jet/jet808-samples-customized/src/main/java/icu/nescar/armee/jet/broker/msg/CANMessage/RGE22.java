package icu.nescar.armee.jet.broker.msg.CANMessage;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2021/4/28
 */
@Data
@Accessors(chain = true)
public class RGE22 implements CANMsgBody{
    private int relativeBodyLevelFrontAxle;
    private int relativeBodyLevelRearAxle;
    private int tyreIdentification;
    private int axleLoad;
    private byte[] canTime;
}

