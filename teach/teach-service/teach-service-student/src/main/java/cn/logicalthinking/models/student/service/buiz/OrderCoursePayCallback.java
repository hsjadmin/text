package cn.logicalthinking.models.student.service.buiz;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.constant.OrderNoConstant;
import cn.logicalthinking.common.base.constant.QuartzConstant;
import cn.logicalthinking.common.base.enums.OrderCourseStatus;
import cn.logicalthinking.common.base.enums.SmsEnum;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.DaoException;
import cn.logicalthinking.common.pay.Callback;
import cn.logicalthinking.common.quartz.*;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import cn.logicalthinking.common.util.TencentSMS;
import cn.logicalthinking.common.util.Tools;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Scope("prototype")
public class OrderCoursePayCallback extends Callback {

    private static final Logger logger = Logger.getLogger(OrderCoursePayCallback.class);

    private String beanName = "sendNotifyToStudentImpl";

    public OrderCoursePayCallback() {
        Map<String, Object> result = new HashMap<>(1);
        result.put("callbackName", "orderCoursePayCallback");
        init(result);
    }


    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private CouponDao couponDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private FinanceStudentDao financeStudentDao;

    @Resource
    private FinanceDao financeDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private FinanceTeacherDao financeTeacherDao;

    @Resource
    private OrderStudentDao orderStudentDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    private Integer notifyTime;

    @Override
    public boolean onProcessed() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.COURSE, "");
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(orderNo));
        if (orderCourse == null) {
            throw new BusinessException("订单异常");
        }
        return !OrderCourseStatus.PREPARE.getKey().equals(orderCourse.getStatus()) && !OrderCourseStatus.PAYING.getKey().equals(orderCourse.getStatus());
    }

    @Override
    public void onSuccess() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.COURSE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        // 获取通知时间
        initNotifyTime();

        // 1.修改状态
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(orderNo));
        if (orderCourse == null) {
            throw new BusinessException("订单异常");
        }
        orderCourse.setStatus(OrderCourseStatus.PAID.getKey());
        orderCourse.setUpdateTime(timeEnd);
        if (orderCourseDao.updateOrderCourse(orderCourse) == 0) {
            throw new BusinessException("订单状态修改失败");
        }

        // 2.添加记录
        FinanceStudent financeStudent = new FinanceStudent();
        financeStudent.setMoney(new BigDecimal(orderCourse.getAmount().toString()));
        financeStudent.setStudentId(orderCourse.getStudentId());
        Student student = studentDao.selectStudentById(orderCourse.getStudentId());
        financeStudent.setName("课程报名支出");
        financeStudent.setType(0);
        if (financeStudentDao.addFinanceStudent(financeStudent) == 0) {
            throw new BusinessException("记录失败");
        }


        // 2.2.修改优惠券状态
        if(orderCourse.getCouponId()!=null){
            Coupon coupon = new Coupon();
            coupon.setId(orderCourse.getCouponId());
            coupon.setStatus(1);
            if (couponDao.updateCoupon(coupon) == 0) {
                throw new BusinessException("操作失败");
            }
        }


        // 3.添加老师收益
        Teacher teacher = teacherDao.selectTeacherById(orderCourse.getTeacherId());
        if (teacher == null) {
            throw new BusinessException("老师数据异常");
        }
//        支付金额
        BigDecimal amount = orderCourse.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal ratio = new BigDecimal(teacher.getCharges().toString()).divide(new BigDecimal("100.0")).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal teacherIncome = amount.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_UP);
        // 老师收益比
//        BigDecimal ratio = new BigDecimal(teacher.getCharges().toString()).divide(new BigDecimal("100.0")).setScale(2, BigDecimal.ROUND_HALF_UP);
//        BigDecimal teacherIncome = amount.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_UP);
//        String updateTime = teacher.getUpdateTime();
//        BigDecimal balance = teacher.getBalance();
//        balance = balance.add(teacherIncome);
//        Map<String, Object> balanceMap = new HashMap<>(3);
//        balanceMap.put("id", teacher.getId());
//        balanceMap.put("updateTime", updateTime);
//        balanceMap.put("balance", balance);
//        if (teacherDao.updateBalanceSafeLy(balanceMap) == 0) {
//            throw new BusinessException("余额更新失败");
//        }

        // 3.2 老师收支明细
//        FinanceTeacher financeTeacher = new FinanceTeacher();
//        financeTeacher.setMoney(teacherIncome);
//        financeTeacher.setTeacherId(teacher.getId());
//        financeTeacher.setName("课程报名收入");
//        financeTeacher.setType(0);
//        if (financeTeacherDao.addFinanceTeacher(financeTeacher) == 0) {
//            throw new BusinessException("记录失败");
//        }

        // 3.3 修改已报名人数,老师提成
        Integer courseTypeId = orderCourse.getCourseTypeId();
        CourseType courseType = courseTypeDao.selectCourseTypeById(courseTypeId);
        String courseTypeUpdateTime = courseType.getUpdateTime();
        Integer enrolment = courseType.getEnrolment();

        CourseType temp = new CourseType();
        temp.setId(courseTypeId);
        temp.setUpdateTime(courseTypeUpdateTime);

        temp.setEnrolment(enrolment + 1);// 人数

//        BigDecimal commission = courseType.getCommission().setScale(2, BigDecimal.ROUND_HALF_UP);;
//        if (commission == null) {
//            commission = new BigDecimal("0.0");
//        }
//        BigDecimal newComission = commission.add(teacherIncome).setScale(2, BigDecimal.ROUND_HALF_UP);
//        // 提成
//        temp.setCommission(newComission);

        if (courseTypeDao.updateCourseTypeEnrolmentAndCommissionSafely(temp) == 0) {
            throw new BusinessException("课程信息更新失败");
        }

        // 4.添加系统的财务管理
        Finance finance = new Finance();
        finance.setStudentName(student.getName());
        finance.setTeacherName(teacher.getName());
        finance.setStudentPay(orderCourse.getAmount());
        finance.setPlatformIncome(amount.subtract(teacherIncome));
        finance.setTeacherBonusRatio(teacher.getCharges());
        finance.setTeacherIncome(teacherIncome);
        if (financeDao.addFinance(finance) == 0) {
            throw new BusinessException("记录失败");
        }

        // 5.添加学生订单
        OrderStudent orderStudent = new OrderStudent();
        orderStudent.setStudentName(student.getName());
        orderStudent.setGender(student.getGender());
        orderStudent.setAddress(orderCourse.getAddress());
        orderStudent.setPhone(orderCourse.getPhone());
        orderStudent.setUserName(student.getUserName());
        orderStudentDao.addOrderStudent(orderStudent);

        // 6.发生购买成功短信
        Course course = courseDao.selectCourseById(courseType.getCourseId());
        try {
            sendSmsNotify(course, courseType, student, teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 7.添加提醒任务
        try {
            addNotifyQuartz(courseTypeId, student, course);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

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

    /**
     * 发生短信
     *
     * @param course
     * @param courseType
     * @param student
     * @param teacher
     * @return
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    private Course sendSmsNotify(Course course, CourseType courseType, Student student, Teacher teacher) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(DateUtil.DATE_TIME);
        String startTime = courseType.getStartTime();
        Date parse = format.parse(startTime);
        String date = DateUtil.getDateStr(parse, DateUtil.DATE);
        String time = DateUtil.getDateStr(parse, DateUtil.TIME);
        String[] args = {teacher.getName(), course.getName(), date,time, notifyTime.toString()};
        TencentSMS.sengSMS1(student.getPhone(), SmsEnum.FOR_BUY, args);
        return course;
    }

    /**
     * 添加提醒任务
     *
     * @param courseTypeId
     * @param student
     * @param course
     * @throws SchedulerException
     */
    @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 2000L, multiplier = 1.5))
    private void addNotifyQuartz(Integer courseTypeId, Student student, Course course) throws SchedulerException {
        // 任务名
        String jobName = QuartzConstant.JOBNAME_COURSE_OUTLINE_STUDENT + "_" + Tools.UUID();
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
                param.put("phone", student.getPhone());
                param.put("courseName", course.getName());
                param.put("courseTime", (int) v1 + "");
                param.put("studentId", student.getId() + "");
                try {
                    ISendNotify iSendNotify = (ISendNotifyToStudent) SpringContextHolder.WEB_APP_CONTEXT.getBean(beanName);
                    iSendNotify.send(param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {

                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("courseName", course.getName());
                jobDataMap.put("jobName", jobName);
                jobDataMap.put("courseTime", notifyTime);
                jobDataMap.put("phone", student.getPhone());
                jobDataMap.put("studentId", student.getId());
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

    @Override
    public void onFail() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.COURSE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        // 修改状态
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(orderNo));
        if (orderCourse == null) {
            throw new BusinessException("订单异常");
        }
        orderCourse.setStatus(OrderCourseStatus.PAY_FAIL.getKey());
        orderCourse.setUpdateTime(timeEnd);
        if (orderCourseDao.updateOrderCourse(orderCourse) == 0) {
            throw new BusinessException("订单状态修改失败");
        }
    }

    @Override
    public void onTimeout() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.COURSE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        // 修改状态
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(orderNo));
        if (orderCourse == null) {
            throw new BusinessException("订单异常");
        }

        // 关闭订单
        orderCourse.setStatus(OrderCourseStatus.CLOSED.getKey());
        orderCourse.setUpdateTime(timeEnd);
        if (orderCourseDao.updateOrderCourse(orderCourse) == 0) {
            throw new BusinessException("订单状态修改失败");
        }
    }
}
