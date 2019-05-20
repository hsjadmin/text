package cn.logicalthinking.common.base.enums;

/**
 * @author xhx
 * @version 1.0
 * @Description 充值订单状态
 * @date 2018-12-19
 */
public enum OrderRechargeStatus {

    /**
     * 充值订单状态（0预支付，1支付中，2支付成功，3支付失败，4超时关闭，5取消订单，6退款）
     */

    PREPARE("0", "0预支付"),
    PAYING("1", "1支付中"),
    PAID("2", "2支付成功"),
    PAY_FAIL("3", "3支付失败"),
    CLOSED("4", "4超时关闭"),
    CANCELED("5", "5取消订单"),
    REFUNDED("6", "6退款"),
    ;

    private String key;
    private String value;

    private OrderRechargeStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueBykey(String key) {
        for (OrderRechargeStatus code : OrderRechargeStatus.values()) {
            if (code.getKey().equals(key)) {
                return code.getValue();
            }
        }
        return "";
    }

}
