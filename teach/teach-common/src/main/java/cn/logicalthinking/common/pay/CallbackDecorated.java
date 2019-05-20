package cn.logicalthinking.common.pay;

import java.util.Map;

/**
 * 支付回调处理装饰器
 */
public class CallbackDecorated extends Callback  {

    private Callback callback;

    public CallbackDecorated(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void init(Map<String, Object> result) {
        this.callback.init(result);
    }

    @Override
    public boolean onProcessed() {
        return this.callback.onProcessed();
    }

    @Override
    public void onSuccess() {
        this.callback.onSuccess();
    }

    @Override
    public void onFail() {
        this.callback.onFail();
    }

    @Override
    public void onTimeout() {
        this.callback.onTimeout();
    }

    @Override
    public Map<String, Object> getResult() {
        return this.callback.getResult();
    }

    @Override
    public void onError(Exception ex) {
        super.onError(ex);
    }
}
