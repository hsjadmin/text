package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note 修改显示状态
 * @auhtor 卢祖飞
 * @date 2018/12/26,14:23
 */
@Service
public class UpdateCourseShowTypeService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String cId = data.getParameter("cId");

        String isShow = data.getParameter("isShow");

        Course course = new Course();

        course.setId(Integer.parseInt(cId));

        course.setIsShow(Integer.parseInt(isShow));

        courseDao.updateCourse(course);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
    }
}
