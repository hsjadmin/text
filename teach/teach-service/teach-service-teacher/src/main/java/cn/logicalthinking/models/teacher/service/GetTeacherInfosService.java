package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @note 查询审核信息
 * @auhtor 卢祖飞
 * @date 2018/12/26,17:33
 */
@Service
public class GetTeacherInfosService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource(name = "teacherUserLoginService")
    private TeacherUserLoginService teacherUserLoginService;


    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String userId = data.getParameter("userId");

        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(userId));

        ParamValidation.isNotNull(teacher,"用户不存在!");

        if("2".equals(teacher.getExamine())){
            return teacherUserLoginService.teacherLogin(teacher);
        }
        return Result.jsonData(CODE.CODE_303.getKey(),teacher);
    }
}
