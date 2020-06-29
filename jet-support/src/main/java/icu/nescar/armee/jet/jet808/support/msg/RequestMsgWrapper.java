package icu.nescar.armee.jet.jet808.support.msg;


import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Auther whale
 * @Date 2020/6/27
 */

@Data
@Accessors(chain = true)
public class RequestMsgWrapper {
    private RequestMsgMetadata metadata;
    private RequestMsgBody body;
}
