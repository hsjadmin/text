package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.ParamValidation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @note 教师-我的-主页显示
 * @auhtor 卢祖飞
 * @date 2018/12/25,15:35
 */
@Service
public class GetTeacherInfoService extends AbstractService {

    @Resource
    private MessageTeacherDao messageTeacherDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacherUser = data.getTeacherUser();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>(2);
        map.put("teacherId", teacherUser.getId());
        //获取教师所有课程
        List<Course> courses = courseDao.selectCourseListAll(map);
        JSONObject jsonObject = (JSONObject) JSON.toJSON(teacherUser);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("teacherId", teacherUser.getId());
        map4.put("status",getIds("2,3"));
        int questionCount = orderQuestionDao.getOrderQuestionCount(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("teacherId", teacherUser.getId());
        int messageCount = messageTeacherDao.selectMessageTeacherCount(map5);
        if (courses != null && courses.size() > 0) {
            String courseId = "";
            for (Course cours : courses) {
                courseId += +cours.getId() + ",";
            }
            if (courseId.lastIndexOf(",") != -1)
                courseId = courseId.substring(0, courseId.lastIndexOf(","));
            Map<String, Object> map1 = new HashMap<>();
            map1.put("courseId", courseId);
            //获取教师所有课程类别
            List<CourseType> type = courseTypeDao.getType(map1);
            List<CourseOutline> outLine=new ArrayList<CourseOutline>();
            List<CourseOutline> outLine2=new ArrayList<CourseOutline>();
            if (type != null && type.size() > 0){
                String typeId = "";
                for (CourseType courseType : type) {
                    typeId += +courseType.getId() + ",";
                }
                if (typeId.lastIndexOf(",") != -1)
                    typeId = typeId.substring(0, typeId.lastIndexOf(","));
                Map<String, Object> map2 = new HashMap<>();
                map2.put("courseTypeId", typeId);
                map2.put("status", 0);
                outLine= courseOutlineDao.getOutLine(map2);

                Map<String, Object> map3 = new HashMap<>();
                map3.put("courseTypeId", typeId);
                map3.put("status", 1);
                outLine2 = courseOutlineDao.getOutLine(map3);

            }
            //未上课
            jsonObject.put("noFinishCourseCount", outLine.size());
            //以上课
            jsonObject.put("finishCourseCount", outLine2.size());
            //解疑数量
            jsonObject.put("questionCount", questionCount);
            //消息条数
            jsonObject.put("messageCount", messageCount);
            return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
        }
        //以上课
        jsonObject.put("noFinishCourseCount", 0);
        //以上课
        jsonObject.put("finishCourseCount", 0);
        //解疑数量
        jsonObject.put("questionCount", questionCount);
        //消息条数
        jsonObject.put("messageCount", messageCount);
        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

    private static String getIds(String idsStr){
        String ids="";
        if(StringUtils.isBlank(idsStr))
            return ids;

        String[] arr=idsStr.split(",");
        for (int i = 0; i < arr.length; i++) {
            ids+="'"+arr[i]+"',";
        }
        if(StringUtils.isBlank(ids))
            return idsStr;
        if(ids.lastIndexOf(",")!=-1)
            ids=ids.substring(0,ids.lastIndexOf(","));
        return ids;
    }
}
