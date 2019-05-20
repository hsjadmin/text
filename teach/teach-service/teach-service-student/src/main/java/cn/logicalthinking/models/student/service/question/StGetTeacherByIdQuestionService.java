package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.QuestionCommentDao;
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
public class StGetTeacherByIdQuestionService extends AbstractService {

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private StudentHasTeacherDao studentHasTeacherDao;

    @Resource
    QuestionCommentDao questionCommentDao;

    @Resource
    ApplicationParameterDao applicationParameterDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        if (StringUtils.isBlank(id))
            throw new ValidataException("id不能为空");

        // 计数综合评分
        Teacher teacher = teacherDao.selectTeacherById(Integer.parseInt(id));
        if (teacher == null) {
            throw new BusinessException("没有该老师");
        }
        BigDecimal starCount = teacher.getStarCount();
        Integer starTimes = teacher.getStarTimes();
        BigDecimal bigDecimal = new BigDecimal(starTimes);
        teacher.setStar(starCount.divide(bigDecimal,1 , BigDecimal.ROUND_HALF_UP) + "");

        // 查询关注数
        Map<String, Object> map = new LinkedHashMap<>(1);
        map.put("teacherId", id);
        int followingNum = studentHasTeacherDao.selectStudentHasTeacherCount(map);
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(teacher);
        jsonObject.put("followingNum", followingNum);

        // 查询评论条数
        int commentCount = questionCommentDao.selectQuestionCommentCount(map);
        jsonObject.put("commentCount", commentCount);

        // 查询是否订阅
        Student studentUser = data.getStudentUser();
        map.put("studentId", studentUser.getId());
        int isFollowing = studentHasTeacherDao.selectStudentHasTeacherCount(map);

        jsonObject.put("isFollowing", isFollowing != 0);


        // 查询起步时间
        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName(AppParamConstant.QUESTION_START_TIME);
        String value = applicationParameter.getValue();
        jsonObject.put("questionStartTime", value);

        // 查询答疑介绍
        ApplicationParameter applicationParameter1 = applicationParameterDao.selectApplicationParameterByName(AppParamConstant.INTRODUCTION);
        String questionIntro = applicationParameter1.getValue();
        jsonObject.put("questionIntro", questionIntro);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
