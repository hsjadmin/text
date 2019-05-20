package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 根据id查询，带课程类型，课程大纲
 * @date 2018-12-19
 */
@Service
public class MaGetCourseTypeByIdService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");
        int i = Integer.parseInt(id);

        Course course = courseDao.selectCourseByIdWithCourseTypeAndCourseOutline(i);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), course);
    }

}
