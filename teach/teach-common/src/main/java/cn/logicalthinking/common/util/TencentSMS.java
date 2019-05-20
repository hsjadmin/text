package cn.logicalthinking.common.util;


import cn.logicalthinking.common.base.enums.SmsEnum;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/3,17:55
 */
@Component
public class TencentSMS {


    private static Integer APP_ID;
    private static String APP_KEY;
    private static String SMS_SIGN = "小六六科技";

    /**
     * 发送短信验证
     *
     * @param phone 电话
     * @author 卢祖飞
     */
    public static SmsSingleSenderResult sengSMS(String phone, SmsEnum smsEnum, String[] params) {
        SmsSingleSenderResult result = null;
        try {
            SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
            result = ssender.sendWithParam("86", phone, smsEnum.getTemplateCode(), params, SMS_SIGN, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(new JSONObject(result));
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送短信验证
     *
     * @param phone 电话
     * @author 卢祖飞
     */
    public static SmsSingleSenderResult sengSMS1(String phone, SmsEnum smsEnum, String[] params) throws Exception {
        SmsSingleSenderResult result = null;
        System.out.println(APP_ID);
        SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
        result = ssender.sendWithParam("86", phone, smsEnum.getTemplateCode(), params, SMS_SIGN, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.println(result);
        return result;
    }

    @Value("${tencentsms.APP_ID}")
    public void setACCESS_ID(Integer appId) {
        APP_ID = appId;
    }

    @Value("${tencentsms.APP_KEY}")
    public void setACCESS_KEY(String appKey) {
        APP_KEY = appKey;
    }
}
