package cn.logicalthinking.common.pay;

import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.quartz.JobQueryOrder;
import cn.logicalthinking.common.quartz.QuartzManager;
import cn.logicalthinking.common.util.Tools;
import com.alibaba.fastjson.JSON;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayUtil;
import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.SchedulerException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class MyWXPayUtil {

    private static Logger logger = Logger.getLogger(MyWXPayUtil.class);

    /**
     * 发起支付，等待回调
     *
     * @param map
     * @return
     * @throws Exception
     */
    public static Map startPay(Map<String, String> map, WXPayConfig wxPayConfig) throws Exception {

        System.out.println(WxProp4Student.MP_ID);

        WXPay wxPay = new WXPay(wxPayConfig);
//        wxPay
        Map<String, String> unifiedOrder = wxPay.unifiedOrder(map);

        logger.info(">>>>>>>>>>>>>>>>>>>>");
        logger.info(map);
        logger.info(unifiedOrder);
        logger.info("<<<<<<<<<<<<<<<<<<<<");

        if (Objects.equals(unifiedOrder.get("return_code"), "FAIL")) {
            throw new BusinessException(unifiedOrder.get("return_msg"));
        }

        String tradeType = map.get("trade_type");
        if (Objects.equals(tradeType, "JSAPI") || Objects.equals(tradeType, "APP")) {
            // 2.生成调起参数
            SortedMap<Object, Object> signMap = new TreeMap<>();
            long timeStamp = System.currentTimeMillis() / 1000;
            signMap.put("appId", wxPayConfig.getAppID());
            signMap.put("timeStamp", timeStamp + "");
            signMap.put("nonceStr", unifiedOrder.get("nonce_str"));
            signMap.put("package", "prepay_id=" + unifiedOrder.get("prepay_id"));
            signMap.put("signType", WxProp4Student.SIGN_TYPE);

            String paySign = WXPayUtil.createSign(signMap, wxPayConfig.getKey());
            signMap.put("paySign", paySign);

            return signMap;
        }
        return unifiedOrder;
    }

    /**
     * 发起支付，自主定时查询
     *
     * @param map
     * @param wxPayConfig
     * @param callback    支付成功回调
     * @return
     * @throws Exception
     */
    public static Map startPay(Map<String, String> map, WXPayConfig wxPayConfig, Callback callback) throws Exception {

        logger.info(JSON.toJSON(map));
        logger.info(JSON.toJSON(wxPayConfig));
        Map result = startPay(map, wxPayConfig);
        addSchedule(map, wxPayConfig, callback);

        result.put("timeout", WxProp4Student.PAY_TIMEOUT);
        return result;
    }

    /**
     * 添加任务轮询查询是否支付成功
     *
     * @param map
     * @param wxPayConfig
     * @param callback
     * @throws SchedulerException
     */
    private static void addSchedule(Map<String, String> map, WXPayConfig wxPayConfig, Callback callback) throws SchedulerException {

        String outTradeNo = map.get("out_trade_no");
        String jobName = outTradeNo + "_" + Tools.UUID();
        Calendar instance = Calendar.getInstance();
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH) + 1;
        int day = instance.get(Calendar.DAY_OF_MONTH);
        String corn = "0/10 * * " + day + " " + month + " ? " + year;
        // 支付超时设置
        instance.add(Calendar.MINUTE, WxProp4Student.PAY_TIMEOUT);

        logger.info("=========================");
        logger.info("当前任务： " + jobName);
        logger.info("corn： " + corn);
        logger.info("endTime： " + instance.getTime());
        logger.info("=========================");

        Map<String, Object> result = callback.getResult();
        result.put("orderNo", outTradeNo);
        result.put("timeEnd", map.get("time_end"));
        result.put("jobName", jobName);
        result.put("wxPayConfig", wxPayConfig);

        JobDataMap jobDataMap = new JobDataMap();

        jobDataMap.put("endTime", instance);
        jobDataMap.put("result", result);


        jobDataMap.put("callbackName", callback.getClass().getSimpleName());
        QuartzManager.addJob(jobName, new JobQueryOrder(), jobDataMap, corn);
    }

    /**
     * @param response
     * @param callBack
     * @throws Exception
     * @Description 支付成功回调
     * @author 肖宏鑫
     * @下午6:37:11
     * @version 1.0
     */
    public static void weChatPayCallBack(HttpServletRequest request, HttpServletResponse response, Callback callBack) {

        try {
            Map map = getMap(request);

            logger.info(map);

            String orderNo = map.get("out_trade_no") == null ? "" : map.get("out_trade_no").toString();
            String timeEnd = map.get("time_end") == null ? "" : map.get("time_end").toString();

            // 响应微信服务器
            LinkedHashMap<String, String> resutl = new LinkedHashMap<>();
            resutl.put("return_code", "SUCCESS");
            resutl.put("return_msg", "OK");

            LinkedHashMap<String, Object> result = new LinkedHashMap<>(2);
            result.put("orderNo", orderNo);
            result.put("timeEnd", timeEnd);

            callBack.init(result);

            // 处理过订单
            if (callBack.onProcessed()) {
                response.getWriter().write(WXPayUtil.mapToXml(resutl));
                return;
            }

            // 通信失败
            if ("FAIL".equals(map.get("return_code"))) {
                logger.error(map.get("return_msg"));
                return;
            }
            // 支付失败
            if ("FAIL".equals(map.get("result_code"))) {
                logger.error(map.get("err_code_des"));
                // 支付失败
                callBack.onFail();
                return;
            }
            // 支付成功
            callBack.onSuccess();
            // 响应微信服务器
            response.getWriter().write(WXPayUtil.mapToXml(resutl));
        } catch (Exception e) {
            callBack.onError(e);
        }

    }

    /**
     * 订单查询
     *
     * @param outTradeNo
     * @param wxPayConfig
     * @param callBack
     * @throws Exception
     */
    public static boolean queryOrder(String outTradeNo, WXPayConfig wxPayConfig, Callback callBack) throws Exception {

        WXPay wxPay = new WXPay(wxPayConfig);

        Map<String, String> orderQueryMap = new HashMap<>(6);
        orderQueryMap.put("out_trade_no", outTradeNo);
        Map<String, String> map = wxPay.orderQuery(orderQueryMap);

        logger.info(map);
        // 处理过订单
        if (callBack.onProcessed()) {
            return true;
        }

        // 通信失败
        if ("FAIL".equals(map.get("return_code"))) {
            logger.error(map.get("return_msg"));
            return false;
        }
        //
        // 未支付，跳过
        if ("NOTPAY".equals(map.get("trade_state"))) {
            logger.error(map.get("trade_state_desc"));
            return false;
        }
        // 支付中，跳过
        if ("USERPAYING".equals(map.get("trade_state"))) {
            logger.error(map.get("trade_state_desc"));
            return false;
        }
        // 支付失败
        if (!"SUCCESS".equals(map.get("trade_state"))) {
            logger.error(map.get("trade_state_desc"));
            // 支付失败
            callBack.onFail();
            return false;
        }
        // 支付成功
        callBack.onSuccess();
        return true;
    }

    public static boolean closeOrder(String outTradeNo, WXPayConfig wxPayConfig) throws Exception {

        WXPay wxPay = new WXPay(wxPayConfig);

        Map<String, String> orderCloseMap = new HashMap<>(6);
        orderCloseMap.put("out_trade_no", outTradeNo);

        Map<String, String> map = wxPay.closeOrder(orderCloseMap);

        // 通信失败
        if ("FAIL".equals(map.get("return_code"))) {
            logger.error(map.get("return_msg"));
            return false;
        }
        // 关闭失败
        if ("FAIL".equals(map.get("result_code"))) {
            logger.error(map.get("return_msg"));
            return false;
        }
        // 关闭成功
        return true;
    }

    /**
     * @param request
     * @return
     * @throws Exception
     */
    private static Map getMap(HttpServletRequest request) throws Exception {
        ServletInputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null) {
            sb.append(s);
        }
        Map map = WXPayUtil.xmlToMap(sb.toString());
        br.close();
        inputStreamReader.close();
        inputStream.close();

        return map;
    }

}
