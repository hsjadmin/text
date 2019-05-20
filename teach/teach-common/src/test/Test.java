import cn.logicalthinking.common.pay.Callback;
import cn.logicalthinking.common.pay.MyWXPayConfig;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.quartz.QuartzManager;
import cn.logicalthinking.common.util.DateUtil;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/application.xml")
public class Test {

    private static Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void test() {
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        String corn = "0/30 * * " + day + " " + month + " ? " + year;
        System.out.println(corn);

        try {
            QuartzManager.addJob("1", new Job() {
                @Override
                public void execute(JobExecutionContext context) throws JobExecutionException {
                    System.out.println(DateUtil.getSimpleCurrentDate());
                }
            }, new JobDataMap(), corn);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    //    public static void main(String[] args) throws SchedulerException {
//        JobDataMap jobDataMap = new JobDataMap();
////        QuartzManager.addJob(QuartzConstant.JOBNAME_CLEAN_FILE, new JobCleanFile(), jobDataMap, "0 40 9 14 1 ?");
//        Scheduler scheduler = QuartzManager.getScheduler();
//        TriggerKey triggerKey = new TriggerKey(QuartzConstant.JOBNAME_CLEAN_FILE, QuartzManager.TRIGGER_GROUP_NAME);
//        Trigger trigger = scheduler.getTrigger(triggerKey);
//        QuartzManager.modifyJobTime(QuartzConstant.JOBNAME_CLEAN_FILE, "0 0 2 ? * MON");
//        System.out.println(trigger);
//    }

    @org.junit.Test
    public void testQusery() throws Exception {
        MyWXPayConfig myWXPayConfig = new MyWXPayConfig();
        MyWXPayUtil.queryOrder("COURSE_10000097", myWXPayConfig, new Callback() {
            @Override
            public boolean onProcessed() {
                return false;
            }

            @Override
            public void onSuccess() {
                System.out.println("onSuccess");
            }

            @Override
            public void onFail() {
                System.out.println("onFail");
            }

            @Override
            public void onTimeout() {
                System.out.println("onTimeout");
            }
        });
    }

//    public static void main(String[] args) throws SchedulerException {
//
//        System.out.println("【定时调度】");
//        JobDataMap jobDataMap = new JobDataMap();
//        Map<String, Object> result = new HashMap<>();
//        result.put("orderNo", System.currentTimeMillis());
//        jobDataMap.put("result", "result");
//        QuartzManager.removeJob("aaa");
//        System.out.println("【定时调度】");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    QuartzManager.addJob("aaa", new JobTest(), jobDataMap, "0/5 * * 17 1 ? 2019");
//                } catch (SchedulerException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        System.out.println("【定时调度】");
//    }

}
