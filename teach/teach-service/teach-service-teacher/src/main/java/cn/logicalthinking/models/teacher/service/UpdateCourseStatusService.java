package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 修改异常的上课状态
 * @auhtor xhx
 * @date 2018/12/25,9:52
 */
@Service
public class UpdateCourseStatusService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacherUser = data.getTeacherUser();

        if (courseDao.updateCourseStatus(teacherUser.getId()) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonData("200", "操作成功");
    }
}
