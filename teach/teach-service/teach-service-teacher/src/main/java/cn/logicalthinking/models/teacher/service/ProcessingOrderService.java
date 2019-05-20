package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 处理订单
 * @auhtor 卢祖飞
 * @date 2019/1/2,15:20
 */
@Service
public class ProcessingOrderService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacherUser = data.getTeacherUser();

        String id = data.getParameter("id");

        String status = data.getParameter("status");

        ParamValidation.isNotNull(id,"id不能为空");
        ParamValidation.isNotNull(status,"状态不能为空");

        OrderQuestion orderQuestion = new OrderQuestion();

        orderQuestion.setId(Integer.parseInt(id));
        orderQuestion.setStatus(Integer.parseInt(status));
        orderQuestionDao.updateOrderQuestion(orderQuestion);
        Teacher teacher = new Teacher();
        teacher.setId(teacherUser.getId());
        if(status == "1" || "1".equals(status)){
            teacher.setIsOnline(1);
            teacherDao.updateTeacher(teacher);
        }
        if(status == "4" || "4".equals(status)){
            teacher.setIsOnline(2);
            teacherDao.updateTeacher(teacher);
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
