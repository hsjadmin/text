package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.CourseOutlineStatus;
import cn.logicalthinking.common.base.enums.OrderCourseStatus;
import cn.logicalthinking.common.base.enums.OrderQuestionStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.Student;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author xhx
 * @version 1.0
 * @Description 根据openId查询用户信息
 * @date 2018-12-19
 */
@Service
public class StGetMineInfoService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>(2);
        map.put("studentId", studentUser.getId());
        // 课程数
        map.put("status", OrderCourseStatus.PAID.getKey());
//        int courseCount = orderCourseDao.selectOrderCourseCount(map);
        int courseCount = 0;
        List<OrderCourse> orderCourses = orderCourseDao.selectOrderCourseListAll(map);
        for (OrderCourse orderCours : orderCourses) {
            CourseType courseType = courseTypeDao.selectCourseTypeById(orderCours.getCourseTypeId());
            if (courseType == null) {
                continue;
            }
            HashMap<String, Object> map1 = new HashMap<>(2);
            map1.put("status", CourseOutlineStatus.HAVE_CLASS.getKey());
            map1.put("courseTypeId", courseType.getId());
            int i = courseOutlineDao.selectCourseOutlineCount(map1);
            courseCount += i;
        }

        // 疑问数
        map.put("status", OrderQuestionStatus.FINISHED.getKey());
        int questionCount = orderQuestionDao.selectOrderQuestionCount(map);

        JSONObject jsonObject = (JSONObject) JSON.toJSON(studentUser);
        jsonObject.put("totalCourse", courseCount);
        jsonObject.put("totalQuestion", questionCount);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
