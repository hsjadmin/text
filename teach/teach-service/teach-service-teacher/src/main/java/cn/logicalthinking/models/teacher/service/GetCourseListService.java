package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.entity.CourseOutline;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @note 查看课程大纲
 * @auhtor 卢祖飞
 * @date 2018/12/26,14:49
 */
@Service
public class GetCourseListService extends AbstractService {

    @Resource
    private CourseOutlineDao courseOutlineDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String courseTypeId = data.getParameter("courseTypeId");

        Map<String, Object> map = data.initMap();

        map.put("courseTypeId",courseTypeId);

        PageInfo<CourseOutline> pageInfo = courseOutlineDao.selectCourseOutlineBycId(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
