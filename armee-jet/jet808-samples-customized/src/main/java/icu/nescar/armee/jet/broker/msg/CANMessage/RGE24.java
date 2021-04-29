package icu.nescar.armee.jet.broker.msg.CANMessage;

import io.github.hylexus.jt.annotation.msg.req.Jt808ReqMsgBody;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.ref.PhantomReference;

/**
 * @Auther whale
 * @Date 2021/4/28
 */
@Data
@Accessors(chain = true)
public class RGE24 implements CANMsgBody{
    private int tyreIdentification;
    private int axleLoadMeasuredByTowedVehicle;
    private int axleLoadMeasuredByExternalVehicle;
    private int axleLoadCalibrationDataLoadLevel;
    private int axleLoadCalibrationType;
    private int axleLoadCalibrationDataStorage;
    private int year;
    private int month;
    private byte[] canTime;

}

