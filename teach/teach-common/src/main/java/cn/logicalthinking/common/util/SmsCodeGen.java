package cn.logicalthinking.common.util;

/**
 * 验证码生成
 * @author XHX
 * @date 2018/10/10
 */
public class SmsCodeGen {

    public static String getCode() {
        return (int) ((Math.random() * 9 + 1) * 100000) + "";
    }
}
