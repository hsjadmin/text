package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.constant.OrderNoConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.OrderCourseStatus;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.pay.MyWXPayConfig;
import cn.logicalthinking.common.pay.MyWXPayUtil;
import cn.logicalthinking.common.util.*;
import cn.logicalthinking.models.student.service.buiz.OrderCoursePayCallback;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author xhx
 * @version 1.0
 * @Description 课程订单支付接口
 * @date 2018-12-19
 */
@Service
public class StPayCourseOrderService extends AbstractService {

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseDao courseDao;

    @Resource
    private CouponDao couponDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private OrderCourseDao orderCourseDao;

    @Resource
    private StudentDao studentDao;

    @Value("${WX.NOTIFY_URL_COURSE:www.baidu.com}")
    private String NOTIFY_URL_COURSE;

    private OrderCoursePayCallback orderCoursePayCallback;

    @SuppressWarnings("all")
    @Override
    protected Result doWork(IClientData data) throws Exception {

        orderCoursePayCallback = SpringContextHolder.WEB_APP_CONTEXT.getBean(OrderCoursePayCallback.class);

        // 获取参数
        Student studentUser = data.getStudentUser();
        String courseTypeId = data.getParameter("courseTypeId");
        String isCoupon = data.getParameter("isCoupon");
        String payment = data.getParameter("payment");
        String address = data.getParameter("address");
        String name = data.getParameter("name");
        String phone = data.getParameter("phone");
        String couponId = data.getParameter("couponId");
        OrderCourse orderCourse = (OrderCourse) data.getObject();

        // 1. 余额支付校验支付密码
        balancePayCheckPin(data, studentUser, payment);

        // 2.是否已购买
        checkHasPay(studentUser, courseTypeId);

        //3.获取课程类型
        CourseType courseType = courseTypeDao.selectCourseTypeById(Integer.parseInt(courseTypeId));
        if (courseType == null) {
            throw new BusinessException("课程类型数据异常");
        }

        // 4. 判断是否可以报名
        isSignUp(courseType);

        //5. 获取到课程
        Course course = getCourse(courseType);

        //6. 是否使用优惠券
        BigDecimal amount = courseType.getDiscount().setScale(2, RoundingMode.CEILING);
        amount = hasUseCoupon(data, studentUser, courseTypeId, isCoupon, orderCourse, courseType, amount);

        //7. 创建订单
        preOrder(isCoupon,couponId,studentUser, address, name, phone, orderCourse, courseType, course, amount);

        // 余额支付
        if ("2".equals(payment)) {

            return balancePay(studentUser,orderCourse,amount);

        } else {
            //调用统一 下单接口
//            String orderId=OrderNoConstant.COURSE + String.valueOf(orderCourse.getId());
//            String money=orderCourse.getAmount().toString();
//            PayModel payModel=new PayModel();
//            payModel.setBody("授课小程序支付中心-课程购买");
//            payModel.setIpAddr(data.getIpAddr());
//            payModel.setOutTrade(orderId);
//            payModel.setProductCode(orderId);
//            payModel.setSubject("授课小程序支付中心-课程购买");
//            payModel.setTotalAmount(money);
//            CustomModel entity=new CustomModel();
//            entity.setUserId(String.valueOf(studentUser.getId()));
//            entity.setType(CustomModel.type_1);
//            entity.setPayType(String.valueOf(PayType.WX_PAY));
//            entity.setMoney(money);
//            String customModelJson = JSONObject.toJSONString(entity);
//            payModel.setAttach(customModelJson);//自定义参数
//            payModel.setNotifyUrl(WxPayConfigs.WXNotifyUrl);

            Map<String, String> map = new HashMap<>(4);
            map.put("out_trade_no", OrderNoConstant.COURSE + String.valueOf(orderCourse.getId()));
            map.put("body", "授课小程序支付中心-课程购买");
            map.put("detail", course.getName() + "-" + course.getGrade() + "-" + course.getSubject());
            BigDecimal fee = orderCourse.getAmount().multiply(new BigDecimal("100"));
            BigDecimal zero = new BigDecimal("0");
            if (zero.equals(fee)) {
                fee = new BigDecimal("1");
                orderCourse.setAmount(new BigDecimal("0.01"));
            }
            map.put("total_fee", fee.intValue() + "");
            map.put("notify_url", NOTIFY_URL_COURSE);


            // 自己微信支付
            if ("0".equals(payment)) {
                // 1.小程序下单
                map.put("trade_type", "JSAPI");
                map.put("openid", data.getStudentUser().getOpenId());
                Map<String, String> signMap = MyWXPayUtil.startPay(map, new MyWXPayConfig(), orderCoursePayCallback);
                return Result.jsonData("200", signMap);
            } else if ("1".equals(payment)) { // 找人代付
                // 二维码方式
                map.put("trade_type", "NATIVE ");
                Map<String, String> unifiedOrder = MyWXPayUtil.startPay(map, new MyWXPayConfig(), orderCoursePayCallback);
                String code_url = unifiedOrder.get("code_url");
                // 创建二维码
                String path = DocumentPathParsing.getFullPath() + UploadFileUtil.uploadPath;
                String url = QRCodeUtil.encode(code_url, path);

                JSONObject jsonObject = (JSONObject) JSONObject.toJSON(orderCourse);
                jsonObject.put("img", UploadFileUtil.uploadPath + url);
                jsonObject.put("timeout", unifiedOrder.get("timeout"));

                return Result.jsonData("200", jsonObject);
            }
        }

        return null;

    }

    /**
     * 获取到课程
     * @param courseType
     * @return
     */
    private Course getCourse(CourseType courseType){
        Course course = courseDao.selectCourseById(courseType.getCourseId());
        if (course == null) {
            throw new BusinessException("课程数据异常");
        }
        BigDecimal amount = courseType.getDiscount();
        if (amount == null) {
            throw new BusinessException("课程数据异常");
        }
        return course;
    }

    /**
     * 判断是否可以报名
     * @param courseType
     */
    private void isSignUp(CourseType courseType){
        Integer quantity = courseType.getQuantity();
        Integer enrolment = courseType.getEnrolment();
        if (quantity == null || enrolment == null) {
            throw new BusinessException("课程未设置可报名人数");
        }
        if (quantity.compareTo(enrolment) != 1) {
            throw new BusinessException("课程已报满");
        }
    }

    private Result balancePay(Student studentUser, OrderCourse orderCourse, BigDecimal amount) {
        BigDecimal balance = studentUser.getBalance();
        if (balance == null) {
            throw new BusinessException("余额不足");
        }
        if (balance.compareTo(amount) == -1) {
            throw new BusinessException("余额不足");
        }
        String updateTime = studentUser.getUpdateTime();
        Map<String, Object> balanceMap = new HashMap<>(2);
        balanceMap.put("id", studentUser.getId());
        balanceMap.put("updateTime", updateTime);
        studentUser.setBalance(balance.subtract(amount));
        balanceMap.put("balance", studentUser.getBalance());
        if (studentDao.updateStudentBalance(balanceMap) == 0) {
            throw new BusinessException("支付失败");
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderNo", OrderNoConstant.COURSE + orderCourse.getId());
        resultMap.put("timeEnd", DateUtil.getSimpleCurrentDate());
        orderCoursePayCallback.init(resultMap);
        orderCoursePayCallback.onSuccess();

        return Result.jsonMsg("200", "支付成功");
    }

    private void preOrder(String isCoupon,String couponId,Student studentUser, String address, String name, String phone, OrderCourse orderCourse, CourseType courseType, Course course, BigDecimal amount) {
        orderCourse.setOrig(courseType.getOrig());
        orderCourse.setCourseId(courseType.getCourseId());
        orderCourse.setDiscount(courseType.getDiscount());
        orderCourse.setPeriods(courseType.getPeriods());
        orderCourse.setDuration(courseType.getDuration());
        orderCourse.setAmount(amount);
        orderCourse.setCourseTime(courseType.getCourseTime());
        orderCourse.setCourseTitle(course.getName());
        orderCourse.setCourseInfo(course.getIntroduce());
        orderCourse.setCourseStartTime(courseType.getStartTime());
        orderCourse.setCourseClassFrequency(courseType.getClassFrequency());
        orderCourse.setStudentId(studentUser.getId());
        orderCourse.setStudentName(name);
        orderCourse.setPhone(phone);
        orderCourse.setGrade(studentUser.getGrade());
        orderCourse.setAddress(address);
        orderCourse.setGender(studentUser.getGender());

        if ("1".equals(isCoupon)) { // 使用优惠券
            orderCourse.setCouponId(Integer.parseInt(couponId));
        }
        // 支付中
        orderCourse.setStatus(OrderCourseStatus.PAYING.getKey());

        // 计算提成
        BigDecimal courseTypeDiscount = courseType.getDiscount();
        Integer teacherId = course.getTeacherId();
        Teacher teacher = teacherDao.selectTeacherById(teacherId);
        if (teacher == null) {
            throw new BusinessException("老师数据异常");
        }
        BigDecimal charges = teacher.getCharges();
        if (charges == null) {
            throw new BusinessException("老师数据异常");
        }
        BigDecimal chargesPoint = charges.divide(new BigDecimal("100.0"));
        BigDecimal multiply = courseTypeDiscount.multiply(chargesPoint);
        multiply.setScale(2, RoundingMode.CEILING);

        orderCourse.setTeacherBonus(courseTypeDiscount);
        orderCourse.setTeacherId(teacher.getId());
        orderCourse.setTeacherName(teacher.getName());

        if (orderCourseDao.addOrderCourse(orderCourse) == 0) {
            throw new BusinessException("订单创建失败");
        }
    }

    private BigDecimal hasUseCoupon(IClientData data, Student studentUser, String courseTypeId, String isCoupon, OrderCourse orderCourse, CourseType courseType, BigDecimal amount) {
        if ("1".equals(isCoupon)) { // 使用优惠券
            String couponId = data.getParameter("couponId");
            // 1. 校验优惠券是否可用
            Map<String, Object> map = new HashMap<>();
            map.put("studentId", studentUser.getId());
            map.put("courseTypeId", courseTypeId);
            map.put("couponId", couponId);
            List<Coupon> list = couponDao.selectAvailableCouponByStudentIdAndCourseTypeId(map);

            if (list == null || list.isEmpty()) {
                throw new BusinessException("优惠券不可用，请重新选择");
            }

//            // 2.修改优惠券状态
//            Coupon temp = new Coupon();
//            temp.setId(Integer.parseInt(couponId));
//            temp.setStatus(1);
//            if (couponDao.updateCoupon(temp) == 0) {
//                throw new BusinessException("操作失败");
//            }

            // 计算优惠后的金额
            Coupon coupon = couponDao.selectCouponById(couponId);
            BigDecimal couponDiscount = coupon.getDiscount();
            BigDecimal courseTypeDiscount = courseType.getDiscount();
            amount = courseTypeDiscount.subtract(couponDiscount);

            // 小余0，取1分
            BigDecimal onePoint = new BigDecimal("0.01");
            if (amount.compareTo(onePoint) == -1) {
                amount = onePoint;
            }

            // 赋值
            orderCourse.setIsCoupon(1);
            orderCourse.setCouponPrice(couponDiscount);
        } else {
            orderCourse.setIsCoupon(0);
        }
        return amount;
    }

    private void checkHasPay(Student studentUser, String courseTypeId) {
        Map<String, Object> hasBuyMap = new HashMap<>(3);
        hasBuyMap.put("studentId", studentUser.getId());
        hasBuyMap.put("courseTypeId", courseTypeId);
        hasBuyMap.put("status", OrderCourseStatus.PAID.getKey());
        if (orderCourseDao.selectOrderCourseCount(hasBuyMap) > 0) {
            throw new BusinessException("课程已购买");
        }
    }

    private void balancePayCheckPin(IClientData data, Student studentUser, String payment) {
        if ("2".equals(payment)) {
            // 是否设置密码
            if ("0".equals(studentUser.getHasPin())) {
                throw new BusinessException("未设置支付密码");
            }

            // 校验支付密码
            String pin = data.getParameter("pin");
            ParamValidation.isNotNull(pin, "支付密码不为空");
            if (!Objects.equals(studentUser.getPin(), MD5.md5(pin))) {
                throw new BusinessException("支付密码错误");
            }
        }
    }

}
