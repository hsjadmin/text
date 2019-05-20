package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程类型表，即大班课小班课一对一等课程 删除
 * @date 2018-12-19
 */
@Service
public class MaRemoveCourseService extends AbstractService {

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private CourseDao courseDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {
        this.data = data;

        Map<String, Object> map = new HashMap<String, Object>();

        String ids = data.getParameter("ids");
        if (StringUtils.isBlank(ids))
            throw new ValidataException("ids不能为空");

        map.put("ids", getIds(ids));

        CourseType courseType = courseTypeDao.selectCourseTypeById(Integer.parseInt(ids));
        if (courseType == null) {
            throw new BusinessException("没有该课程");
        }

        // 1.删除大纲
        courseOutlineDao.deleteCourseOutlineByctId(ids);


        courseTypeDao.removeCourseType(map);

        Map<String, Object> ctMap = new HashMap<>();
        ctMap.put("ids", courseType.getCourseId());
        courseDao.removeCourse(ctMap);

        return Result.jsonMsg(CODE.CODE_200_DELETE.getKey(), CODE.CODE_200_DELETE.getValue());

    }

    private static String getIds(String idsStr) {
        String ids = "";
        if (StringUtils.isBlank(idsStr))
            return ids;

        String[] arr = idsStr.split(",");
        for (int i = 0; i < arr.length; i++) {
            ids += "'" + arr[i] + "',";
        }
        if (StringUtils.isBlank(ids))
            return idsStr;
        if (ids.lastIndexOf(",") != -1)
            ids = ids.substring(0, ids.lastIndexOf(","));
        return ids;
    }

}
