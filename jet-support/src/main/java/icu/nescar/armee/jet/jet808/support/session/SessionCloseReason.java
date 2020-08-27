package icu.nescar.armee.jet.jet808.support.session;

/**
 * @Auther  whale
 * @Date  2020-6-26
 * 会话关闭的原因
 */
public enum SessionCloseReason {
    IDLE_TIMEOUT,//空闲态超时
    CHANNEL_INACTIVE,//channel未激活
    SERVER_EXCEPTION_OCCURRED//服务器端被强占
}