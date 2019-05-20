package cn.logicalthinking.common.pay;

import cn.logicalthinking.common.quartz.QuartzManager;
import com.github.wxpay.sdk.WXPayConfig;
import org.apache.log4j.Logger;
import org.quartz.SchedulerException;

import java.util.Map;

/**
 * 支付回调处理定时任务装饰器
 */
public class CallbackDecoratedQuartz extends CallbackDecorated {

    private static final Logger logger = Logger.getLogger(CallbackDecoratedQuartz.class);

    public CallbackDecoratedQuartz(Callback callback) {
        super(callback);
    }

    @Override
    public void init(Map<String, Object> result) {
        super.init(result);
    }

    @Override
    public boolean onProcessed() {
        boolean onProcessed = super.onProcessed();
        if (onProcessed) {
            try {
                removeJob();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        return onProcessed;
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
        try {
            removeJob();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void removeJob() throws SchedulerException {
        String jobName = String.valueOf(super.getResult().get("jobName"));
        QuartzManager.removeJob(jobName);
        logger.info("=========================");
        logger.info("任务： " + jobName + "结束");
        logger.info("=========================");
    }

    @Override
    public void onFail() {
        super.onFail();
    }

    @Override
    public void onTimeout() {
        try {
            super.onTimeout();
            // 超时关闭订单
            WXPayConfig wxPayConfig = (WXPayConfig) getResult().get("wxPayConfig");
            MyWXPayUtil.closeOrder(String.valueOf(getResult().get("orderNo")), wxPayConfig);
            removeJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
