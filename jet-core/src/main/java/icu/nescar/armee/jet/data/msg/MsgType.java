package icu.nescar.armee.jet.data.msg;

import java.util.Optional;

/**
 * 消息类型及转化接口
 * @author Charles Song
 * @date 2020-6-18
 **/
public interface MsgType {
    /**
     * 获取消息ID
     * @return 消息ID
     */
    int getMsgId();

    /**
     * 获取消息描述
     * @return 消息描述
     */
    String getDesc();

    /**
     * 根据消息的ID得到该消息的类型
     * @param msgId 消息ID
     * @return 消息类型
     */
    default Optional<MsgType> parseFromInt(int msgId) {
        throw new UnsupportedOperationException("this method should be override in subclass");
    }

    /**
     * 转化为String
     * @return String
     */
    @Override
    String toString();
}
