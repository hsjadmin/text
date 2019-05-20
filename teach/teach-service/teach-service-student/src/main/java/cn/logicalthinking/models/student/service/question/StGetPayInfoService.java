package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @note 查询订单详情
 * @auhtor 卢祖飞
 * @date 2019/1/2,16:20
 */
@Service
public class StGetPayInfoService extends AbstractService {

    @Resource
    private CouponDao couponDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private AddressDao addressDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        //优惠券id
        String cId = data.getParameter("cid");

        //订单id
        String oid = data.getParameter("oid");

        //查询订单详情
        OrderQuestion orderQuestion = orderQuestionDao.selectOrderQuestionById(oid);
        //老师信息
        Teacher teacher = teacherDao.selectTeacherById(orderQuestion.getTeacherId());
        Student student = studentDao.selectStudentById(orderQuestion.getStudentId());
        //系统参数
        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName("startingTime");
        //优惠券信息
        Coupon coupon = couponDao.selectCouponById(cId);


        //答疑时间
        Double answeringTime = Double.parseDouble(orderQuestion.getAnsweringTime());

        //起步价
        BigDecimal startingPrice = teacher.getStartingPrice();

        //起步时间
        Double startingTime = Double.parseDouble(applicationParameter.getValue());
        BigDecimal price = null;
        String truePrice = "";

        if (StringUtils.isBlank(cId)) {
            //未使用优惠券
            if (answeringTime < startingTime) {
                price = startingPrice;
                truePrice = startingPrice+"";
            } else {
                Double time = answeringTime - startingTime;
                time = Math.ceil(time);
                price = startingPrice.add(teacher.getChargeSettings().multiply(new BigDecimal(time)));
                truePrice = price + "(" + startingPrice + "+" + teacher.getChargeSettings() + "*" + time + ")";
            }
        } else {
            //使用优惠券
            if (answeringTime < startingTime) {
                price = startingPrice;
                truePrice = startingPrice+"";
                if (price.compareTo(coupon.getSatisfy()) == 1) {
                    price = price.subtract(coupon.getDiscount());
                    if(price.compareTo(BigDecimal.ZERO) == -1){
                        price = new BigDecimal(0.01);
                        truePrice = price+"";
                    }else {
                        truePrice = price + "(" + price + "-" + coupon.getDiscount() + ")";
                    }
                }
            } else {
                Double time = answeringTime - startingTime;
                time = Math.ceil(time);
                price = startingPrice.add(teacher.getChargeSettings().multiply(new BigDecimal(time)));
                truePrice = price + "(" + startingPrice + "+" + teacher.getChargeSettings() + "*" + time + ")";
                if (price.compareTo(coupon.getSatisfy()) == 1) {
                    price = price.subtract(coupon.getDiscount());
                    if(price.compareTo(BigDecimal.ZERO) == -1){
                        price = new BigDecimal(0.01);
                        truePrice = price+"";
                    }else {
                        truePrice = price + "(" + startingPrice + "+" + teacher.getChargeSettings() + "*" + time + ")-" + coupon.getDiscount();
                    }
                }
            }
        }
        String accountForm = startingPrice + "元" + startingTime + "分钟,超时以" + teacher.getChargeSettings() + "/分钟";
        //学生收货地址
        Map<String, Object> map = new HashMap<>();
        map.put("studentId", student.getId());
        List<Address> addresses = addressDao.selectAddressListAll(map);
        JSONObject jsonObject = new JSONObject();
        Address address1 = null;
        if (addresses != null && addresses.size() != 0) {
            for (Address address : addresses) {
                if ("1".equals(address.getDefaulting())) {
                    address1 = address;
                }
            }
            if (address1 == null) {
                address1 = addresses.get(0);
            }
            jsonObject.put("address", address1);
        }

        jsonObject.put("answerTime", answeringTime);
        jsonObject.put("truePrice", truePrice);
        jsonObject.put("accountForm", accountForm);
        jsonObject.put("isPwd",student.getHasPin());
        jsonObject.put("balance",student.getBalance());
        jsonObject.put("price", price);
        if (coupon != null) {
            jsonObject.put("coupon", coupon.getDiscount());
        }
        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), jsonObject);
    }
}
