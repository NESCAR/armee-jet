package icu.nescar.armee.jet.data.msg;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static icu.nescar.armee.jet.annotation.msg.req.AdditionalField.*;

/**
 *
 * @author Charles Song
 * @date 2020-6-18
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class AdditionalFieldInfo {

    private int groupMsgId;

    private boolean isNestedAdditionalField = false;

    private int byteCountOfMsgId = DEFAULT_BYTE_COUNT_OF_MSG_ID;

    private int byteCountOfContentLength = DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH;

    public static final AdditionalFieldInfo DEFAULT_ROOT_GROUP;

    static {
        DEFAULT_ROOT_GROUP = new AdditionalFieldInfo()
                .setGroupMsgId(ROOT_GROUP_MSG_ID)
                .setNestedAdditionalField(false)
                .setByteCountOfMsgId(DEFAULT_BYTE_COUNT_OF_MSG_ID)
                .setByteCountOfContentLength(DEFAULT_BYTE_COUNT_OF_CONTENT_LENGTH);
    }
}
