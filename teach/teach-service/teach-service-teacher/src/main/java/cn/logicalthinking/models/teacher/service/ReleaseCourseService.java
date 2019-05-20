package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.constant.QuartzConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.DaoException;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.quartz.*;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import cn.logicalthinking.common.util.Tools;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @note 发布课程
 * @auhtor 卢祖飞
 * @date 2018/12/27,9:35
 */
@Service
public class ReleaseCourseService extends AbstractService {
    private static final Logger logger = Logger.getLogger(ReleaseCourseService.class);

    private String beanName = "sendNotifyToStudentImpl";

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    private IClientData data;

    private Integer notifyTime;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @SuppressWarnings("all")
    private void initNotifyTime() {
        try {
            ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName(AppParamConstant.REMINDER_BEFORE_CLASS);
            if (applicationParameter != null) {
                notifyTime = Integer.parseInt(applicationParameter.getValue());
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = data.getTeacherUser();

        Course course = (Course) data.getObject();

        //添加课程返回主键
        Integer courseId = addCourse(course, teacher.getId());
        //添加课程类别返回主键
        Integer courseTypeId = addCourseType(courseId);
        //添加课程大纲
        addCourseOutline(courseTypeId, teacher, course);

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());
    }

    //解析课程大纲
    public static List<CourseOutline> getCourseOutLine(String courseOutline) {
        List<CourseOutline> courseOutlines = new ArrayList<CourseOutline>();
        try {
            courseOutlines = JSON.parseArray(courseOutline, CourseOutline.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidataException("fileInfo解析失败");
        }
        return courseOutlines;
    }

    //解析课程类别
    public static CourseType getCourseType(String CourseTypeInfo) {
        CourseType courseType = new CourseType();
        try {
            courseType = JSON.parseObject(CourseTypeInfo, CourseType.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidataException("fileInfo解析失败");
        }
        return courseType;
    }

    //添加课程
    public Integer addCourse(Course course, Integer teacherId) {
        course.setIsShow(0);
        course.setIsFinish(0);
        course.setLiveStatus(0);
        course.setStatus(0);
        course.setTeacherId(teacherId);
        course.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
        courseDao.addCourses(course);
        System.out.println(course.getId());
        return course.getId();
    }

    //添加课程类别
    public Integer addCourseType(Integer courseId) {
        String courseTypeInfo = data.getParameter("typeInfo");
        CourseType courseType = getCourseType(courseTypeInfo);
        courseType.setCourseId(courseId);
        courseType.setQuantity(10);
        courseType.setEnrolment(0);
        courseType.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
        courseTypeDao.addCourseTypes(courseType);
        System.out.println(courseType.getId());
        return courseType.getId();
    }

    public void addCourseOutline(Integer courseTypeId, Teacher teacher, Course course) {
        String courseOutlineInfo = data.getParameter("outlineInfo");
        List<CourseOutline> courseOutlineList = getCourseOutLine(courseOutlineInfo);
        List<CourseOutline> courseOutlines = new ArrayList<>();

        for (int i = 0; i < courseOutlineList.size(); i++) {
            courseOutlineList.get(i).setCourseTypeId(courseTypeId);
            courseOutlineList.get(i).setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
            courseOutlineList.get(i).setStatus(0);
            courseOutlineList.get(i).setRank(i + 1);
            courseOutlines.add(courseOutlineList.get(i));

            try {
                addNotifyQuartz(courseTypeId, teacher, course);
            } catch (SchedulerException e) {
                logger.error("提醒添加失败");
                logger.error(e.getMessage(), e);
            }

        }
        courseOutlineDao.batchCourseOutline(courseOutlines);
    }

    /**
     * 添加提醒任务
     *
     * @param courseTypeId
     * @param teacher
     * @param course
     * @throws SchedulerException
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    private void addNotifyQuartz(Integer courseTypeId, Teacher teacher, Course course) throws SchedulerException {
        // 任务名
        String jobName = QuartzConstant.JOBNAME_COURSE_OUTLINE_TEACHER + "_" + Tools.UUID();
        // 提醒时间
        Map<String, Object> colMap = new HashMap<>(1);
        colMap.put("courseTypeId", courseTypeId);
        List<CourseOutline> courseOutlineList = courseOutlineDao.selectCourseOutlineListAll(colMap);
        for (CourseOutline courseOutline : courseOutlineList) {
            if (courseOutline == null || StringUtils.isBlank(courseOutline.getStartTime())) {
                continue;
            }


            String startTimeStr = courseOutline.getStartTime();
            Date dateParse = DateUtil.getDateParse(startTimeStr, DateUtil.YMDHM);

            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(dateParse.getTime());
            instance.add(Calendar.MINUTE, -notifyTime);
            long time = instance.getTime().getTime();
            long currentTimeMillis = System.currentTimeMillis();

            // 已过预期提醒时间
            if (time < currentTimeMillis) {
                long l = currentTimeMillis - time;
                double v = l / 1000d / 60d;
                double v1 = notifyTime - Math.ceil(v);
                if (v1 <= 0)
                    v1 = 1;
                Map<String, String> param = new HashMap<>();
                param.put("phone", teacher.getPhone());
                param.put("courseName", course.getName());
                param.put("courseTime", (int) v1 + "");
                param.put("teacherId", course.getTeacherId() + "");
                try {
                    ISendNotify iSendNotify= (ISendNotifyToStudent) SpringContextHolder.WEB_APP_CONTEXT.getBean(beanName);
                    iSendNotify.send(param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("courseName", course.getName());
                jobDataMap.put("jobName", jobName);
                jobDataMap.put("courseTime", notifyTime);
                jobDataMap.put("phone", teacher.getPhone());
                jobDataMap.put("teacherId", course.getTeacherId());
                jobDataMap.put("beanName", beanName);

                String corn = CronDateUtils.getCron(instance.getTime());
                logger.info("=========================");
                logger.info("添加任务： " + jobName);
                logger.info("corn： " + corn);
                logger.info("=========================");
                QuartzManager.addJob(jobName, new ClassRemindJob(), jobDataMap, corn);

            }

        }
    }
}
