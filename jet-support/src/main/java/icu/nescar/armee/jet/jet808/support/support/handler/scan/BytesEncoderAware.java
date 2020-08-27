package icu.nescar.armee.jet.jet808.support.support.handler.scan;


import icu.nescar.armee.jet.jet808.support.codec.BytesEncoder;

/**
 * @author hylexus
 * Created At 2020-02-02 1:32 下午
 * MsgHandler可以实现这个接口以注入BytesEncoder给处理器实例
 * 内置的MsgHandler都实现了这个接口
 * 如果自定义MsgHandler接口实现类的话，可以使用spring的依赖注入的方式来使用BytesEncoder实例
 */
public interface BytesEncoderAware {

    void setBytesEncoder(BytesEncoder bytesEncoder);

}
