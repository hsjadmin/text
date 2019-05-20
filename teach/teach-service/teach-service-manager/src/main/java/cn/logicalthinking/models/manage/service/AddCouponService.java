package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.entity.CouponGroup;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.dao.CouponGroupDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.entity.Student;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 优惠券 添加
 * @date 2018-12-19
 */
@Service
public class AddCouponService extends AbstractService {

    @Resource
    private StudentDao studentDao;

    @Resource
    private CouponDao couponDao;

    @Resource
    private CouponGroupDao couponGroupDao;

    private IClientData data;


    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        //获取所有学生
        List<Student> students = studentDao.selectStudentListAll(null);

        CouponGroup couponGroup = (CouponGroup) data.getObject();

        List<Coupon> coupons = new ArrayList<>();
        String id = Tools.UUID();

        couponGroupDao.addCouponGroup(couponGroup);

        for (Student student : students) {
            Coupon temp = new Coupon();
            temp.setIdentification(id);
            temp.setName(student.getName());
            temp.setStudentId(student.getId());
            temp.setUserName(student.getUserName());
            temp.setCreateTime(DateUtil.getDateStr(new Date(), DateUtil.DATE_TIME));
            temp.setStartTime(couponGroup.getStartTime());
            temp.setEndTime(couponGroup.getEndTime());
            temp.setSatisfy(couponGroup.getSatisfy());
            temp.setDiscount(couponGroup.getDiscount());
            temp.setCouponType(couponGroup.getCouponType());
            temp.setCouponName(couponGroup.getCouponName());
            temp.setStatus(0);
            temp.setIdentification(couponGroup.getId());
            coupons.add(temp);
        }
        couponDao.batchCoupon(coupons);



        return Result.jsonMsg(CODE.CODE_200_ADD.getKey(), CODE.CODE_200_ADD.getValue());

    }


}
