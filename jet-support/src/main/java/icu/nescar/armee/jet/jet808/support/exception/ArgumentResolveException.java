package icu.nescar.armee.jet.jet808.support.exception;


import icu.nescar.armee.jet.exception.AbstractJtException;
import icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl.ArgumentContext;
import lombok.Getter;

/**
 * @author hylexus
 * Created At 2020-02-02 1:35 下午
 */
@Getter
public class ArgumentResolveException extends AbstractJtException {
    private final ArgumentContext argumentContext;

    public ArgumentResolveException(ArgumentContext argumentContext) {
        this.argumentContext = argumentContext;
    }

    public ArgumentResolveException(String message, ArgumentContext argumentContext) {
        super(message);
        this.argumentContext = argumentContext;
    }

    public ArgumentResolveException(String message, Throwable cause, ArgumentContext argumentContext) {
        super(message, cause);
        this.argumentContext = argumentContext;
    }

    public ArgumentResolveException(Throwable cause, ArgumentContext argumentContext) {
        super(cause);
        this.argumentContext = argumentContext;
    }

    public ArgumentResolveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ArgumentContext argumentContext) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.argumentContext = argumentContext;
    }
}
