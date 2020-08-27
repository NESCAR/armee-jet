package icu.nescar.armee.jet.jet808.support.session;

/**
 * @Auther  whale
 * @Date  2020-6-26
 * session会话，每个用户的会话对象就叫session对象。
 *  * 一个用户客户端默认情况下独占一个session对象。服务器会将用户数据写到用户独占的session对象中
 */

import icu.nescar.armee.jet.exception.JtCommunicationException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class Session {
    private String id;
    private Channel channel = null;
    private String terminalId;
    private int currentFlowId = 0;
    private long lastCommunicateTimeStamp = 0L;

    public void sendMsgToClient(byte[] bytes) throws InterruptedException, JtCommunicationException {
        this.sendMsgToClient(Unpooled.wrappedBuffer(bytes));//
    }//将字节数组发给客户端

    public void sendMsgToClient(ByteBuf byteBuf) throws InterruptedException, JtCommunicationException {
        ChannelFuture sync = channel.writeAndFlush(byteBuf).sync();
        if (!sync.isSuccess()) {
            throw new JtCommunicationException("sendMsgToClient failed");
        }
    }//将bytebuf对象发送给客户端

    public static String generateSessionId(Channel channel) {
        return channel.id().asLongText();
    }
//生成session会话的id，就是对应channel的id
    public static Session buildSession(Channel channel, String terminalId) {
        Session session = new Session();
        session.setChannel(channel);
        session.setId(generateSessionId(channel));
        session.setTerminalId(terminalId);
        session.setLastCommunicateTimeStamp(System.currentTimeMillis());
        return session;
    }//建立session，包括指定对应channel，对应id和终端id以及时间戳

    public int getCurrentFlowId() {
        return currentFlowId();
    }
//获取流水号 因为消息可能会分包发送。
    private synchronized int currentFlowId() {
        if (currentFlowId >= 0xffff) {
            currentFlowId = 0;
        }
        return currentFlowId++;
    }
}
