package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @note 保存录制的视频/下课
 * @auhtor 卢祖飞
 * @date 2019/1/2,10:44
 */
@Service
public class KeepVideoUrlService extends AbstractService {

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseDao courseDao;
    @Resource
    private TeacherDao teacherDao;
    @Resource
    private FinanceTeacherDao financeTeacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        ParamValidation.isNotNull(id,"id不能为空");

        //通过大纲id去修改课程状态
        CourseOutline courseOutline1 = courseOutlineDao.selectCourseOutlineById(Integer.parseInt(id));
        ParamValidation.isNotNull(courseOutline1,"大纲id不存在");
        if(courseOutline1.getStatus()==1)
            throw new ValidataException("该章节已上完");

        CourseType courseType = courseTypeDao.selectCourseTypeById(courseOutline1.getCourseTypeId());
        ParamValidation.isNotNull(courseType,"大纲对应的课程类型不存在");

        Course course=courseDao.selectCourseById(courseType.getCourseId());
        ParamValidation.isNotNull(courseType,"大纲对应的课程不存在");

        Teacher teacher= teacherDao.selectTeacherById(course.getTeacherId());
        ParamValidation.isNotNull(teacher,"课程对应的老师不存在");

        //1.修改视频地址以及大纲状态
        courseOutline1.setStatus(1);
        courseOutlineDao.updateCourseOutline(courseOutline1);

        //查询已报名人数
        Map<String, Object> map = new HashMap<>();
        map.put("courseTypeId",courseOutline1.getCourseTypeId());
        map.put("status",0);
        int num = courseOutlineDao.selectCourseOutlineCount(map);

        //2.监听该课程是否全都上完  如果都上完则把课程的状态改成已完成
        course.setLiveStatus(0);
        if(num <= 0){
            course.setIsFinish(1);
        }
        courseDao.updateCourse(course);

        //3.计算老师提成
        calculatedPrice(courseType,course,teacher);

        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(),"保存成功");
    }

    /**
     * 每当老师上完一节课，
     * 就会给老师转入指定的金额
     * 计算公式（课程总价/章节*人数）* 0.7
     */
    private  void calculatedPrice(CourseType courseType,Course course, Teacher teacher){


        BigDecimal ratio = new BigDecimal(teacher.getCharges().toString()).divide(new BigDecimal("100.0")).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal amount = courseType.getDiscount();//课程总价
        Integer enrolment = courseType.getEnrolment();//报名人数

        Map<String,Object> map=new HashedMap();
        map.put("courseTypeId",courseType.getId());
        int num = courseOutlineDao.selectCourseOutlineCount(map);//章节数
        System.out.printf("课程张数"+num);
        //3.1计算老师完成一节课获取到的提成（课程总价/章节*人数）* 0.7  ）
        BigDecimal teacherIncome1 = amount.divide(new BigDecimal(num), 2, BigDecimal.ROUND_HALF_UP);
        System.out.printf("enrolment"+enrolment);
        BigDecimal teacherIncome2=teacherIncome1.multiply(new BigDecimal(enrolment));
        BigDecimal teacherIncome=teacherIncome2.multiply(ratio).setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal balance = teacher.getBalance();
        balance = balance.add(teacherIncome);
        teacher.setBalance(balance);
        if (teacherDao.updateTeacher(teacher)== 0) {
            throw new BusinessException("余额更新失败");
        }

//        3.2 老师收支明细
        FinanceTeacher financeTeacher = new FinanceTeacher();
        financeTeacher.setMoney(teacherIncome);
        financeTeacher.setTeacherId(teacher.getId());
        financeTeacher.setName("课程报名收入");
        financeTeacher.setType(0);
        if (financeTeacherDao.addFinanceTeacher(financeTeacher) == 0) {
            throw new BusinessException("记录失败");
        }

        //3.3累计老师获取的提成
        BigDecimal commission = courseType.getCommission();
        if (commission == null) {
            commission = new BigDecimal("0.0");
        }
        BigDecimal newComission = commission.add(teacherIncome).setScale(2, BigDecimal.ROUND_HALF_UP);
        courseType.setCommission(newComission);// 提成
        if (courseTypeDao.updateCourseTypeEnrolmentAndCommissionSafely(courseType) == 0) {
            throw new BusinessException("课程信息更新失败");
        }
    }
}
