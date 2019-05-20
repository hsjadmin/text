package cn.logicalthinking.common.quartz;

import cn.logicalthinking.common.pay.Callback;
import cn.logicalthinking.common.pay.CallbackDecoratedQuartz;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import com.github.wxpay.sdk.WXPayConfig;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Calendar;
import java.util.Map;

/**
 * 支付查询任务
 */
public class JobQueryOrder implements Job {

    private static final Logger logger = Logger.getLogger(JobQueryOrder.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

            Map result = (Map) jobDataMap.get("result");
            Calendar endTime = (Calendar) jobDataMap.get("endTime");

            String jobName = result.get("jobName").toString();
            String callbackName = result.get("callbackName").toString();
            String outTradeNo = result.get("orderNo").toString();
            WXPayConfig wxPayConfig = (WXPayConfig) result.get("wxPayConfig");

            // 获取回调对象
            Callback callback = (Callback) SpringContextHolder.WEB_APP_CONTEXT.getBean(callbackName);
            // 定时任务装饰
            callback = new CallbackDecoratedQuartz(callback);
            // 初始化值
            callback.init(result);

            logger.info("【定时调度】>>>>>>>>>>支付查询任务：查询订单号为" + outTradeNo + "的订单>>>>>>>>>>");
            Calendar now = Calendar.getInstance();
            if (now.after(endTime)) {
                logger.info("超时关闭: out_trade_no=" + outTradeNo + ", jobName=" + jobName);
                callback.onTimeout();
                return;
            }

            try {
                MyWXPayUtil.queryOrder(outTradeNo, wxPayConfig, callback);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }

            logger.info("【定时调度】<<<<<<<<<<支付查询任务：查询订单号为" + outTradeNo + "的订单<<<<<<<<<<");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

    }

}
