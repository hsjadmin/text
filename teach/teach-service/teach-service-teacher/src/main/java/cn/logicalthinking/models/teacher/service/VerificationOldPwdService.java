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
 * @note 验证新密码
 * @auhtor 卢祖飞
 * @date 2018/12/27,17:47
 */
@Service
public class VerificationOldPwdService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = data.getTeacherUser();

        ParamValidation.isNotNull(teacher,"登录失效");

        String oldPwd = data.getParameter("oldPwd");

        ParamValidation.isNotNull(oldPwd,"请输入旧密码");

        Teacher teacher1 = teacherDao.selectTeacherById(teacher.getId());

        ParamValidation.isNotNull(teacher1.getPayPwd(),"未设置提现密码");

        System.out.println(MD5.md5(oldPwd));
        System.out.println(MD5.md5(oldPwd).equals(teacher1.getPayPwd()));

        if(MD5.md5(oldPwd).equals(teacher1.getPayPwd()))
            return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(),"验证成功");
        else
            return Result.jsonMsg(CODE.CODE_400.getKey(),"旧密码验证失败");
    }
}
