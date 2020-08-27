package icu.nescar.armee.jet.jet808.support.session;

/**
 * @Auther  whale
 * @Date  2020-6-26
 * 用来管理每个终端的TCP连接
 * 管理session对象
 * session会话，每个用户的会话对象就叫session对象。
 *  * 一个用户客户端默认情况下独占一个session对象。服务器会将用户数据写到用户独占的session对象中
 */

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
public class SessionManager {
    private final Object lock = new Object();
    private static final SessionManager instance = new SessionManager();

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        return instance;
    }

    // <terminalId,Session>
    private final Map<String, Session> sessionMap = new ConcurrentHashMap<>();
    // <sessionId,terminalId>
    private final Map<String, String> sessionIdTerminalIdMapping = new ConcurrentHashMap<>();

    public void persistenceIfNecessary(String terminalId, Channel channel) {
        Optional<Session> session = findByTerminalId(terminalId, true);
        if (!session.isPresent()) {
            Session newSession = Session.buildSession(channel, terminalId);
            persistence(newSession);
        }
    }

    public void persistence(Session session) {
        synchronized (lock) {
            this.sessionMap.put(session.getTerminalId(), session);//同步；乐观锁，适用读多写少的场景。同一时间只能被同一个线程访问。
            sessionIdTerminalIdMapping.put(session.getId(), session.getTerminalId());
        }
    }

    public void removeBySessionIdAndClose(String sessionId, SessionCloseReason reason) {
        synchronized (lock) {
            sessionIdTerminalIdMapping.remove(sessionId);
            sessionMap.remove(sessionId);
        }
        log.info("session removed [{}] , sessionId = {}", reason, sessionId);
    }

    public Optional<Session> findByTerminalId(String terminalId) {
        return findByTerminalId(terminalId, false);
    }

    public Optional<Session> findByTerminalId(String terminalId, boolean updateLastCommunicateTime) {
        Session session = sessionMap.get(terminalId);
        if (session == null) {
            return Optional.empty();
        }

        if (updateLastCommunicateTime) {
            synchronized (lock) {
                session.setLastCommunicateTimeStamp(System.currentTimeMillis());
            }
        }

        if (!this.checkStatus(session)) {
            return Optional.empty();
        }

        return Optional.of(session);//如果session存在 就返回一个session对象，否则抛出null异常
        //这样的好处是不需要对session进行判断，否则就要先判断它是否是null值，再进行下一步操作
    }

    private boolean checkStatus(Session session) {
        if (!session.getChannel().isActive()) {
            synchronized (lock) {
                this.sessionMap.remove(session.getId());
                sessionIdTerminalIdMapping.remove(session.getId());
                session.getChannel().close();
                log.error("Close by server, terminalId = {}", session.getTerminalId());
                return false;
            }
        }

        return true;
    }

}