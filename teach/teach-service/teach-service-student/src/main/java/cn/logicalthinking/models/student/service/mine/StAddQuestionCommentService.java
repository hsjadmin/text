package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.QuestionCommentDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.QuestionComment;
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
 * @Description 解疑评论表 添加
 * @date 2018-12-19
 */
@Service
public class StAddQuestionCommentService extends AbstractService {

    @Resource
    private QuestionCommentDao questionCommentDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected void before(IClientData data) {
        QuestionComment questionComment = (QuestionComment) data.getObject();
        ParamValidation.isNotNull(questionComment.getOrderId(), "未选择评论课程id");
        ParamValidation.isNotNull(questionComment.getStar(), "未选择评分");
    }

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        QuestionComment questionComment = (QuestionComment) data.getObject();
        Integer orderId = questionComment.getOrderId();
        if (orderId == null) {
            throw new BusinessException("找不到评论的订单");
        }
        OrderQuestion temp = orderQuestionDao.selectOrderQuestionById(String.valueOf(orderId));
        if (temp == null) {
            throw new BusinessException("没有该订单");
        }
        // 是否可评论
        if ("1".equals(temp.getApprised())) {
            throw new BusinessException("订单已评论，请勿重复评论");
        }


        OrderQuestion orderQuestion = new OrderQuestion();
        orderQuestion.setId(orderId);
        orderQuestion.setApprised("1");
        if (orderQuestionDao.updateOrderQuestion(orderQuestion) == 0) {
            throw new BusinessException("评论失败");
        }

        orderQuestion = orderQuestionDao.selectOrderQuestionById(String.valueOf(orderId));

        if (orderQuestion == null) {
            throw new BusinessException("找不到订单");
        }

        Student studentUser = data.getStudentUser();

        questionComment.setStudentId(studentUser.getId());
        questionComment.setName(studentUser.getName());
        questionComment.setPicture(studentUser.getPicture());
        questionComment.setTeacherId(orderQuestion.getTeacherId());
        questionComment.setQuestionImg(orderQuestion.getQuestionImg());

        String star = questionComment.getStar();

        if ("3".compareTo(star) == 1) {
            questionComment.setType(2);
        } else if ("3".compareTo(star) == 0) {
            questionComment.setType(1);
        } else {
            questionComment.setType(0);
        }


        if (questionCommentDao.addQuestionComment(questionComment) == 0) {
            throw new BusinessException("评价失败");
        }

        // 添加老师评论
        Integer teacherId = orderQuestion.getTeacherId();
        Teacher teacher = teacherDao.selectTeacherById(teacherId);
        if (teacher == null) {
            throw new BusinessException("数据异常,无法评论");
        }
        teacher.setStarCount(teacher.getStarCount().add(new BigDecimal(questionComment.getStar())));
        teacher.setStarTimes(teacher.getStarTimes() + 1);
        if (teacherDao.updateTeacher(teacher) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }

}
