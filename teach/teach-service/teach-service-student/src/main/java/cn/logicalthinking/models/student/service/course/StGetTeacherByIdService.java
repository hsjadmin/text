package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.CourseCommentDao;
import cn.logicalthinking.common.dao.StudentHasTeacherDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.exception.ValidataException;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 老师表 根据id查询,返回订阅数，是否订阅, 评论条数
 * @date 2018-12-19
 */
@Service
public class StGetTeacherByIdService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private StudentHasTeacherDao studentHasTeacherDao;

    @Resource
    CourseCommentDao courseCommentDao;

    @Resource
    ApplicationParameterDao applicationParameterDao;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        // 计数综合评分
        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(id));
        if (teacher == null) {
            throw new BusinessException("老师已被删除");
        }
        BigDecimal starCount = teacher.getStarCount();
        Integer starTimes = teacher.getStarTimes();
        BigDecimal bigDecimal = new BigDecimal(starTimes);
        teacher.setStar(starCount.divide(bigDecimal, 1, BigDecimal.ROUND_HALF_UP) + "");

        // 查询关注数
        Map<String, Object> map = new LinkedHashMap<>(1);
        map.put("teacherId", id);
        int followingNum = studentHasTeacherDao.selectStudentHasTeacherCount(map);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(teacher);
        jsonObject.put("followingNum", followingNum);

        // 查询是否订阅
        Student studentUser = data.getStudentUser();
        map.put("studentId", studentUser.getId());
        int isFollowing = studentHasTeacherDao.selectStudentHasTeacherCount(map);

        jsonObject.put("isFollowing", isFollowing != 0);

        // 评论数
        int commentCount = courseCommentDao.selectCourseCommentCount(map);
        jsonObject.put("commentCount", commentCount);

        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName(AppParamConstant.INTRODUCTION);
        if (applicationParameter != null) {

            jsonObject.put("introduction", applicationParameter.getValue());
        } else {
            jsonObject.put("introduction", "");
        }

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
