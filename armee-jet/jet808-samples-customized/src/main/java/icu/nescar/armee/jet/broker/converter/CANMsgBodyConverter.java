package icu.nescar.armee.jet.broker.converter;

import icu.nescar.armee.jet.broker.msg.CANMessage.*;
import icu.nescar.armee.jet.broker.msg.req.CANMsgRequestMsgBody;
import io.github.hylexus.jt808.converter.RequestMsgBodyConverter;
import io.github.hylexus.jt808.msg.RequestMsgBody;
import io.github.hylexus.jt808.msg.RequestMsgMetadata;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

import static io.github.hylexus.oaks.utils.IntBitOps.intFromBytes;

/**
 * @Auther whale
 * @Date 2021/4/22
 * can报文的转换器
 */
@Slf4j
public class CANMsgBodyConverter implements RequestMsgBodyConverter<CANMsgRequestMsgBody> {
    @Override
    public Optional<CANMsgRequestMsgBody> convert2Entity(RequestMsgMetadata requestMsgMetadata) {
        byte[] bytes = requestMsgMetadata.getBodyBytes();
        CANMsgRequestMsgBody body=new CANMsgRequestMsgBody();
        body.setMsgItem(intFromBytes(bytes,0,2));
        body.setCanTime(Arrays.copyOfRange(bytes,2,8));
        body.setCanID(intFromBytes(bytes,8,4));
        byte[] canData=Arrays.copyOfRange(bytes,12,20);

        if(body.getCanID()==0x0C02C820){
            EBS11 canBody=new EBS11();
            canBody.setCanTime(body.getCanTime());
            canBody.setABSstatus((canData[0]>>6)&0x03);
            canBody.setRetarderControlStatus((canData[0]>>4)&0x03);
            canBody.setASRBrakeCcontrolStatus((canData[0]>>2)&0x03);
            canBody.setASREngineControlStatus(canData[0]&0x03);
            canBody.setBrakeLightSwitch((canData[1]>>6)&0x03);
            canBody.setVehicleType((canData[1]>>4)&0x03);
            canBody.setVDCActive((canData[1]>>2)&0x03);
            canBody.setServiceBrakeDemandPressure(intFromBytes(canData,2,2));
            canBody.setParkBrakeDemandRelativePressure(intFromBytes(canData,4,1));
            canBody.setRetarderDemandRelativePressure(intFromBytes(canData,5,1));
            canBody.setRelativeBrakeDemandFrontLeftSide(intFromBytes(canData,6,1));
            canBody.setRelativeBrakeDemandRearRightSide(intFromBytes(canData,7,1));
            body.setCanData(canBody);


        }
        if(body.getCanID()==0x18FEC920){
            EBS12 canBody=new EBS12();
            canBody.setCanTime(body.getCanTime());
            canBody.setRetarderControlStatus((canData[0]>>6)&0x03);
            canBody.setROPStatus((canData[0]>>4)&0x03);
            canBody.setYCStatus((canData[0]>>2)&0x03);
            canBody.setTrailerROPSystemRequest((canData[1]>>6)&0x03);
            canBody.setTrailerYCSystemRequest((canData[1]>>4)&0x03);
            canBody.setReverseGearStatus((canData[1]>>2)&0x03);
            canBody.setEmergencyBrakingStatus(canData[1]&0x03);
            canBody.setTwoElectricalCircuitsBrakeDemandStatus((canData[2]>>6)&0x03);
            canBody.setABSOffroadRequest((canData[2]>>4)&0x03);
            canBody.setPneumaticControlLineStatus((canData[2]>>2)&0x03);
            canBody.setLoadProportionalFunctionInstallationStatus(canData[2]&0x03);
            canBody.setRoadCurvature(intFromBytes(canData,4,2));
            canBody.setBrakingSystemWheelbasedSpeed(intFromBytes(canData,6,2));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x0C0320C8){
            EBS21 canBody=new EBS21();
            canBody.setCanTime(body.getCanTime());
            canBody.setABSStatus((canData[0]>>6)&0x03);
            canBody.setRetarderControlStatus((canData[0]>>4)&0x03);
            canBody.setServiceBrakeStatus((canData[0]>>2)&0x03);
            canBody.setAutomaticTowedVehicleBrakingStatus(canData[0]&0x03);
            canBody.setVDCActive((canData[1]>>6)&0x03);
            canBody.setSupportOfSideOrAxleWiseBrakeForceDistribution((canData[1]>>4)&0x03);
            canBody.setBrakingSystemWheelBasedSpeed(intFromBytes(canData,2,2));
            canBody.setRetarderRelativePeakTorque(intFromBytes(canData,4,1));
            canBody.setWheelSpeedDifferenceMainAxle(intFromBytes(canData,5,2));
            canBody.setLateralAcceleration(intFromBytes(canData,7,1));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18FEC4C8){
            EBS22 canBody=new EBS22();
            canBody.setCanTime(body.getCanTime());
            canBody.setABSStatus((canData[0]>>6)&0x03);
            canBody.setRetarderControlStatus((canData[0]>>4)&0x03);
            canBody.setServiceBrakeStatus((canData[0]>>2)&0x03);
            canBody.setAutomaticTowedVehicleBrakingStatus(canData[0]&0x03);
            canBody.setElectricalSupplyStatus((canData[1]>>6)&0x03);
            canBody.setRedWarningSignalRequest((canData[1]>>4)&0x03);
            canBody.setAmberWarningSignalRequest((canData[1]>>2)&0x03);
            canBody.setElectricalSupplyOfNonbrakingSystemStatus(canData[1]&0x03);
            canBody.setSpringBrakeInstallationStatus((canData[2]>>6)&0x03);
            canBody.setElectricalLoadProportionalFunctionInstallStatus((canData[2]>>4)&0x03);
            canBody.setVehicleType((canData[2]>>2)&0x03);
            canBody.setSpringBrakeStatus(canData[2]&0x03);
            canBody.setLoadRampApproachAssistanceStatus((canData[3]>>6)&0x03);
            canBody.setSupplyLineBraking((canData[3]>>4)&0x03);
            canBody.setStopLampsRequest((canData[3]>>2)&0x03);
            canBody.setBrakingViaElectricControlLineSupport(canData[3]&0x03);
            canBody.setAxleLoadSum(intFromBytes(canData,4,2));
            canBody.setRetarderReferenceTorque(intFromBytes(canData,6,2));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18FEC6C8){
            EBS23 canBody=new EBS23();
            canBody.setCanTime(body.getCanTime());
            canBody.setTyrePressureStatus((canData[0]>>6)&0x03);
            canBody.setBrakeLiningStatus((canData[0]>>4)&0x03);
            canBody.setBrakeTemperatureStatus((canData[0]>>2)&0x03);
            canBody.setPneumaticSupplyPressure(canData[0]&0x03);
            canBody.setTyreIdentificationForTyrePressure(intFromBytes(canData,1,1));
            canBody.setTyreIdentificationForBrakeLining(intFromBytes(canData,2,1));
            canBody.setTyreIdentificationForBrakeTemperature(intFromBytes(canData,3,1));
            canBody.setTyrePressure(intFromBytes(canData,4,1));
            canBody.setBrakeLining(intFromBytes(canData,5,1));
            canBody.setBrakeTemperature(intFromBytes(canData,6,1));
            canBody.setPneumaticSupplyPressure(intFromBytes(canData,7,2));
            body.setCanData(canBody);

        }

        if(body.getCanID()==0x18FD9AC8){
            EBS24 canBody=new EBS24();
            canBody.setCanTime(body.getCanTime());
            canBody.setGeometricDataIndex(intFromBytes(canData,0,1));
            canBody.setGeometricDataIndexContent(intFromBytes(canData,1,1));
            canBody.setTowedDetectionStatus((canData[2]>>4)&0x15);
            canBody.setVehicleCombinationABSStatus((canData[2]>>2)&0x03);
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18FEADC8){
            EBS25 canBody=new EBS25();
            canBody.setCanTime(body.getCanTime());
            canBody.setBrakeCylinderPressureFirstAxleLeftWheel(intFromBytes(canData,0,1));
            canBody.setBrakeCylinderPressureFirstAxleRightWheel(intFromBytes(canData,1,1));
            canBody.setBrakeCylinderPressureSecondAxleLeftWheel(intFromBytes(canData,2,1));
            canBody.setBrakeCylinderPressureSecondAxleRightWheel(intFromBytes(canData,3,1));
            canBody.setBrakeCylinderPressureThirdAxleLeftWheel(intFromBytes(canData,4,1));
            canBody.setBrakeCylinderPressureThirdAxleRightWheel(intFromBytes(canData,5,1));
            canBody.setROPSystemStatus((canData[6]>>6)&0x03);
            canBody.setYCSystemStatus((canData[6]>>4)&0x03);
            canBody.setExternalBrakeRequestStatus((canData[6]>>2)&0x03);
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x0CFE6EC8){
            EBS26 canBody=new EBS26();
            canBody.setCanTime(body.getCanTime());
            canBody.setWheelSpeedFirstAxleLeftWheel(intFromBytes(canData,0,2));
            canBody.setWheelSpeedFirstAxleRightWheel(intFromBytes(canData,2,2));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18E4C820){
            RGE11 canBody=new RGE11();
            canBody.setCanTime(body.getCanTime());
            canBody.setRideHeightRequest((canData[0]>>6)&0x03);
            canBody.setLevelChangeRequestFrontAxle((canData[0]>>4)&0x03);
            canBody.setLevelChangeRequestRearAxle((canData[0]>>2)&0x03);
            canBody.setTractionHelpRequest(canData[0]&0x03);
            canBody.setLiftAxle1PositionRequest((canData[1]>>6)&0x03);
            canBody.setLiftAxle2PositionRequest((canData[1]>>4)&0x03);
            canBody.setSteeringAxleLockingRequest((canData[1]>>2)&0x03);
            canBody.setRampLevelRequest(canData[1]&0x03);
            canBody.setLevelControlRequest((canData[2]>>6)&0x03);
            canBody.setRampLevelStorageRequest((canData[2]>>4)&0x03);
            canBody.setStopLevelChangeRequest((canData[2]>>2)&0x03);
            canBody.setRideHeightStorageRequest(canData[2]&0x03);
            canBody.setDrivenAxleLoad(intFromBytes(canData,3,2));
            canBody.setParkingAndTrailerAirPressure(intFromBytes(canData,5,1));
            canBody.setAuxiliaryEquipmentSupplyPressure(intFromBytes(canData,6,1));
            canBody.setLiftAxle3PositionRequest((canData[7]>>6)&0x03);
            canBody.setLiftAxle4PositionRequest((canData[7]>>4)&0x03);
            canBody.setLiftAxle5PositionRequest((canData[7]>>2)&0x03);
            canBody.setRideHeightAndRampLevelSetRequest(canData[7]&0x03);
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x188AC820){
            RGE12 canBody=new RGE12();
            canBody.setCanTime(body.getCanTime());
            canBody.setAxleLoadCalibrationDataStorageRequest((canData[0]>>6)&0x03);
            canBody.setTyreWheelIdentification(intFromBytes(canData,1,1));
            canBody.setAxleLoadMeasuredByExternScale(intFromBytes(canData,2,1));
            canBody.setIdentificationDataIndex(intFromBytes(canData,4,1));
            canBody.setIdentificationDataContent(intFromBytes(canData,5,1));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18E520C8){
            RGE21 canBody=new RGE21();
            canBody.setCanTime(body.getCanTime());
            canBody.setRideHeightLevel((canData[0]>>6)&0x03);
            canBody.setLevelControlStatus((canData[0]>>4)&0x03);
            canBody.setTractionHelpStatus((canData[0]>>2)&0x03);
            canBody.setRampLevelPosition(canData[0]&0x03);
            canBody.setLiftAxle1Position((canData[1]>>6)&0x03);
            canBody.setLiftAxle2Position((canData[1]>>4)&0x03);
            canBody.setSteeringAxleLockingStatus((canData[1]>>2)&0x03);
            canBody.setRideHeightStorage((canData[2]>>6)&0x03);
            canBody.setRampLevelStorageStatus((canData[2]>>4)&0x03);
            canBody.setLevelChangeStatusFrontAxle((canData[2]>>2)&0x03);
            canBody.setLevelChangeStatusRearAxle(canData[2]&0x03);
            canBody.setStopLevelChangeAcknowledge((canData[3]>>6)&0x03);
            canBody.setNormalLevel((canData[3]>>4)&0x03);
            canBody.setRampLevel((canData[3]>>2)&0x03);
            canBody.setExtendedRideHeightAndRampLevelStorage(canData[3]&0x03);
            canBody.setLevelChangeStatusFrontAxle(intFromBytes(canData,4,2));
            canBody.setLevelChangeStatusRearAxle(intFromBytes(canData,6,2));
            body.setCanData(canBody);

        }
        if(body.getCanID()==0x18FE5CC8){
            RGE22 canBody=new RGE22();
            canBody.setCanTime(body.getCanTime());
            canBody.setRelativeBodyLevelFrontAxle(intFromBytes(canData,0,2));
            canBody.setRelativeBodyLevelRearAxle(intFromBytes(canData,2,2));
            canBody.setTyreIdentification(intFromBytes(canData,4,1));
            canBody.setAxleLoad(intFromBytes(canData,5,2));
            body.setCanData(canBody);


        }
        if(body.getCanID()==0x18FE5EC8){
            RGE23 canBody=new RGE23();
            canBody.setCanTime(body.getCanTime());
            canBody.setTyreIdentification(intFromBytes(canData,0,1));
            canBody.setTyreTemperature(intFromBytes(canData,1,2));
            canBody.setAirLeakageDetection(intFromBytes(canData,3,2));
            canBody.setTyrePressureThresholdDetection((canData[5]>>5)&0x07);
            canBody.setTyreModulePowerSupplyStatus((canData[5]>>3)&0x03);
            canBody.setIdentificationDataIndex(intFromBytes(canData,6,1));
            canBody.setIdentificationDataContent(intFromBytes(canData,7,1));
            body.setCanData(canBody);
        }
        if(body.getCanID()==0x188920C8){
            RGE24 canBody=new RGE24();
            canBody.setCanTime(body.getCanTime());
            canBody.setTyreIdentification(intFromBytes(canData,0,1));
            canBody.setAxleLoadMeasuredByTowedVehicle(intFromBytes(canData,1,2));
            canBody.setAxleLoadMeasuredByExternalVehicle(intFromBytes(canData,3,2));
            canBody.setAxleLoadCalibrationDataLoadLevel((canData[5]>>6)&0x03);
            canBody.setAxleLoadCalibrationType((canData[5]>>4)&0x03);
            canBody.setAxleLoadCalibrationDataStorage((canData[5]>>2)&0x03);
            canBody.setYear(intFromBytes(canData,6,1));
            canBody.setMonth(intFromBytes(canData,7,1));
            body.setCanData(canBody);
        }
        if(body.getCanID()==0x18FEE620){
            TDE11 canBody=new TDE11();
            canBody.setCanTime(body.getCanTime());
            canBody.setSeconds(intFromBytes(canData,0,1));
            canBody.setMinutes(intFromBytes(canData,1,1));
            canBody.setHours(intFromBytes(canData,2,1));
            canBody.setDay(intFromBytes(canData,3,1));
            canBody.setMonth(intFromBytes(canData,4,1));
            canBody.setYear(intFromBytes(canData,5,1));
            canBody.setLocalMinuteOffset(intFromBytes(canData,6,1));
            canBody.setLocalHourOffset(intFromBytes(canData,7,1));
            body.setCanData(canBody);
        }
        else{
            log.info("收到未定义的CanID="+body.getCanID()+"的can报文");
        }

        return Optional.of(body);
    }
}

