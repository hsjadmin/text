package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生表 修改
 * @date 2018-12-19
 */
@Service
public class StUpdatePINService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Student studentUser = data.getStudentUser();

        String newPIN = data.getParameter("newPIN");
        ParamValidation.isNotNull(newPIN, "未输入新支付密码");

        studentUser.setPin(MD5.md5(newPIN));
        studentUser.setHasPin("1");
        if (studentDao.updateStudent(studentUser) == 0) {
            throw new BusinessException("修改失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), "修改成功");

    }

}
