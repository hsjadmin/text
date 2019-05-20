package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.*;
import cn.logicalthinking.common.entity.*;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/3,13:52
 */
@Service
public class StPayCourseQuestionService extends AbstractService {

    @Resource
    private CouponDao couponDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;

    @Resource
    private AddressDao addressDao;

    @Resource
    private StudentDao studentDao;

    @Resource
    private OrderStudentDao orderStudentDao;

    @Resource
    private FinanceTeacherDao financeTeacherDao;

    @Resource
    private FinanceStudentDao financeStudentDao;

    @Resource
    private FinanceDao financeDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {
        //支付密码
        String payPwd = data.getParameter("payPwd");
        //优惠券id
        String cId = data.getParameter("cid");
        //订单id
        String oid = data.getParameter("oid");
        //收货地址id
        String addressId = data.getParameter("addressId");

        ParamValidation.isNotNull(payPwd,"支付密码不能为空");
        ParamValidation.isNotNull(oid,"订单id不能为空");
        ParamValidation.isNotNull(addressId,"收获地址不能为空");

        //查询订单详情
        OrderQuestion orderQuestion = orderQuestionDao.selectOrderQuestionById(oid);
        if(1 == orderQuestion.getIsPay()){
            throw new ValidataException("该订单已支付,请勿重复支付");
        }

        //老师信息
        Teacher teacher = teacherDao.selectTeacherById(orderQuestion.getTeacherId());
        //学生信息
        Student student1 = studentDao.selectStudentById(orderQuestion.getStudentId());
        //收货地址
        Address address = addressDao.selectAddressById(Integer.parseInt(addressId));
        //系统参数
        ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterByName("startingTime");
        //优惠券信息
        Coupon coupon = null;
        if(StringUtils.isNotBlank(cId)){
            coupon= couponDao.selectCouponById(cId);
            ParamValidation.isNotNull(coupon,"优惠券失效");
            if(coupon.getStatus() == 1){
                throw new ValidataException("优惠券已使用过,请刷新");
            }
        }
        //答疑时间
        Double answeringTime = Double.parseDouble(orderQuestion.getAnsweringTime());
        //起步价
        BigDecimal startingPrice = teacher.getStartingPrice();
        //起步时间
        Double startingTime = Double.parseDouble(applicationParameter.getValue());
        if(StringUtils.isBlank(student1.getPin())){
            throw new ValidataException("未设置支付密码,请先设置支付密码");
        }

        if(!MD5.md5(payPwd).equals(student1.getPin())){
            throw new ValidataException("支付密码错误");
        }
        BigDecimal price = null;
        OrderQuestion orderQuestion1 = new OrderQuestion();
        orderQuestion1.setId(Integer.parseInt(oid));
        if(StringUtils.isBlank(cId)){
            //未使用优惠券
            price = noUseCoupon(answeringTime,startingTime,startingPrice,teacher,oid,address);
        }else{
            //使用优惠券
            price = useCoupon(answeringTime,startingTime,startingPrice,coupon,teacher,oid,address);
        }
        Student student = new Student();
        student.setId(orderQuestion.getStudentId());
        student.setBalance(price);
        if(price.compareTo(student1.getBalance()) == 1){
            throw new ValidataException("你的余额不足,请及时充值");
        }
        //学生使用余额付款
        studentDao.updateStudentBalances(student);

        BigDecimal charges = teacher.getCharges();

        Map<String, Object> map = new HashMap<>();
        map.put("id",teacher.getId());
        map.put("money",price.multiply(charges.divide(new BigDecimal(100))));

        //修改老师余额
        teacherDao.addBalance(map);

        //添加老师账户明细
        FinanceTeacher financeTeacher = new FinanceTeacher();
        financeTeacher.setTeacherId(teacher.getId());
        financeTeacher.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        financeTeacher.setType(0);
        financeTeacher.setName("疑难收入");
        financeTeacher.setMoney(price.multiply(charges.divide(new BigDecimal(100))));
        financeTeacherDao.addFinanceTeacher(financeTeacher);
        //添加学生明细
        FinanceStudent financeStudent = new FinanceStudent();
        financeStudent.setStudentId(student1.getId());
        financeStudent.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        financeStudent.setName("疑难支出");
        financeStudent.setType(0);
        financeStudent.setMoney(price);
        financeStudentDao.addFinanceStudent(financeStudent);
        //添加明细
        Finance finance = new Finance();
        finance.setStudentName(student1.getName());
        finance.setTeacherName(teacher.getName());
        finance.setStudentPay(price);
        finance.setTeacherBonusRatio(teacher.getCharges());
        finance.setPlatformIncome(price.subtract(price.multiply(charges.divide(new BigDecimal(100)))));
        finance.setTeacherIncome(price.multiply(charges.divide(new BigDecimal(100))));
        financeDao.addFinance(finance);
        //添加学生订单
        OrderStudent orderStudent = new OrderStudent();
        orderStudent.setGender(student1.getGender());
        orderStudent.setAddress(address.getAddress());
        orderStudent.setPhone(student1.getPhone());
        orderStudent.setStudentName(student1.getName());
        orderStudent.setUserName(student1.getUserName());
        orderStudent.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        orderStudentDao.addOrderStudent(orderStudent);

        return Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),"支付成功");
    }


    /**
    *@note 使用优惠券
    *@auhtor 卢祖飞
    *@date 2019/1/3,14:37
    *@version 1.0
    */
    private BigDecimal useCoupon(Double answeringTime,Double startingTime,BigDecimal startingPrice,Coupon coupon,Teacher teacher,String oid,Address address){
        OrderQuestion orderQuestion = new OrderQuestion();
        BigDecimal price = null;
        //使用优惠券
        if(answeringTime < startingTime){
            price = startingPrice;
            if(price.compareTo(coupon.getSatisfy()) == 1){
                price = price.subtract(coupon.getDiscount());
            }
        }else{
            Double time = answeringTime - startingTime;
            time = Math.ceil(time);
            price = startingPrice.add(teacher.getChargeSettings().multiply(new BigDecimal(time)));
            if(price.compareTo(coupon.getSatisfy()) == 1){
                price = price.subtract(coupon.getDiscount());
            }
        }
        if(price.compareTo(BigDecimal.ZERO) == -1){
            price = new BigDecimal(0.01);
        }
        orderQuestion.setId(Integer.parseInt(oid));
        orderQuestion.setTruePrice(price);
        orderQuestion.setIsCoupon(1);
        orderQuestion.setPayType(2);
        orderQuestion.setIsPay(1);
        orderQuestion.setCouponPrice(coupon.getDiscount());
        orderQuestion.setStatus(3);
        orderQuestion.setAddress(address.getAddress());
        orderQuestionDao.updateOrderQuestion(orderQuestion);
        coupon.setStatus(1);
        couponDao.updateCoupon(coupon);
        return price;
    }

    /**
    *@note 未使用优惠券
    *@auhtor 卢祖飞
    *@date 2019/1/3,14:36
    *@version 1.0
    */
    private BigDecimal noUseCoupon(Double answeringTime,Double startingTime,BigDecimal startingPrice,Teacher teacher,String oid,Address address){
        OrderQuestion orderQuestion = new OrderQuestion();
        BigDecimal price = null;
        if(answeringTime < startingTime){
            price = startingPrice;
        }else{
            Double time = answeringTime - startingTime;
            time = Math.ceil(time);
            price = startingPrice.add(teacher.getChargeSettings().multiply(new BigDecimal(time)));
        }
        orderQuestion.setId(Integer.parseInt(oid));
        orderQuestion.setTruePrice(price);
        orderQuestion.setIsCoupon(0);
        orderQuestion.setPayType(2);
        orderQuestion.setIsPay(1);
        orderQuestion.setCouponPrice(new BigDecimal(0));
        orderQuestion.setStatus(3);
        orderQuestion.setAddress(address.getAddress());
        orderQuestionDao.updateOrderQuestion(orderQuestion);
        return price;
    }

}
