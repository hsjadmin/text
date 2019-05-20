package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import cn.logicalthinking.models.student.service.buiz.OrderRechargePayCallback;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生充值回调
 * @date 2018-12-19
 */
@Service
public class StRechargeCallbackService extends AbstractService {

    @Override
    protected Result doWork(IClientData data) {

        HttpServletRequest request = data.getRequest();
        HttpServletResponse response = data.getResponse();
        OrderRechargePayCallback orderRechargePayCallback = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderRechargePayCallback.class);
        MyWXPayUtil.weChatPayCallBack(request, response, orderRechargePayCallback);

        return null;
    }
}

