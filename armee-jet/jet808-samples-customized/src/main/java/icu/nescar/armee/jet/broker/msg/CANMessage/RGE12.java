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
public class RGE12 implements CANMsgBody{
    private int axleLoadCalibrationDataStorageRequest;
    private int tyreWheelIdentification;
    private int axleLoadMeasuredByExternScale;
    private int identificationDataIndex;
    private int identificationDataContent;
    private byte[] canTime;
}

