package cn.logicalthinking.common.pay;

import java.io.Serializable;
import java.util.Map;

/**
 * 支付回调处理
 */
public abstract class Callback implements Serializable {

    private Map<String, Object> result;

    public void init(Map<String, Object> result) {
        this.result = result;
    }

    /**
     * 处理过
     */
    public abstract boolean onProcessed();

    /**
     * 支付成功
     */
    public abstract void onSuccess();

    /**
     * 支付失败
     */
    public abstract void onFail();


    /**
     * 发生异常
     *
     * @param ex
     */
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public abstract void onTimeout();

    public Map<String, Object> getResult() {
        return this.result;
    }

}
