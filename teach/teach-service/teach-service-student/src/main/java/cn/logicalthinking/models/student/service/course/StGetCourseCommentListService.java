package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseCommentDao;
import cn.logicalthinking.common.entity.CourseComment;
import cn.logicalthinking.common.util.ParamValidation;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程评论表 列表查询
 * @date 2018-12-19
 */
@Service
public class StGetCourseCommentListService extends AbstractService {

    @Resource
    private CourseCommentDao courseCommentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "type",//评论类型 （0好评1中评2差评）
        };

        String courseId = data.getParameter("courseId");
        ParamValidation.isNotNull(courseId, "缺失课程id");
        map.put("courseId", courseId);

        data.setConditionMap(map, conditionArr);

        PageInfo<CourseComment> pageInfo = courseCommentDao.selectCourseCommentListByPage(map);

        // 查询各评论类型数量
        Map<String, Object> mapCount = new HashMap<>();
        mapCount.put("courseId", courseId);
        // 所有
        int all = courseCommentDao.selectCourseCommentCount(mapCount);

        // 好评
        mapCount.put("type", 0);
        int good = courseCommentDao.selectCourseCommentCount(mapCount);
        // 中评
        mapCount.put("type", 1);
        int normal = courseCommentDao.selectCourseCommentCount(mapCount);
        // 差评
        mapCount.put("type", 2);
        int bad = courseCommentDao.selectCourseCommentCount(mapCount);

        Result result = Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
        result.put("all", all);
        result.put("good", good);
        result.put("normal", normal);
        result.put("bad", bad);

        return result;
    }

}
