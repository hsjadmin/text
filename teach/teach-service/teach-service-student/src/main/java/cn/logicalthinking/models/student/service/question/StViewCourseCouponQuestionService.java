package cn.logicalthinking.models.student.service.question;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xhx
 * @version 1.0
 * @Description 立即报名付款界面查询 可用优惠券接口
 * @date 2018-12-19
 */
@Service
public class StViewCourseCouponQuestionService extends AbstractService {

    @Resource
    private CouponDao couponDao;

    @Resource
    private OrderQuestionDao orderQuestionDao;


    @Override
    protected Result doWork(IClientData data) throws Exception {

        Student studentUser = data.getStudentUser();

        Map<String, Object> map = new HashMap<>();

        //订单id
        String id = data.getParameter("id");

        map.put("studentId",studentUser.getId());

        //查询学生所有优惠券
        List<Coupon> coupons = couponDao.selectCouponListAll(map);
        //查询当前订单信息
        OrderQuestion orderQuestion = orderQuestionDao.selectOrderQuestionById(id);
        List<Coupon> coupons1 = new ArrayList<Coupon>();

        for (Coupon coupon : coupons) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            //优惠券开始使用时间
            Date parse = format.parse(coupon.getStartTime());
            //优惠券到期时间
            Date parse1 = format.parse(coupon.getEndTime());

            //当前时间
            String dateStr = DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME);
            Date parse2 = format.parse(dateStr);
            if(coupon.getSatisfy().compareTo(orderQuestion.getPrice()) == -1 && coupon.getCouponType() == 0 && coupon.getStatus() == 0 && parse.before(parse2) && parse1.after(parse2)){
                coupons1.add(coupon);
            }
        }
        return Result.jsonData(CODE.CODE_200_SELECT.getKey(),coupons1);
    }

}
