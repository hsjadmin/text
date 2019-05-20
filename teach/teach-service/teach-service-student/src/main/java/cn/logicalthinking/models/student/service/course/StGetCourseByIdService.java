package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.CourseOutlineStatus;
import cn.logicalthinking.common.base.enums.OrderCourseStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.ValidataException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 根据id查询课程详情，带课程大纲，是否报名，评论数
 * @date 2018-12-19
 */
@Service
public class StGetCourseByIdService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseCommentDao courseCommentDao;

    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private TeacherDao teacherDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        Course course = courseDao.selectCourseByIdWithCourseTypeAndCourseOutline(Integer.parseInt(id));
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(course);

        // 查询课程 评论数
        Map<String, Object> map = new LinkedHashMap<>(1);
        map.put("courseId", course.getId());
        int appriseNum = courseCommentDao.selectCourseCommentCount(map);

        jsonObject.put("appriseNum", appriseNum);


        Student studentUser = data.getStudentUsers();
        if(studentUser!=null){
            // 查询是否报名
            Map<String, Object> map2 = new LinkedHashMap<>(1);
            map2.put("studentId", studentUser.getId());
            map2.put("courseId", course.getId());
            map2.put("status", OrderCourseStatus.PAID.getKey());
            int i = orderCourseDao.selectOrderCourseCount(map2);
            jsonObject.put("hasApply", i != 0);
        }else{
            jsonObject.put("hasApply",false);
        }


        // 房间号
        Teacher teacher = teacherDao.selectTeacherById(course.getTeacherId());
        jsonObject.put("liveRoom", teacher.getLiveRoom());

        // 老师电话
//        jsonObject.put("teacher", teacher);

        //

        // 是否正在直播，及直播中的大纲id
        jsonObject.put("living", false);

        if (course.getCourseTypeList() == null || course.getCourseTypeList().isEmpty()) {
            throw new BusinessException("该课程数据异常");
        }
        List<CourseOutline> courseOutlines = course.getCourseTypeList().get(0).getCourseOutlineList();
        if (courseOutlines == null || courseOutlines.isEmpty()) {
            throw new BusinessException("该课程没有大纲");
        }
        for (CourseOutline courseOutline : courseOutlines) {
            if (courseOutline.getStatus() == CourseOutlineStatus.IN_CLASS.getKey()) {
                jsonObject.put("living", true);
                jsonObject.put("livingCourseOutlineId", courseOutline.getId());
            }
        }


        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
