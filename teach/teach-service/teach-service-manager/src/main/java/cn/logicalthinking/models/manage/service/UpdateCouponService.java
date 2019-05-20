package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description  优惠券 修改
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class UpdateCouponService extends AbstractService{
	
	@Resource
	private CouponDao couponDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Coupon coupon=(Coupon)data.getObject();
		
		if(coupon.getId() == null)
			throw new ValidataException("id不能为空");

		coupon.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		couponDao.updateCoupon(coupon);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
