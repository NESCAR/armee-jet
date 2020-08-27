package icu.nescar.armee.jet.jet808.support.msg;


import icu.nescar.armee.jet.data.msg.MsgType;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/6/27
 * 请求消息的元数据
 */

@Data//lombok注解 自动生成get set tostring等方法
@Accessors(chain = true)//配置lombok如何显示和产生get和set方法  chain=true说明生成的set方法返回this
public class RequestMsgMetadata {
    protected RequestMsgHeader header;//消息头部
    protected byte[] bodyBytes;//主体字节
    protected byte checkSum;//校验码
    protected MsgType msgType;//消息类型

    @Override
    public String toString() {
        return "RequestMsgMetadata{"
                + "msgType=" + msgType
                + ", header=" + header
                //+ ", bodyBytes=" + Arrays.toString(bodyBytes)
                + ", checkSum=" + checkSum
                + '}';
    }
}