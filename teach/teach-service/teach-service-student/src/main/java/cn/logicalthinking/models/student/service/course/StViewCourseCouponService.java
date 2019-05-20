package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 立即报名付款界面查询 可用优惠券接口
 * @date 2018-12-19
 */
@Service
public class StViewCourseCouponService extends AbstractService {

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CouponDao couponDao;

    @Override
    protected void before(IClientData data) {
        String courseTypeId = data.getParameter("courseTypeId");
        ParamValidation.isNotNull(courseTypeId, "课程类型id不能为空");

    }

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();
        String courseTypeId = data.getParameter("courseTypeId");

        // 查询课程信息
        CourseType courseType = courseTypeDao.selectCourseTypeById(Integer.parseInt(courseTypeId));
        if (courseType == null) {
            throw new BusinessException("课程数据异常");
        }

        // 查询该订单可用优惠券信息
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", studentUser.getId());
        map.put("courseTypeId", courseTypeId);
        List<Coupon> list = couponDao.selectAvailableCouponByStudentIdAndCourseTypeId(map);


        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), list);
    }

}
