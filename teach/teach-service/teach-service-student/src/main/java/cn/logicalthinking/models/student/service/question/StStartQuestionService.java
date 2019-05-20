package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
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
 * @Description 开始答疑
 * @date 2019-1-2
 */
@Service
public class StStartQuestionService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        String teacherId = data.getParameter("teacherId");
        ParamValidation.isNotNull(teacherId, "缺失老师id");

        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(teacherId));

        if (teacher == null) {
            throw new BusinessException("老师数据异常,请重试");
        }

        if (teacher.getIsOnline() == 1) {
            throw new BusinessException("老师正在答疑中");
        }
        if (teacher.getIsOnline() == 0) {
            throw new BusinessException("老师不在线");
        }

        BigDecimal startingPrice = teacher.getStartingPrice();
        BigDecimal studentUserBalance = studentUser.getBalance();
        if (startingPrice == null) {
            throw new BusinessException("老师未设置起步价格");
        }
        if (studentUserBalance == null) {
            studentUserBalance = new BigDecimal("0");
        }
        if (studentUserBalance.compareTo(startingPrice) == -1) {
            return Result.jsonMsg(CODE.CODE_401.getKey(), "您的余额不足" + startingPrice.toString() + "元，还不能进行解疑，赶快进行充值吧~");
        }

        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(), "余额充足");
    }

}
