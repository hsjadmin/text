package cn.logicalthinking.common.base.enums;

/**
 * @author xhx
 * @version 1.0
 * @Description 疑难解答订单状态
 * @date 2018-12-19
 */
public enum GenderEnum {

    /**
     * 疑难解答订单状态
     */

    MALE(0, "男"),
    FEMALE(1, "女"),
    ;

    private Integer key;
    private String value;

    private GenderEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueBykey(Integer key) {
        for (GenderEnum code : GenderEnum.values()) {
            if (code.getKey().equals(key)) {
                return code.getValue();
            }
        }
        return null;
    }

}
