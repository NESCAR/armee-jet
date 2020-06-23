package icu.nescar.armee.jet.core.exception;

/**
 * 数据类型转换异常
 * @author Charles Song
 * @date 2020-6-18
 */
public class JtDataTypeConvertException extends AbstractJtException {
    public JtDataTypeConvertException() {
    }

    public JtDataTypeConvertException(String message) {
        super(message);
    }

    public JtDataTypeConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public JtDataTypeConvertException(Throwable cause) {
        super(cause);
    }

    public JtDataTypeConvertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
