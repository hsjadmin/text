package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Teacher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 查询个人信息
 * @auhtor 卢祖飞
 * @date 2018/12/26,11:17
 */
@Service
public class TeacherSelfInfoService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacherUser = data.getTeacherUser();

        Teacher teacher = teacherDao.selectTeacherById(teacherUser.getId());

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),teacher);
    }
}
