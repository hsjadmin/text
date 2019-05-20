package cn.logicalthinking.models.manage.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.exception.ValidataException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author 黄世杰
 * @version 1.0
 * @Description 课程类型表，即大班课小班课一对一等课程 修改
 * @date 2018-12-19
 */
@Service
public class MaUpdateCourseTypeService extends AbstractService {

    @Resource
    private CourseTypeDao courseTypeDao;

    private IClientData data;

    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        CourseType courseType = (CourseType) data.getObject();

        if (courseType.getId() == null)
            throw new ValidataException("id不能为空");

        CourseType selectCourseTypeById = courseTypeDao.selectCourseTypeById(courseType.getId());

        if (!Objects.equals(selectCourseTypeById.getDiscount(), courseType.getDiscount())) {
            // 若修改优惠价，则重新计算提成
            BigDecimal discount = courseType.getDiscount();
            BigDecimal enrolment = new BigDecimal(courseType.getEnrolment());
            discount.setScale(2, BigDecimal.ROUND_HALF_UP);
            enrolment.setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal multiply = discount.multiply(enrolment);

            courseType.setCommission(multiply);

        }

        courseTypeDao.updateCourseType(courseType);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(), CODE.CODE_200_UPDATE.getValue());

    }

}
