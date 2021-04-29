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
public class RGE21 implements CANMsgBody{
    private int rideHeightLevel;
    private int levelControlStatus;
    private int tractionHelpStatus;
    private int rampLevelPosition;
    private int liftAxle1Position;
    private int liftAxle2Position;
    private int steeringAxleLockingStatus;
    private int rideHeightStorage;
    private int rampLevelStorageStatus;
    private int levelChangeStatusFrontAxle;
    private int levelChangeStatusRearAxle;
    private int stopLevelChangeAcknowledge;
    private int normalLevel;
    private int rampLevel;
    private int extendedRideHeightAndRampLevelStorage;
    private int nominalVehicleBodyLevelFrontAxle;
    private int nominalVehicleBodyLevelRearAxle;
    private byte[] canTime;

}

