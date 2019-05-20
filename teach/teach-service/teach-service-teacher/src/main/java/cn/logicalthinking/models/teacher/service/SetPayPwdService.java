package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 忘记密码/首次设置密码
 * @auhtor 卢祖飞
 * @date 2018/12/26,19:09
 */
@Service
public class SetPayPwdService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String pwd = data.getParameter("pwd");

        ParamValidation.isNotNull(pwd,"请输入密码");

        Teacher teacher = data.getTeacherUser();

        Teacher teacher1 = new Teacher();

        teacher1.setId(teacher.getId());
        teacher1.setPayPwd(MD5.md5(pwd));

        teacherDao.updateTeacher(teacher1);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
