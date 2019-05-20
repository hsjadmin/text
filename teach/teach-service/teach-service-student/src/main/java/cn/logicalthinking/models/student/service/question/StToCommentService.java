package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.QuestionCommentDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.QuestionComment;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/5,13:54
 */
@Service
public class StToCommentService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private QuestionCommentDao questionCommentDao;

    @Resource
    private StudentDao studentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        //疑难订单id
        String oId = data.getParameter("oid");

        //星星数量
        String start = data.getParameter("start");
        //评论内容
        String context = data.getParameter("context");

        ParamValidation.isNotNull(oId, "订单id为空");
        ParamValidation.isNotNull(start, "请选择星星数");
        ParamValidation.isNotNull(context, "请输入评论内容");

        //获取订单信息
        OrderQuestion orderQuestion = orderQuestionDao.selectOrderQuestionById(oId);
        // 是否可评论
        if ("1".equals(orderQuestion.getApprised())) {
            throw new BusinessException("订单已评论，请勿重复评论");
        }

        Integer startNum = Integer.parseInt(start);

        //获取学生信息
        Student student = studentDao.selectStudentById(orderQuestion.getStudentId());

        QuestionComment questionComment = new QuestionComment();
        questionComment.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
        questionComment.setOrderId(Integer.parseInt(oId));
        questionComment.setPicture(student.getPicture());
        questionComment.setTeacherId(orderQuestion.getTeacherId());
        if (startNum < 3)
            questionComment.setType(0);
        if (startNum == 3)
            questionComment.setType(1);
        if (startNum > 3)
            questionComment.setType(2);
        questionComment.setStar(start);
        questionComment.setStudentId(orderQuestion.getStudentId());
        questionComment.setName(orderQuestion.getStudentName());
        questionComment.setComment(context);

        questionCommentDao.addQuestionComment(questionComment);

        OrderQuestion orderQuestion1 = new OrderQuestion();
        orderQuestion1.setId(Integer.parseInt(oId));
        orderQuestion1.setApprised("1");
        orderQuestionDao.updateOrderQuestion(orderQuestion1);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());
    }
}
