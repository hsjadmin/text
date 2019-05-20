package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.OrderQuestionStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 取消答疑订单
 * @date 2019-1-2
 */
@Service
public class StCancelQuestionOrderService extends AbstractService {

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        String id = data.getParameter("id");

        OrderQuestion orderQuestion = new OrderQuestion();
        orderQuestion.setId(Integer.parseInt(id));

        orderQuestion.setUpdateTime(DateUtil.getSimpleCurrentDate());
        orderQuestion.setStatus(OrderQuestionStatus.CANCELLED.getKey());

        if (orderQuestionDao.updateOrderQuestion(orderQuestion) == 0) {
            throw new BusinessException("操作失败");
        }

        return Result.jsonMsg("200", "取消成功");
    }
}
