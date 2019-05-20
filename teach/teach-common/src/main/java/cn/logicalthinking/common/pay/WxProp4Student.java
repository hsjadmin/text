package cn.logicalthinking.common.pay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 学生端小程序微信配置
 */
@Component
public class WxProp4Student {
    // 公众平台应用APPId
    public static String MP_ID;
    // 公众平台应用 APP_SECRET
    public static String MP_SECRET;
    // 商户号
    public static String MCH_ID;
    // 商户支付密钥Key
    public static String MCH_API_KEY;

    // 字符编码
    public static String CHARTSET = "UTF-8";

    // 加密方式
    public static String SIGN_TYPE = "MD5";

    /**
     * 支付超时时间，分钟
     */
    public static Integer PAY_TIMEOUT;

    @Value("${WX.MP_ID}")
    public void setMpId(String mpId) {
        MP_ID = mpId;
    }

    @Value("${WX.MP_SECRET:64acf268c6431c9c654ac7233dcf32bb}")
    public void setMpSecret(String mpSecret) {
        MP_SECRET = mpSecret;
    }

    @Value("${WX.MCH_ID:1266745701}")
    public void setMchId(String mchId) {
        MCH_ID = mchId;
    }

    @Value("${WX.MCH_API_KEY:Logicalthinking11111111111111111}")
    public void setMchApiKey(String mchApiKey) {
        MCH_API_KEY = mchApiKey;
    }

    @Value(("${PAY.TOMEOUT}"))
    public void setPayTimeout(Integer payTimeout) {
        PAY_TIMEOUT = payTimeout;
    }
}
