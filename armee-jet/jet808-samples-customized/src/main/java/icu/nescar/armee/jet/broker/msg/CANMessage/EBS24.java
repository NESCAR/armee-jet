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
public class EBS24 implements CANMsgBody{
    private int geometricDataIndex;
    private int geometricDataIndexContent;
    private int towedDetectionStatus;
    private int vehicleCombinationABSStatus;
    private byte[] canTime;
}

