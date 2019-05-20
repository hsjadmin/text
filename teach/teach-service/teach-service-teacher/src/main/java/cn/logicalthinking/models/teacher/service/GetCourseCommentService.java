package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseCommentDao;
import cn.logicalthinking.common.entity.CourseComment;
import cn.logicalthinking.common.util.ParamValidation;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @note 查询评论
 * @auhtor 卢祖飞
 * @date 2018/12/26,14:30
 */
@Service
public class GetCourseCommentService extends AbstractService {

    @Resource
    private CourseCommentDao courseCommentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        /*课程id*/
        String cId = data.getParameter("cId");
        ParamValidation.isNotNull(cId,"cId不能为空");

        String typeId = data.getParameter("type");

        Map<String, Object> map = data.initMap();

        map.put("courseId",cId);

        if(StringUtils.isNotBlank(typeId)){
            map.put("type",typeId);
        }

        PageInfo<CourseComment> pageInfo = courseCommentDao.selectCourseCommentByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
    }
}
