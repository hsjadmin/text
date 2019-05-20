package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseCommentDao;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.CourseComment;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程评论表 添加
 * @date 2018-12-26
 */
@Service
public class StAddCourseCommentService extends AbstractService {

    @Resource
    private CourseCommentDao courseCommentDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private OrderCourseDao orderCourseDao;

    @Override
    protected void before(IClientData data) {

        CourseComment courseComment = (CourseComment) data.getObject();

        ParamValidation.isNotNull(courseComment.getStart(), "未选择评分");
        ParamValidation.isNotNull(courseComment.getOrderCourseId(), "未选择评论订单");
    }

    protected Result doWork(IClientData data) throws Exception {

        CourseComment courseComment = (CourseComment) data.getObject();

        Student studentUser = data.getStudentUser();

        Integer orderCourseId = courseComment.getOrderCourseId();
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(orderCourseId);

        if (orderCourse == null) {
            throw new BusinessException("没有该订单");
        }

        if("1".equals(orderCourse.getApprised())){
            throw new BusinessException("订单已评论");
        }


        // 添加课程评分
        courseComment.setStudentId(studentUser.getId());
        courseComment.setPicture(studentUser.getPicture());
        courseComment.setName(studentUser.getName());
        courseComment.setCourseId(orderCourse.getCourseId());

        Integer star = courseComment.getStart();

        if (star > 3) {
            courseComment.setType(0);
        } else if (star == 3) {
            courseComment.setType(1);
        } else {
            courseComment.setType(2);
        }

        if (courseCommentDao.addCourseComment(courseComment) == 0) {
            throw new BusinessException("操作失败");
        }


        // 添加老师评分

        Integer teacherId = orderCourse.getTeacherId();
        Teacher teacher = teacherDao.selectTeacherById(teacherId);
        if (teacher == null) {
            throw new BusinessException("数据异常,无法评论");
        }
        teacher.setStarCount(teacher.getStarCount().add(new BigDecimal(courseComment.getStart())));
        teacher.setStarTimes(teacher.getStarTimes() + 1);
        if (teacherDao.updateTeacher(teacher) == 0) {
            throw new BusinessException("操作失败");
        }

        // 修改订单状态

        OrderCourse temp = new OrderCourse();
        temp.setId(orderCourse.getId());
        temp.setApprised("1");

        if (orderCourseDao.updateOrderCourse(temp) == 0) {
            throw new BusinessException("评论失败");
        }

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }


}
