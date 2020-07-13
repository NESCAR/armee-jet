package icu.nescar.armee.jet.exception;

/**
 * 非法参数异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class JtIllegalArgumentException extends AbstractJtException {
    public JtIllegalArgumentException() {
    }

    public JtIllegalArgumentException(String message) {
        super(message);
    }

    public JtIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public JtIllegalArgumentException(Throwable cause) {
        super(cause);
    }

    public JtIllegalArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
