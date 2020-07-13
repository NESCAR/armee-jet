package icu.nescar.armee.jet.jet808.support.handler.impl.reflection.argument.resolver.impl;


import icu.nescar.armee.jet.jet808.support.msg.RequestMsgBody;
import icu.nescar.armee.jet.jet808.support.msg.RequestMsgMetadata;
import icu.nescar.armee.jet.jet808.support.session.Session;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;

/**
 * @author hylexus
 * Created At 2020-02-09 12:14 下午
 */
@Getter
@Setter
@ToString
public class ArgumentContext {
    @Nullable
    private final RequestMsgMetadata metadata;
    @Nullable
    private final Session session;
    @Nullable
    private final RequestMsgBody msg;
    @Nullable
    private final Throwable throwable;

    public ArgumentContext(@Nullable RequestMsgMetadata metadata, @Nullable Session session, @Nullable RequestMsgBody msg, @Nullable Throwable throwable) {
        this.metadata = metadata;
        this.session = session;
        this.msg = msg;
        this.throwable = throwable;
    }
}
