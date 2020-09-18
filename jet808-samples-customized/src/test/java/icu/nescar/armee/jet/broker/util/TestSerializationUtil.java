package icu.nescar.armee.jet.broker.util;

import icu.nescar.armee.jet.broker.msg.req.AlarmUploadRequestMsgBody;
import org.junit.Test;

public class TestSerializationUtil {
    @Test
    public void testSerDeser() {
        AlarmUploadRequestMsgBody body = new AlarmUploadRequestMsgBody();
        body.setTime("2020-09-08T12:00:09Z");
        body.setAlarmStatus((byte) 1);
        try {
            byte[] ser = SerializationUtil.serialize(body);
            AlarmUploadRequestMsgBody deser = (AlarmUploadRequestMsgBody) SerializationUtil.deserialize(ser);
            System.out.println(String.format("status : %d time : %s", deser.getAlarmStatus(), deser.getTime()));
        } catch (Exception e) {

        }

    }
}
