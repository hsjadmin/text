package cn.logicalthinking.common.base.enums;

/**
 * 验证码模板
 *
 * @author 肖宏鑫
 * @version 1.0
 * @Description
 * @date 2018/10/10
 */

public enum SmsEnum {


    /**
     * 登录注册
     */
    FOR_LOGIN(260549, "code"),
    /**
     * 上课提醒
     */
    FOR_REMIND(268922, "code"),
    /**
     * 购买提醒
     */
    FOR_BUY(269269, "code"),
    /**
     * 答疑提醒
     */
    FOR_ANSWER(261288,"code");






    private Integer templateCode;
    private String templateParam;
    private String[] templateParams;

    SmsEnum(Integer templateCode, String templateParam) {
        this.templateCode = templateCode;
        this.templateParam = templateParam;
    }
    SmsEnum(Integer templateCode, String... templateParam) {
        this.templateCode = templateCode;
        this.templateParams = templateParam;
    }

    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public String[] getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(String[] templateParams) {
        this.templateParams = templateParams;
    }
}
