package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @note 查看老师课程
 * @auhtor 卢祖飞
 * @date 2018/12/26,11:49
 */
@Service
public class GetTeacherCoursesService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        Teacher teacher = data.getTeacherUser();

        ParamValidation.isNotNull(teacher.getId(),"登录超时");

        map.put("teacherId",teacher.getId());

        PageInfo<Course> pageInfo = courseDao.selectCourseListByTeacherId(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
