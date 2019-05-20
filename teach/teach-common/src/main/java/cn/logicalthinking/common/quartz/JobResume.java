package cn.logicalthinking.common.quartz;

import cn.logicalthinking.common.base.constant.QuartzConstant;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JobResume {

    private static final Logger logger = Logger.getLogger(JobResume.class);

    private String path;

    private String cron;

    public JobResume(@Value("${fp.cleanFile}") String path, @Value("${fp.cron}") String cron) {

        this.path = path;
        this.cron = cron;

        logger.info("恢复任务调度..");
        try {
            resume();
            logger.info("恢复任务调度-成功");
        } catch (SchedulerException e) {
            logger.info("恢复任务调度-异常");
            logger.error(e.getMessage(), e);
        }

        try {
            logger.info("加载清理任务..");
            addCleanFileJob();
            logger.info("加载清理任务-成功..");
        } catch (SchedulerException e) {
            logger.info("加载清理任务-异常..");
            logger.error(e.getMessage(), e);
        }
    }

    void resume() throws SchedulerException {
        QuartzManager.resumeAll();
    }


    void addCleanFileJob() throws SchedulerException {
        Scheduler scheduler = QuartzManager.getScheduler();
        TriggerKey triggerKey = new TriggerKey(QuartzConstant.JOBNAME_CLEAN_FILE, QuartzManager.TRIGGER_GROUP_NAME);
        Trigger trigger = scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            logger.info("not set clean file quartz ... add it right...");
            logger.info(path);
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("path", path);
            QuartzManager.addJob(QuartzConstant.JOBNAME_CLEAN_FILE, new JobCleanFile(), jobDataMap, cron);
        } else {

            String cronExpression = ((CronTrigger) trigger).getCronExpression();
            logger.info("old cron is: " + cronExpression);
            if (!Objects.equals(cronExpression, cron)) {
                logger.info("new cron is: " + cron);
                logger.info("cron changing ...");

                QuartzManager.modifyJobTime(QuartzConstant.JOBNAME_CLEAN_FILE, cron);
                logger.info("cron changed to " + cron);
            }
        }
    }

}
