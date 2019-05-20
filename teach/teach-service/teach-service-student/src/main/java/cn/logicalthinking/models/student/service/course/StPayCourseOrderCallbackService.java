package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import cn.logicalthinking.models.student.service.buiz.OrderCoursePayCallback;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程订单支付回调接口
 * @date 2018-12-19
 */
@Service
public class StPayCourseOrderCallbackService extends AbstractService {

    @SuppressWarnings("all")
    @Override
    protected Result doWork(IClientData data) throws Exception {

        HttpServletRequest request = data.getRequest();
        HttpServletResponse response = data.getResponse();
        OrderCoursePayCallback orderCoursePayCallback = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderCoursePayCallback.class);
        MyWXPayUtil.weChatPayCallBack(request, response, orderCoursePayCallback);

        return null;

    }

}
