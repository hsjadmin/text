import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Map;

public class JobTest implements Job {

    private static final Logger logger = Logger.getLogger(JobTest.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        Map result = (Map) jobDataMap.get("result");

        String outTradeNo = result.get("orderNo").toString();


        logger.info("【定时调度】>>>>>>>>>>支付查询任务：查询订单号为" + outTradeNo + "的订单>>>>>>>>>>");
        logger.info("【定时调度】<<<<<<<<<<支付查询任务：查询订单号为" + outTradeNo + "的订单<<<<<<<<<<");

        System.out.println("【定时调度】>>>>>>>>>>支付查询任务：查询订单号为" + outTradeNo + "的订单>>>>>>>>>>");
        System.out.println("【定时调度】<<<<<<<<<<支付查询任务：查询订单号为" + outTradeNo + "的订单<<<<<<<<<<");
    }
}
