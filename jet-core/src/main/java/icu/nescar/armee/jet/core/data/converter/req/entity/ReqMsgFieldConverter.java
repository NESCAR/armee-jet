package icu.nescar.armee.jet.core.data.converter.req.entity;

/**
 * @author Charles Song
 * @date 2020-6-23
 */
@FunctionalInterface
public interface ReqMsgFieldConverter<T> {

    T convert(byte[] bytes, byte[] subSeq);

    class NoOpsConverter implements ReqMsgFieldConverter<Object> {
        @Override
        public Object convert(byte[] bytes, byte[] subSeq) {
            return null;
        }
    }

}
