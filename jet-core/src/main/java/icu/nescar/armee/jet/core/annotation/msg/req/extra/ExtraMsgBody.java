package icu.nescar.armee.jet.core.annotation.msg.req.extra;

import java.lang.annotation.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 * @see ExtraField
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExtraMsgBody {

    int DEFAULT_BYTE_COUNT_OF_MSG_ID = 1;
    int DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH = 1;

    int byteCountOfMsgId() default DEFAULT_BYTE_COUNT_OF_MSG_ID;

    int byteCountOfContentLength() default DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH;
}
