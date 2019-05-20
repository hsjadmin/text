package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.CourseOutlineStatus;
import cn.logicalthinking.common.base.enums.OrderCourseStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.Student;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 查询我的课程
 * @date 2018-12-19
 */
@Service
public class StGetMyCourseListService extends AbstractService {

    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        // 是否完成
        String isFinish = data.getParameter("isFinish");

        // 默认查未完成
        if (StringUtils.isBlank(isFinish)) {
            isFinish = "0";
        }

        Student studentUser = data.getStudentUser();
        Map<String, Object> map = data.initMap();
        map.put("studentId", studentUser.getId());
        map.put("isFinish", isFinish);
        map.put("status", OrderCourseStatus.PAID.getKey());

        PageInfo<OrderCourse> pageInfo = orderCourseDao.selectOrderCourseListByPageWithCourseTypeAndCourse(map);

        // 分页的数据
        JSONArray jsonArray = new JSONArray();
        List<OrderCourse> list = pageInfo.getList();
        for (OrderCourse orderCourse : list) {
            Map<String, Object> param = new HashMap<>();
            param.put("courseTypeId", orderCourse.getCourseTypeId());
            List<CourseOutline> courseOutlines = courseOutlineDao.selectCourseOutlineListAll(param);

            // 课程
            JSONObject orderCourseJsonObject = (JSONObject) JSONObject.toJSON(orderCourse);

            // 查询正在直播的大纲
            for (CourseOutline courseOutline : courseOutlines) {
                if (courseOutline.getStatus() == CourseOutlineStatus.IN_CLASS.getKey()) {
                    orderCourseJsonObject.put("livingCourseOutlineId", courseOutline.getId());
                }
            }

            jsonArray.add(orderCourseJsonObject);

        }

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonArray, pageInfo.getTotal());
    }

}
