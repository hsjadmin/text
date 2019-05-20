package cn.logicalthinking.common.base.enums;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程订单状态
 * @date 2018-12-19
 */
public enum OrderCourseStatus {

    /**
     * 课程订单状态
     */

    PREPARE(0, "0待审核"),
    PAYING(1, "1支付中"),
    PAID(2, "2支付成功"),
    PAY_FAIL(3, "3支付失败"),
    CLOSED(4, "4超时关闭"),
    CANCELED(5, "5取消订单"),
    REFUNDED(6, "6退款"),
    ;

    private Integer key;
    private String value;

    private OrderCourseStatus(Integer key, String value) {
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
        for (OrderCourseStatus code : OrderCourseStatus.values()) {
            if (code.getKey().equals(key)) {
                return code.getValue();
            }
        }
        return "";
    }

}
