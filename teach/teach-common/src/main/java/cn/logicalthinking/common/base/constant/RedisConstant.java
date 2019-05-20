package cn.logicalthinking.common.base.constant;

/**
 * @author XHX
 * @date 2018/10/17
 */
public interface RedisConstant {

    /**
     * 间隔符
     */
    String GAP = ":";

    /**
     * 短信验证码失效时间为为5分钟(300000)，测试为1周(604800000)
     */
    int REDIS_SMS_CODE_TIMEOUT = 300000;

    /**
     * 学生端
     */
    String STUDENT = "STUDENT";
    /**
     * 老师端
     */
    String TEACHER = "TEACHER";

    /**
     * 后台管理
     */
    String MANAGE = "MANAGE";
    /**
     * 短信验证码redis前缀
     */
    String SMS_CODE = "SMS.CODE";
    /**
     * 登录注册业务
     */
    String REGISTER = "REGISTER.LOGIN";
    /**
     * 找回密码业务
     */
    String FORGET = "FORGET";
    /**
     * 更换绑定业务
     */
    String CHANGE_BINDING = "CHANGE.BINDING";


    /**
     * 学生登录注册
     */
    String STUDENT_REGISTER_LOGIN_SMS_CODE = STUDENT + GAP + REGISTER + GAP + SMS_CODE + GAP;

    /**
     * 学生忘记密码
     */
    String STUDENT_FORGET_SMS_CODE = STUDENT + GAP + FORGET + GAP + SMS_CODE + GAP;

    /**
     * 学生更换绑定
     */
    String STUDENT_CHANGE_BINDING_SMS_CODE = STUDENT + GAP + CHANGE_BINDING + GAP + SMS_CODE + GAP;


    /**
     * 老师登录注册
     */
    String TEACHER_REGISTER_LOGIN_SMS_CODE = TEACHER + GAP + REGISTER + GAP + SMS_CODE + GAP;

    /**
     * 老师忘记密码
     */
    String TEACHER_FORGET_SMS_CODE = TEACHER + GAP + FORGET + GAP + SMS_CODE + GAP;

    /**
     * 老师更换绑定
     */
    String TEACHER_CHANGE_BINDING_SMS_CODE = TEACHER + GAP + CHANGE_BINDING + GAP + SMS_CODE + GAP;

    /**
     * 级别，年级，科目
     */
    String LGS = "LGS";
    /**
     * 级别，年级，科目 老师端专用
     */
    String LGS_FOR_TEACHER = "LGS_FOR_TEACHER";

    String COURSE_SHARE = "COURSE_SHARE" + GAP;
}
