package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.CouponDao;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@Component
public class CouponDaoImpl extends BaseDao implements CouponDao{

	public int addCoupon(Coupon coupon) throws DaoException{
		return super.getSqlSession().insert("addCoupon", coupon);
	}
	public int batchCoupon(List<Coupon> list) throws DaoException{
		return super.getSqlSession().insert("batchCoupon",list);
	}

	public int updateCoupon(Coupon coupon) throws DaoException{
		return super.getSqlSession().update("updateCoupon", coupon);
	}

	public int removeCoupon(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeCoupon", map);
	}

	public Coupon selectCouponById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectCouponById",id);
	}

	public List<Coupon> selectCouponListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectCouponListAll",map);
	}
	
	public PageInfo<Coupon> selectCouponListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<Coupon> selectList = super.getSqlSession().selectList("selectCouponListByPage", map);
        return new PageInfo<Coupon>(selectList);
	}

	public int selectCouponCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectCouponCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/


	public List<Coupon> selectAvailableCouponByStudentIdAndCourseTypeId(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		return super.getSqlSession().selectList("selectAvailableCouponByStudentIdAndCourseTypeId", map);
	}

	@Override
	public int removeCouponBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeCouponBysId",studentId);
	}

	@Override
	public PageInfo<Coupon> selectCouponListByPageOne(Map<String, Object> map) throws DaoException {
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<Coupon> selectList = super.getSqlSession().selectList("selectCouponListByPageOne", map);
		return new PageInfo<Coupon>(selectList);
	}
}