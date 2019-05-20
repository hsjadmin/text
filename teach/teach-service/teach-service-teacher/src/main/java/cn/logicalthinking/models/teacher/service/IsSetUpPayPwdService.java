package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 验证是否设置提现密码
 * @auhtor 卢祖飞
 * @date 2019/1/2,10:36
 */
@Service
public class IsSetUpPayPwdService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacherUser = data.getTeacherUser();

        Teacher teacher = teacherDao.selectTeacherById(teacherUser.getId());

        if(StringUtils.isNotBlank(teacher.getPayPwd())){
            return Result.jsonData(CODE.CODE_200_SELECT.getKey(),true);
        }
            return Result.jsonData(CODE.CODE_200_SELECT.getKey(),false);
    }
}
