package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.CourseTypeEnum;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程类型表，即大班课小班课一对一等课程 添加
 * {// course
 * ...
 * [//courseType
 * ...
 * [// courseOutLine
 * ...
 * ],
 * [// courseOutLine
 * ...
 * ],
 * ]
 * }
 * @date 2018-12-19
 */
@Service
public class MaAddCourseService extends AbstractService {

    @Resource
    private CourseDao courseDao;
    @Resource
    private CourseTypeDao courseTypeDao;
    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Override
    protected void before(IClientData data) {
        String parameter = data.getParameter("course");
        Course course = JSON.parseObject(parameter, Course.class);
        ParamValidation.isNotNull(course, "课程为空");
        ParamValidation.isNotNull(course.getTeacherId(), "未选择老师");
        ParamValidation.isNotNull(course.getCourseTypeList(), "课程类型为空");

        List<CourseType> courseTypeList = course.getCourseTypeList();
        for (CourseType courseType : courseTypeList) {
            ParamValidation.isNotNull(courseType.getCourseOutlineList(), "课程大纲为空");
        }
    }

    protected Result doWork(IClientData data) throws Exception {

        String parameter = data.getParameter("course");
        Course course = JSON.parseObject(parameter, Course.class);

        if (courseDao.addCourse(course) == 0) {
            throw new BusinessException("课程添加失败");
        }

        List<CourseType> courseTypeList = course.getCourseTypeList();
        for (CourseType courseType : courseTypeList) {
            List<CourseOutline> courseOutlineList = courseType.getCourseOutlineList();
            // 设置课程id
            courseType.setCourseId(course.getId());
            // 默认只有小班课，10人
            courseType.setCourseType(CourseTypeEnum.SMALL.getKey());
            courseType.setQuantity(10);
            courseType.setEnrolment(0);

            if (courseTypeDao.addCourseType(courseType) == 0) {
                throw new BusinessException("课程类型添加失败");
            }

            for (CourseOutline courseOutline : courseOutlineList) {
                // 设置课程类型id
                courseOutline.setCourseTypeId(courseType.getId());
                courseOutlineDao.addCourseOutline(courseOutline);
            }

        }

        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }


}
