package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/8,16:30
 */
@Service
public class SetUpchannelNoService extends AbstractService {

    @Resource
    private CourseOutlineDao courseOutlineDao;
    @Resource
    private CourseTypeDao courseTypeDao;
    @Resource
    private CourseDao courseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //大纲id
        String id = data.getParameter("id");
        CourseOutline courseOutline= courseOutlineDao.selectCourseOutlineById(Integer.parseInt(id));
        ParamValidation.isNotNull(courseOutline,"大纲不存在");
        //频道号
        String channelNo = data.getParameter("channelNo");

        courseOutline.setChannelNo(channelNo);
        courseOutline.setStatus(2);
        courseOutlineDao.updateCourseOutline(courseOutline);

        CourseType courseType = courseTypeDao.selectCourseTypeById(courseOutline.getCourseTypeId());
        ParamValidation.isNotNull(courseType,"课程类型不存在");
        Course course = new Course();
        course.setId(courseType.getCourseId());
        course.setLiveStatus(1);
        courseDao.updateCourse(course);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),"保存成功");
    }
}
