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
 * @note 疑难上下线
 * @auhtor 卢祖飞
 * @date 2019/1/5,10:26
 */
@Service
public class OnLineQuestionService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Teacher teacherUser = data.getTeacherUser();

        String isOnlineStatus = data.getParameter("isOnlineStatus");

        Teacher teacher = new Teacher();
        teacher.setId(teacherUser.getId());
        teacher.setIsOnline(Integer.parseInt(isOnlineStatus));

        teacherDao.updateTeacher(teacher);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
