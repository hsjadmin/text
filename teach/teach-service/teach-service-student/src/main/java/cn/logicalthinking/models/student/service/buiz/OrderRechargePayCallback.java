package cn.logicalthinking.models.student.service.buiz;

import cn.logicalthinking.common.base.constant.OrderNoConstant;
import cn.logicalthinking.common.base.enums.OrderRechargeStatus;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.dao.FinanceStudentDao;
import cn.logicalthinking.common.dao.OrderRechargeDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.FinanceStudent;
import cn.logicalthinking.common.entity.OrderRecharge;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.pay.Callback;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@Scope("prototype")
public class OrderRechargePayCallback extends Callback {

    public OrderRechargePayCallback() {
        Map<String, Object> result = new HashMap<>(1);
        result.put("callbackName", "orderRechargePayCallback");
        init(result);
    }

    @Resource
    private FinanceStudentDao financeStudentDao;

    @Resource
    private FinanceDao financeDao;

    @Resource
    StudentDao studentDao;

    @Resource
    private OrderRechargeDao orderRechargeDao;

    @Override
    public boolean onProcessed() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.RECHARGE, "");
        OrderRecharge orderRecharge = orderRechargeDao.selectOrderRechargeById(Integer.parseInt(orderNo));

        if (orderRecharge == null) {
            throw new BusinessException("订单异常");
        }

        return OrderRechargeStatus.PAID.getKey().equals(orderRecharge.getStatus())
                || OrderRechargeStatus.PAY_FAIL.getKey().equals(orderRecharge.getStatus())
                || OrderRechargeStatus.CLOSED.getKey().equals(orderRecharge.getStatus());
    }

    @Transactional
    @Override
    public void onSuccess() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.RECHARGE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        // 修改状态
        OrderRecharge orderRecharge = orderRechargeDao.selectOrderRechargeById(Integer.parseInt(orderNo));
        OrderRecharge orderRechargeTemp = new OrderRecharge();
        orderRechargeTemp.setId(orderRecharge.getId());
        orderRechargeTemp.setStatus(OrderRechargeStatus.PAID.getKey());
        orderRechargeTemp.setUpdateTime(timeEnd);

        if (orderRechargeDao.updateOrderRecharge(orderRechargeTemp) == 0) {
            throw new BusinessException("订单状态修改失败");
        }

        // 添加学生的账户费用明细
        String amount = orderRecharge.getAmount().toString();

        FinanceStudent financeStudent = new FinanceStudent();
        String value = String.valueOf(System.currentTimeMillis());
        financeStudent.setId(Integer.parseInt(value.substring(0, value.length() - 3)));
        financeStudent.setMoney(new BigDecimal(amount));
        financeStudent.setStudentId(orderRecharge.getStudentId());
        financeStudent.setName("余额充值");
        Student student = studentDao.selectStudentById(orderRecharge.getStudentId());
        financeStudent.setType(1);
        if (financeStudentDao.addFinanceStudent(financeStudent) == 0) {
            throw new BusinessException("记录失败");
        }

        // 添加到系统的财务管理
//        Finance finance = new Finance();
//        finance.setStudentName(student.getName());
//        finance.setStudentPay(new BigDecimal(amount));
//        finance.setPlatformIncome(new BigDecimal("0"));
//        if (financeDao.addFinance(finance) == 0) {
//            throw new BusinessException("记录失败");
//        }

        // 添加余额
        HashMap<String, Object> map = new HashMap<>();
        BigDecimal balance = student.getBalance();
        balance.setScale(2, BigDecimal.ROUND_HALF_UP);
        balance = balance.add(new BigDecimal(amount));
        map.put("id", student.getId());
        map.put("balance", balance);
        map.put("updateTime", student.getUpdateTime());
        if (studentDao.updateStudentBalance(map) == 0) {
            throw new BusinessException("操作失败");
        }
    }

    @Override
    public void onFail() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.RECHARGE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        OrderRecharge orderRecharge = orderRechargeDao.selectOrderRechargeById(Integer.parseInt(orderNo));
        orderRecharge.setStatus(OrderRechargeStatus.PAY_FAIL.getKey());
        orderRecharge.setUpdateTime(timeEnd);
        if (orderRechargeDao.updateOrderRecharge(orderRecharge) == 0) {
            throw new BusinessException("订单状态修改失败");
        }
    }

    @Override
    public void onTimeout() {
        String orderNo = String.valueOf(getResult().get("orderNo")).replaceAll(OrderNoConstant.RECHARGE, "");
        String timeEnd = getResult().get("timeEnd") == null ? null : getResult().get("timeEnd").toString();

        OrderRecharge orderRecharge = orderRechargeDao.selectOrderRechargeById(Integer.parseInt(orderNo));
        if (orderRecharge == null) {
            return;
        }
        orderRecharge.setStatus(OrderRechargeStatus.CLOSED.getKey());
        orderRecharge.setUpdateTime(timeEnd);
        if (orderRechargeDao.updateOrderRecharge(orderRecharge) == 0) {
            throw new BusinessException("订单状态修改失败");
        }
    }


    public static void main(String[] args) {
        Double d = 0.01;
        BigDecimal bigDecimal = new BigDecimal(d.toString());
        BigDecimal add = bigDecimal.add(new BigDecimal("1.01"));
        add.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(add);
    }
}
