package icu.nescar.armee.jet.broker.msg.CANMessage;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;
import sun.dc.pr.PRError;

/**
 * @Auther whale
 * @Date 2021/4/28
 */
@Data
@Accessors(chain = true)
public class RGE23 implements CANMsgBody{
    private int tyreIdentification;
    private int tyreTemperature;
    private int airLeakageDetection;
    private int tyrePressureThresholdDetection;
    private int tyreModulePowerSupplyStatus;
    private int identificationDataIndex;
    private int identificationDataContent;
    private byte[] canTime;
}

