package icu.nescar.armee.jet.data.converter.resp.entity;

/**
 * @author hylexus
 * Created At 2020-03-11 21:27 下午
 */
public interface RespMsgFieldConverter {

    byte[] convert(Object object);

    class NoOpsConverter implements RespMsgFieldConverter {

        @Override
        public byte[] convert(Object object) {
            return new byte[0];
        }
    }
}
