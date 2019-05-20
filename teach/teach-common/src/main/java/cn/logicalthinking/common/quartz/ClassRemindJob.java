package cn.logicalthinking.common.quartz;

import cn.logicalthinking.common.util.SpringContextHolder;
import org.apache.log4j.Logger;
import org.quartz.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 上课短信提醒任务
 */
public class ClassRemindJob implements Job {

    private static final Logger logger = Logger.getLogger(ClassRemindJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        // 短信提醒
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        String phone = jobDataMap.getString("phone");
        String courseName = jobDataMap.getString("courseName");
        String jobName = jobDataMap.getString("jobName");
        int courseTime = jobDataMap.getInt("courseTime");
        int studentId = jobDataMap.getInt("studentId");
        int teacherId = jobDataMap.getInt("teacherId");
        String beanName = jobDataMap.getString("beanName");

        logger.info("【定时调度】>>上课短信提醒任务：提醒手机号为" + phone + " 的用户，" + courseTime + "时，上《" + courseName + "》课");
        // 推送提醒
        ISendNotify iSendNotify = (ISendNotifyToStudent) SpringContextHolder.WEB_APP_CONTEXT.getBean(beanName);
        Map<String, String> param = new HashMap<>();
        param.put("phone", phone);
        param.put("courseName", courseName);
        param.put("courseTime", courseTime + "");
        param.put("studentId", studentId + "");
        param.put("teacherId", teacherId + "");
        try {
            iSendNotify.send(param);
            QuartzManager.removeJob(jobName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.info("【定时调度】<<上课短信提醒任务：提醒手机号为" + phone + " 的用户，" + courseTime + "时，上《" + courseName + "》课");

    }
}
