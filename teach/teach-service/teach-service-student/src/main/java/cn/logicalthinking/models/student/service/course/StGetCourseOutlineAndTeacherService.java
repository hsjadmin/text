package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.ValidataException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询章节信息和老师详情接口
 * @date 2018-1-2
 */
@Service
public class StGetCourseOutlineAndTeacherService extends AbstractService {

    @Resource
    private CourseOutlineDao courseOutlineDao;
    @Resource
    private CourseTypeDao courseTypeDao;
    @Resource
    private CourseDao courseDao;

    @Resource
    private TeacherDao teacherDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        CourseOutline courseOutline = courseOutlineDao.selectCourseOutlineById(Integer.parseInt(id));

        if (courseOutline == null) {
            throw new BusinessException("没有该大纲");
        }
        Integer courseTypeId = courseOutline.getCourseTypeId();
        CourseType courseType = courseTypeDao.selectCourseTypeById(courseTypeId);
        if (courseOutline == null) {
            throw new BusinessException("大纲数据异常");
        }
        Course course = courseDao.selectCourseById(courseType.getCourseId());
        if (courseOutline == null) {
            throw new BusinessException("大纲数据异常");
        }
        Teacher teacher = teacherDao.selectTeacherById(course.getTeacherId());
        if (courseOutline == null) {
            throw new BusinessException("大纲数据异常");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseOutline", courseOutline);
        jsonObject.put("teacher", teacher);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
