package cn.logicalthinking.models.student.service.mine;

import cn.logicalthinking.common.base.constant.OrderNoConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.OrderRechargeDao;
import cn.logicalthinking.common.entity.OrderRecharge;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.pay.MyWXPayConfig;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.QRCodeUtil;
import cn.logicalthinking.common.util.SpringContextHolder;
import cn.logicalthinking.common.util.UploadFileUtil;
import cn.logicalthinking.models.student.service.buiz.OrderRechargePayCallback;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生充值
 * @date 2018-12-19
 */
@Service
public class StRechargeService extends AbstractService {

    @Resource
    private OrderRechargeDao orderRechargeDao;

    @Value("${WX.NOTIFY_URL_RECHARGE}")
    private String NOTIFY_URL_RECHARGE;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        String amount = data.getParameter("amount");
        String payment = data.getParameter("payment");
        ParamValidation.isNotNull(amount, "充值金额不为空");
        ParamValidation.isNotNull(payment, "未选择支付方式");

        BigDecimal money = new BigDecimal(amount);
        if (money.compareTo(new BigDecimal("0.01")) == -1) {
            throw new BusinessException("金额不能小于1分");
        }

        // 创建订单
        OrderRecharge orderRecharge = new OrderRecharge();
        orderRecharge.setAmount(money.doubleValue());
        orderRecharge.setPayment(payment);
        orderRecharge.setStudentId(studentUser.getId());
        orderRecharge.setStatus("1");

        if (orderRechargeDao.addOrderRecharge(orderRecharge) == 0) {
            throw new BusinessException("订单创建失败");
        }

        // 下单参数
        String string = money.multiply(new BigDecimal("100")).intValue() + "";
        Map<String, String> map = new LinkedHashMap<>(4);
        map.put("out_trade_no", OrderNoConstant.RECHARGE + String.valueOf(orderRecharge.getId()));
        map.put("total_fee", string);
        map.put("notify_url", NOTIFY_URL_RECHARGE);


        map.put("body", "授课小程序支付中心-余额充值");
        map.put("detail", "余额充值-" + amount + "元");
        map.put("attach", "额外数据");
        // 自己支付（小程序支付）

        OrderRechargePayCallback orderRechargePayCallback = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderRechargePayCallback.class);

        if (Objects.equals(payment, "0")) {
            // 1.小程序下单
            map.put("trade_type", "JSAPI");
            map.put("openid", studentUser.getOpenId());
            Map<String, String> signMap = MyWXPayUtil.startPay(map, new MyWXPayConfig(), orderRechargePayCallback);
            return Result.jsonData("200", signMap);

        } else { // 好友代付，扫码支付

            // 二维码方式
            map.put("trade_type", "NATIVE ");
            Map<String, String> unifiedOrder = MyWXPayUtil.startPay(map, new MyWXPayConfig(), orderRechargePayCallback);
            String code_url = unifiedOrder.get("code_url");
            // 创建二维码
            String path = UploadFileUtil.destFile + UploadFileUtil.uploadPath;
            String name = QRCodeUtil.encode(code_url, path);

            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(orderRecharge);
            jsonObject.put("img", UploadFileUtil.uploadPath + name);
            jsonObject.put("timeout", unifiedOrder.get("timeout"));

            return Result.jsonData("200", jsonObject);
        }

    }

}
