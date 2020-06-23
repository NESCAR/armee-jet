package icu.nescar.armee.jet.core.exception;

/**
 * 通信异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class JtCommunicationException extends AbstractJtException {
    public JtCommunicationException() {
    }

    public JtCommunicationException(String message) {
        super(message);
    }

    public JtCommunicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public JtCommunicationException(Throwable cause) {
        super(cause);
    }

    public JtCommunicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
