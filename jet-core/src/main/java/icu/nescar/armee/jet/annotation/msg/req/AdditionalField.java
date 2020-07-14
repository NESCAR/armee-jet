package icu.nescar.armee.jet.annotation.msg.req;


import icu.nescar.armee.jet.annotation.DebugOnly;
import icu.nescar.armee.jet.annotation.msg.req.basic.BasicField;

import java.lang.annotation.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Charles Song
 * @date 2020-6-19
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Deprecated
@DebugOnly
public @interface AdditionalField {

    Set<Class<?>> SUPPORTED_TARGET_CLASS = Collections.singleton(List.class);
    int ROOT_GROUP_MSG_ID = -1;
    int DEFAULT_BYTE_COUNT_OF_MSG_ID = 1;
    int DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH = 1;

    /**
     * @see BasicField#startIndex()
     * @return 开始下标
     */
    int startIndex();

    /**
     * @see BasicField#length()
     * @return 长度
     */
    int length() default 0;

    /**
     * @see BasicField#byteCountMethod()
     * @return byte计数方法
     */
    String byteCountMethod() default "";

    MsgTypeMapping[] msgTypeMappings();

    @interface MsgTypeMapping {

        int groupMsgId() default ROOT_GROUP_MSG_ID;

        boolean isNestedAdditionalField() default false;

        int byteCountOfMsgId() default DEFAULT_BYTE_COUNT_OF_MSG_ID;

        int byteCountOfContentLength() default DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH;

    }
}
