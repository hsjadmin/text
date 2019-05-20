package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.dao.FinanceTeacherDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.Finance;
import cn.logicalthinking.common.entity.FinanceTeacher;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/28,11:37
 */
@Service
public class CashWithdrawalService extends AbstractService {

    @Resource
    private FinanceTeacherDao financeTeacherDao;

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private FinanceDao financeDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //提现金额
        String money = data.getParameter("money");
        //提现密码
        String payPwd = data.getParameter("payPwd");

        Teacher teacherUser = data.getTeacherUser();
        //非空验证
        isNull(money,payPwd,teacherUser);

        Teacher teacher = teacherDao.selectTeacherById(teacherUser.getId());
        //验证金额
        checkMoney(money,teacher.getBalance());
        //验证提现密码
        checkPwd(payPwd,teacher);

        Map<String, Object> map = new HashMap<>();
        map.put("money",money);
        map.put("id",teacher.getId());

        teacherDao.updateBalance(map);

        FinanceTeacher financeTeacher = new FinanceTeacher();
        financeTeacher.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        financeTeacher.setIsFinish("0");
        financeTeacher.setMoney(new BigDecimal(money));
        financeTeacher.setTeacherId(teacher.getId());
        financeTeacher.setType(1);
        financeTeacher.setName("教师提现");
        financeTeacherDao.addFinanceTeacher(financeTeacher);

        Finance finance = new Finance();
        finance.setTeacherName(teacher.getName());
        finance.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
        finance.setTeacherIncome(new BigDecimal(money));
        finance.setPlatformIncome(BigDecimal.ZERO);
        finance.setStudentPay(BigDecimal.ZERO);
        finance.setPlatformIncome(BigDecimal.ZERO);
        finance.setTeacherBonusRatio(teacher.getCharges());
        financeDao.addFinance(finance);

        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(),"申请成功,请耐心等待");
    }
    //非空验证
    private void isNull(String money,String payPwd,Teacher teacherUser){
        ParamValidation.isNotNull(teacherUser,"登录超时,请重新登陆!");
        ParamValidation.isNotNull(money,"金额不能为空");
        ParamValidation.isNotNull(payPwd,"密码不能为空");
    }

    //验证提现金额
    private void checkMoney(String money, BigDecimal balance){
        BigDecimal outMoney = null;
        try {
            outMoney = new BigDecimal(money);
        } catch (Exception e) {
            throw new ValidataException("金额格式错误");
        }
        if(outMoney.compareTo(balance) == 1){
            throw new ValidataException("提现金额大于已有金额");
        }
    }
    //验证提现密码
    private void checkPwd(String pwd,Teacher teacher){
        pwd = MD5.md5(pwd);
        if(!pwd.equals(teacher.getPayPwd())){
            throw new ValidataException("提现密码错误");
        }
    }

}
