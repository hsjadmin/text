package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @note 修改疑难解答订单时间
 * @auhtor 卢祖飞
 * @date 2019/1/2,11:36
 */
@Service
public class SetUpProblemTimeService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String oid = data.getParameter("id");

        String answerTime = data.getParameter("answerTime");
        ParamValidation.isNotNull(oid, "id不能为空");
        ParamValidation.isNotNull(answerTime, "计时不能为空");

        //查询订单详情
        OrderQuestion orderQuestion = orderQuestionDao.selectOrderQuestionById(oid);
        //老师信息
        Teacher teacher = teacherDao.selectTeacherById(orderQuestion.getTeacherId());

        //系统参数
        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName("startingTime");

        //答疑时间
        Double answeringTime = Double.parseDouble(answerTime);

        //起步价
        BigDecimal startingPrice = teacher.getStartingPrice();

        //起步时间
        Double startingTime = Double.parseDouble(applicationParameter.getValue());
        BigDecimal price = null;

        if (answeringTime < startingTime) {
            price = startingPrice;
        } else {
            Double time = answeringTime - startingTime;
            time = Math.ceil(time);
            price = startingPrice.add(teacher.getChargeSettings().multiply(new BigDecimal(time)));
        }

        OrderQuestion orderQuestion1 = new OrderQuestion();
        orderQuestion1.setId(Integer.parseInt(oid));
        orderQuestion1.setPrice(price);
        orderQuestion1.setStatus(2);
        orderQuestion1.setAnsweringTime(answerTime);

        orderQuestionDao.updateOrderQuestion(orderQuestion1);

        Teacher teacher1 = new Teacher();
        teacher1.setId(teacher.getId());
        teacher1.setIsOnline(2);
        teacherDao.updateTeacher(teacher);


        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), "保存成功");
    }
}
