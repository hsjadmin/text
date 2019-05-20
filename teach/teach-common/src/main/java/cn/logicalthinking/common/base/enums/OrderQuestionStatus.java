package cn.logicalthinking.common.base.enums;

/**
 * @author xhx
 * @version 1.0
 * @Description 疑难解答订单状态
 * @date 2018-12-19
 */
public enum OrderQuestionStatus {

    /**
     * 疑难解答订单状态
     */

    CHECKING(0, "0待审核"),
    ANSWERING(1, "1解疑中"),
    TO_BE_PAY(2, "2待支付"),
    FINISHED(3, "3已完成"),
    NOT_PASS(4, "4未通过"),
    CANCELLED(5, "5已取消"),
    ;

    private Integer key;
    private String value;

    private OrderQuestionStatus(Integer key, String value) {
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
        for (OrderQuestionStatus code : OrderQuestionStatus.values()) {
            if (code.getKey().equals(key)) {
                return code.getValue();
            }
        }
        return "";
    }

}
