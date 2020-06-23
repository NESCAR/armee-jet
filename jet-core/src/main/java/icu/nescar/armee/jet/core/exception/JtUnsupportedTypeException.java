package icu.nescar.armee.jet.core.exception;

/**
 * 不支持类型异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class JtUnsupportedTypeException extends AbstractJtException {
    public JtUnsupportedTypeException() {
    }

    public JtUnsupportedTypeException(String message) {
        super(message);
    }

    public JtUnsupportedTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JtUnsupportedTypeException(Throwable cause) {
        super(cause);
    }

    public JtUnsupportedTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
