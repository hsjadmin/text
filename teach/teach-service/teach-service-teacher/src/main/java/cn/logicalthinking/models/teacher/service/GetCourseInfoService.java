package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseCommentDao;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.util.ParamValidation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @note 查询课程详情
 * @auhtor 卢祖飞
 * @date 2018/12/26,11:59
 */
@Service
public class GetCourseInfoService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private CourseCommentDao courseCommentDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //获取课程id
        String cId = data.getParameter("cId");

        Map<String, Object> map = new HashMap<>();

        map.put("courseId",cId);

        int num = courseCommentDao.selectCourseCommentCount(map);

        Course course = courseDao.selectCourseInfo(Integer.parseInt(cId));

        JSONObject jsonObject = (JSONObject) JSON.toJSON(course);
        jsonObject.put("commentCount",num);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),jsonObject);
    }

    private List<CourseType> getPrice(Course course){
        List<CourseType> courseTypeList = course.getCourseTypeList();
        if(courseTypeList.size() <= 0){
            return null;
        }
        CourseType courseType = courseTypeList.get(0);
        courseType.setDiscount(courseType.getDiscount().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        courseType.setDiscount(courseType.getOrig().setScale(2, BigDecimal.ROUND_HALF_DOWN));
        return courseTypeList;
    }
}
