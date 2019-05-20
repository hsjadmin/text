package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderCourseDao;
import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程订单查询接口
 * @date 2018-12-19
 */
@Service
public class StPayCourseOrderQueryService extends AbstractService {

    @Resource
    private OrderCourseDao orderCourseDao;

    @SuppressWarnings("all")
    @Override
    protected Result doWork(IClientData data) throws Exception {

        // 获取参数
        Student studentUser = data.getStudentUser();

        String id = data.getParameter("id");
        //
        OrderCourse orderCourse = orderCourseDao.selectOrderCourseById(Integer.parseInt(id));

        if (orderCourse == null) {
            throw new BusinessException("订单未创建");
        }

        return Result.jsonData("200", orderCourse);

    }
}
