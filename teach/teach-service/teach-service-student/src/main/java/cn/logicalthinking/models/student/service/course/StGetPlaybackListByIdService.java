package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.exception.ValidataException;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询章节视频回放
 * @date 2018-1-2
 */
@Service
public class StGetPlaybackListByIdService extends AbstractService {

    @Resource
    private
    CourseOutlineDao courseOutlineDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        HashMap<String, Object> map = new HashMap<>(2);
        map.put("courseTypeId", id);

        // 已上课
        map.put("status", 1);
        PageInfo<CourseOutline> pageInfo = courseOutlineDao.selectCourseOutlineBycId(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
