package icu.nescar.armee.jet.core.data.converter.resp.entity;

/**
 * @author Charles Song
 * @date 2020-6-23
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
