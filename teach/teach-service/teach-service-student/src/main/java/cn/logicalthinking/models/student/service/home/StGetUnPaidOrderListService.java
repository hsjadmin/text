package cn.logicalthinking.models.student.service.home;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.enums.OrderQuestionStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生端 查询未处理订单
 * @date 2018-12-19
 */
@Service
public class StGetUnPaidOrderListService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        Map<String, Object> map = new HashMap<>();

        map.put("studentId", studentUser.getId());
        map.put("status", OrderQuestionStatus.TO_BE_PAY.getKey());
        map.put("isPay", 0);

        List<OrderQuestion> orderQuestionList = orderQuestionDao.selectOrderQuestionListAll(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), orderQuestionList);
    }

}
