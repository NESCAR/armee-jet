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
public class EBS21 implements CANMsgBody{
    private int ABSStatus;
    private int retarderControlStatus;
    private int serviceBrakeStatus;
    private int automaticTowedVehicleBrakingStatus;
    private int VDCActive;
    private int supportOfSideOrAxleWiseBrakeForceDistribution;
    private int brakingSystemWheelBasedSpeed;
    private int retarderRelativePeakTorque;
    private int wheelSpeedDifferenceMainAxle;
    private int lateralAcceleration;
    private byte[] canTime;
}

