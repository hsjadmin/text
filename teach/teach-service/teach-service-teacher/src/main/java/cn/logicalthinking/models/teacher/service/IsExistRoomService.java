package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @note 创建房间
 * @auhtor 卢祖飞
 * @date 2018/12/29,14:53
 */
@Service
public class IsExistRoomService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseOutlineDao courseOutlineDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Teacher teacherUser = data.getTeacherUser();

        String id = data.getParameter("id");

        String courseTypeId = data.getParameter("courseTypeId");

        long millis = System.currentTimeMillis();

        String roomId = teacherUser.getPhone() + millis;

        Teacher teacher = new Teacher();

        teacher.setId(teacherUser.getId());
        teacher.setLiveRoom(roomId);
        teacher.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        teacherDao.updateTeacher(teacher);

        Map<String, Object> map = new HashMap<>();
        map.put("courseTypeId",courseTypeId);
        map.put("status",2);
        List<CourseOutline> courseOutlineList = courseOutlineDao.selectCourseOutlineListAll(map);
        for (CourseOutline courseOutline : courseOutlineList) {
            if(courseOutline.getId()!= Integer.parseInt(id)){
                courseOutline.setStatus(0);
                courseOutlineDao.updateCourseOutline(courseOutline);
            }
        }

        return Result.jsonData(CODE.CODE_200_UPDATE.getKey(),roomId);
    }

    public static void main(String[] args) {
        String s= "[{\"vid\":\"2241113694\",\"filename\":\"0-50690038923837-mix.mp4\",\"pieceindex\":\"0\",\"size\":\"1176966\",\"type\":\"mp4\",\"user\":\"0\",\"mix\":true,\"url\":\"http://jdvodgoxegdqv.vod.126.net/jdvodgoxegdqv/0-50690038923837-0-mix.mp4\",\"channelid\":\"50690038923837\",\"timestamp\":\"1546937286\",\"md5\":\"ab3a18903a89c85b87147264547e0776\"}]";
        JSONArray object = JSONObject.parseArray(s);
        JSONObject jsonObject1 = JSONObject.parseObject(object.get(0).toString());
        System.out.println(jsonObject1.get("channelid").toString());

    }
}
