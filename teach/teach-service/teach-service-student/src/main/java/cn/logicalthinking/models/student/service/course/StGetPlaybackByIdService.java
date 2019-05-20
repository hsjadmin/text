package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询章节视频回放
 * @date 2018-1-2
 */
@Service
public class StGetPlaybackByIdService extends AbstractService {

    @Resource
    private
    CourseOutlineDao courseOutlineDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        CourseOutline courseOutline = courseOutlineDao.selectCourseOutlineById(Integer.parseInt(id));

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), courseOutline);
    }

}
