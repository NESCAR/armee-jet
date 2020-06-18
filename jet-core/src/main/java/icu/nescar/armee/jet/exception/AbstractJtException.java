package icu.nescar.armee.jet.exception;

/**
 * 抽象JT异常
 * @author Charles Song
 * @date 2020-6-18
 */
public abstract class AbstractJtException extends RuntimeException {

    public AbstractJtException() {
    }

    public AbstractJtException(String message) {
        super(message);
    }

    public AbstractJtException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractJtException(Throwable cause) {
        super(cause);
    }

    public AbstractJtException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
