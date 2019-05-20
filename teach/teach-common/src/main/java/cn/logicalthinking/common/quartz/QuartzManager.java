package cn.logicalthinking.common.quartz;

import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import java.text.ParseException;
import java.util.Date;

/**
 * 定时器管理工具
 *
 * @author XHX
 * @date 2018/9/5
 */
public class QuartzManager {

    private static Logger logger = Logger.getLogger(QuartzManager.class);

    /**
     * 调度器
     */
    private static Scheduler scheduler = getScheduler();
    /**
     * 默认任务组名
     */
    public static String JOB_GROUP_NAME = "DEFAULT_JOB_GROUP";
    /**
     * 默认触发器组名
     */
    public static String TRIGGER_GROUP_NAME = "DEFAULT_TRIGGER_GROUP";

    public static Scheduler getScheduler() {

        if (scheduler == null) {
            try {
                SchedulerFactory schedulerFactory = new StdSchedulerFactory();
                scheduler = schedulerFactory.getScheduler();
            } catch (SchedulerException e) {
                logger.info("调度器获取异常", e);
                return null;
            }
        }

        return scheduler;
    }

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName 任务名
     * @param job     任务
     * @param time    时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, Job job, JobDataMap jobDataMap, String time)
            throws SchedulerException {
        addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, job, jobDataMap, time);
    }

    /**
     * 添加一个定时任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param job              任务
     * @param time             时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName,
                              Job job, JobDataMap jobDataMap, String time)
            throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(jobName, jobGroupName)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();//任务名，任务组，任务执行类
        //触发器
        CronTrigger trigger = TriggerBuilder.newTrigger()
                //触发器名,触发器组
                .withIdentity(triggerName, triggerGroupName)
                //触发器时间设定
                .withSchedule(CronScheduleBuilder.cronSchedule(time))
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        //启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName 任务名
     * @param job     任务
     * @param time    时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, Job job, JobDataMap jobDataMap, String time, Date startAt, Date endAt)
            throws SchedulerException {
        addJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME, job, jobDataMap, time, startAt, endAt);
    }

    /**
     * 添加一个定时任务
     *
     * @param jobName          任务名
     * @param jobGroupName     任务组名
     * @param triggerName      触发器名
     * @param triggerGroupName 触发器组名
     * @param job              任务
     * @param time             时间设置，参考quartz说明文档
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void addJob(String jobName, String jobGroupName,
                              String triggerName, String triggerGroupName,
                              Job job, JobDataMap jobDataMap, String time, Date startAt, Date endAt)
            throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(job.getClass())
                .withIdentity(jobName, jobGroupName)
                .setJobData(jobDataMap)
                .storeDurably()
                .build();//任务名，任务组，任务执行类
        //触发器
        CronTrigger trigger = TriggerBuilder.newTrigger()
                //触发器名,触发器组
                .withIdentity(triggerName, triggerGroupName)
                //触发器时间设定
                .withSchedule(CronScheduleBuilder.cronSchedule(time))
                .startAt(startAt)
                .endAt(endAt)
                .build();
        scheduler.scheduleJob(jobDetail, trigger);
        //启动
        if (!scheduler.isShutdown()) {
            scheduler.start();
        }
    }

    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param triggerName
     * @param time
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void modifyJobTime(String triggerName, String time) throws SchedulerException {
        modifyJobTime(triggerName, TRIGGER_GROUP_NAME, time);
    }

    /**
     * 修改一个任务的触发时间
     *
     * @param triggerName
     * @param triggerGroupName
     * @param time
     * @throws SchedulerException
     * @throws ParseException
     */
    public static void modifyJobTime(String triggerName, String triggerGroupName,
                                     String time)
            throws SchedulerException {
        TriggerKey key = TriggerKey.triggerKey(triggerName, triggerGroupName);
        //表达式构建器
        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(time);
        CronTrigger trigger = (CronTrigger) TriggerBuilder.newTrigger().withIdentity(key).withSchedule(cronSchedule).build();
        try {
            scheduler.rescheduleJob(key, trigger);
        } catch (SchedulerException e) {
            System.out.println("更改表达式失败");
            e.printStackTrace();
        }

    }

    /** */
    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName
     * @throws SchedulerException
     */
    public static void removeJob(String jobName)
            throws SchedulerException {
        removeJob(jobName, JOB_GROUP_NAME, jobName, TRIGGER_GROUP_NAME);
    }

    /**
     * 移除一个任务
     *
     * @param jobName
     * @param jobGroupName
     * @param triggerName
     * @param triggerGroupName
     * @throws SchedulerException
     */
    public static void removeJob(String jobName, String jobGroupName,
                                 String triggerName, String triggerGroupName)
            throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        JobKey jobKey = JobKey.jobKey(jobName, jobGroupName);
        //停止触发器
        scheduler.pauseTrigger(triggerKey);
        //移除触发器
        scheduler.unscheduleJob(triggerKey);
        //删除任务
        scheduler.deleteJob(jobKey);

    }

    /**
     * 暂停触发器
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 组名
     * @throws SchedulerException
     */
    public static void pauseTrigger(String triggerName, String triggerGroupName)
            throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        //停止触发器
        scheduler.pauseTrigger(triggerKey);
    }

    /**
     * 暂停触发器
     *
     * @param triggerGroupName 组名
     * @throws SchedulerException
     */
    public static void pauseTrigger(String triggerGroupName)
            throws SchedulerException {
        //停止触发器
        GroupMatcher<TriggerKey> gm = GroupMatcher.groupEquals(triggerGroupName);
        scheduler.pauseTriggers(gm);
    }

    /**
     * 重启触发器
     *
     * @param triggerName      触发器名
     * @param triggerGroupName 组名
     * @throws SchedulerException
     */
    public static void resumeTrigger(String triggerName, String triggerGroupName)
            throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        //重启触发器
        scheduler.resumeTrigger(triggerKey);
        scheduler.start();
    }

    /**
     * 重启触发器
     *
     * @param triggerGroupName 组名
     * @throws SchedulerException
     */
    public static void resumeTrigger(String triggerGroupName)
            throws SchedulerException {
        GroupMatcher<TriggerKey> gm = GroupMatcher.groupEquals(triggerGroupName);
        //重启触发器
        scheduler.resumeTriggers(gm);
        scheduler.start();
    }

    /**
     * 重启所有触发器
     *
     * @throws SchedulerException
     */
    public static void resumeAll() throws SchedulerException {

        scheduler.resumeAll();
        scheduler.start();
    }

}