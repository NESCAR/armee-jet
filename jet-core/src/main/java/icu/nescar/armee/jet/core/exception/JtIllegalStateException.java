package icu.nescar.armee.jet.core.exception;

/**
 * 非法状态异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class JtIllegalStateException extends AbstractJtException {
    public JtIllegalStateException() {
    }

    public JtIllegalStateException(String message) {
        super(message);
    }

    public JtIllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public JtIllegalStateException(Throwable cause) {
        super(cause);
    }

    public JtIllegalStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
