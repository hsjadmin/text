package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.AddressDao;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.ParamValidation;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 立即报名付款界面查询接口
 * @date 2018-12-19
 */
@Service
public class StViewCourseInfoService extends AbstractService {

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private CourseDao courseDao;


    @Override
    protected void before(IClientData data) {
        String courseTypeId = data.getParameter("courseTypeId");
        ParamValidation.isNotNull(courseTypeId, "课程类型id不能为空");

    }

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String courseTypeId = data.getParameter("courseTypeId");
        Student studentUser = data.getStudentUser();

        CourseType courseType = courseTypeDao.selectCourseTypeById(Integer.parseInt(courseTypeId));
        if (courseType == null) {
            throw new BusinessException("课程类型数据异常");
        }
        Course course = courseDao.selectCourseById(courseType.getCourseId());
        if (course == null) {
            throw new BusinessException("课程数据异常");
        }
        Integer quantity = courseType.getQuantity();
        Integer enrolment = courseType.getEnrolment();
        if (quantity == null || enrolment == null) {
            throw new BusinessException("课程未设置可报名人数");
        }
        if (quantity.compareTo(enrolment) != 1) {
            throw new BusinessException(
                    "课程已报满");
        }
        //开班时间
        String startTime = courseType.getStartTime();
        int num = DateUtil.compare_dateTree(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME),startTime);
        if(num==1)
            throw new BusinessException(
                    "该课程已经开班，无法进行报名");

        JSONObject jsonObject = new JSONObject(true);
        jsonObject.put("courseType", courseType);
        jsonObject.put("course", course);
        jsonObject.put("studentUser", studentUser);

        Map<String, Object> map = new HashMap<>(2);
        map.put("studentId", studentUser.getId());
        map.put("defaulting", 1);
        List<Address> addresses = addressDao.selectAddressListAll(map);
        if (addresses != null && !addresses.isEmpty()) {
            jsonObject.put("address", addresses.get(0));
        } else {

            jsonObject.put("address", "");
        }


        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }

}
