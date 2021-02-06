package icu.nescar.armee.jet.broker.util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;


/**
 * @Auther whale
 * @Date 2021/1/30
 * 钉钉服务机器人的密码计算
 */
public class Test {
    public static void main(String[] args) throws Exception {
        // timestamp: 当前时间戳，单位是毫秒，与请求调用时间误差不能超过1小时
        Long timestamp = System.currentTimeMillis();
        // secret: 密钥，机器人安全设置页面，加签一栏下面显示的SEC开头的字符串
        String secret = "SEC4bce863434bfc9b88ba36bf8bcbd2e900383c2c6b747600af6ca7f9c0ffca950";
        // 把timestamp+"\n"+密钥当做签名字符串，使用HmacSHA256算法计算签名，
        // 然后进行Base64 encode，最后再把签名参数再进行urlEncode，
        // 得到最终的签名（需要使用UTF-8字符集）。
        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        System.out.println(sign);
        System.out.println(timestamp);
    }

}

