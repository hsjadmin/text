package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 修改密码
 * @auhtor 卢祖飞
 * @date 2018/12/26,19:17
 */
@Service
public class ChangePasswordService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacher = data.getTeacherUser();
        //新密码
        String pwd1 = data.getParameter("pwd1");
        //确认新密码
        String pwd2 = data.getParameter("pwd2");

        isNull(teacher,pwd1,pwd2);

        pwd1 = pwd1.trim();
        pwd2 = pwd2.trim();

        if(!pwd1.equals(pwd2)){
            throw new ValidataException("两次输入的新密码不一致");
        }

        Teacher teacher1 = new Teacher();

        teacher1.setId(teacher.getId());
        teacher1.setPayPwd(MD5.md5(pwd1));

        teacherDao.updateTeacher(teacher1);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }


    private void isNull(Teacher teacher,String pwd1,String pwd2){
        ParamValidation.isNotNull(teacher,"登录超时,请重新登录");
        ParamValidation.isNotNull(pwd1,"请输入新密码");
        ParamValidation.isNotNull(pwd2,"请再次输入新密码");
    }

}
