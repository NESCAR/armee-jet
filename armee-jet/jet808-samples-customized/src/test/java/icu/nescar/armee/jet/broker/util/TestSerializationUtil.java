package icu.nescar.armee.jet.broker.util;

import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;
import org.junit.Test;

public class TestSerializationUtil {
    @Test
    public void testSerDeser() {
        AlarmUploadRequestMsgBody body = new AlarmUploadRequestMsgBody();
        body.setAlarmTime(new byte[10]);
        body.setAlarmStatus((byte) 1);
        try {
            byte[] ser = SerializationUtil.serialize(body);
            AlarmUploadRequestMsgBody deser = (AlarmUploadRequestMsgBody) SerializationUtil.deserialize(ser);
            System.out.println(String.format("status : %d time : %s", deser.getAlarmStatus(), deser.getAlarmTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
