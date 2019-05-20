package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 隐藏或显示老师课程
 * @date 2018-12-19
 */
@Service
public class MaSwitchShowService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String courseId = data.getParameter("courseId");
        ParamValidation.isNotNull(courseId, "课程id不能为空");
        Integer id = Integer.valueOf(courseId);
        Course course = new Course();
        course.setId(id);

        Course selectCourseById = courseDao.selectCourseById(id);
        if (selectCourseById == null) {
            throw new BusinessException("没有该课程");
        }
        Integer isShow = selectCourseById.getIsShow();

        if (isShow == null || isShow == 0) {
            course.setIsShow(1);
        } else {
            course.setIsShow(0);
        }

        if (courseDao.updateCourse(course) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
