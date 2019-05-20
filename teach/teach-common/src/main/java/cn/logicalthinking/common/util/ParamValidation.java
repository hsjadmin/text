package cn.logicalthinking.common.util;

import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;

import java.util.Collection;

/**
 * 参数校验工具
 *
 * @author XHX
 * @date 2018/9/26
 */
public class ParamValidation {

    public static void isNotNull(String s, String msg) {
        isValidate(!StringUtils.isBlank(s), msg);
    }

    public static void isNotNull(Object obj, String msg) {
        isValidate(obj != null, msg);
    }

    public static void isNotNull(Collection collection, String msg) {
        isValidate(collection != null && !collection.isEmpty(), msg);
    }

    public static void isEquals(String s1, String s2, String msg) {
        // 为null
        if (s1 == s2) {
            return;
        }
        isValidate(s1.equals(s2), msg);
    }

    public static void isPhoneNum(String phone) {
        isPhoneNum(phone, "手机格式不正确");
    }

    public static void isPhoneNum(String phone, String msg) {
        isValidate(RegexUtils.checkMobile(phone), msg);
    }

    public static void isValidate(boolean bool, String msg) {
        if (!bool) {
            throw new ValidataException(msg);
        }
    }

}
