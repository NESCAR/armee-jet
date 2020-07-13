package icu.nescar.armee.jet.exception;

/**
 * 消息转义异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class MsgEscapeException extends AbstractJtException {
    public MsgEscapeException() {
    }

    public MsgEscapeException(String message) {
        super(message);
    }

    public MsgEscapeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MsgEscapeException(Throwable cause) {
        super(cause);
    }

    public MsgEscapeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
