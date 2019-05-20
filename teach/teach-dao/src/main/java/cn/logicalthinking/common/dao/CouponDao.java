package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface CouponDao {

	//新增
	public int addCoupon(Coupon coupon) throws DaoException;
	
	//批量添加
	public int batchCoupon(List<Coupon> list) throws DaoException;

	//修改
	public int updateCoupon(Coupon coupon) throws DaoException;

	//删除
	public int removeCoupon(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public Coupon selectCouponById(String id) throws DaoException;

	//条件查询全部
	public List<Coupon> selectCouponListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<Coupon> selectCouponListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectCouponCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	//查询可用优惠券
    List<Coupon> selectAvailableCouponByStudentIdAndCourseTypeId(Map<String, Object> map) throws DaoException;

    int removeCouponBysId(String studentId) throws DaoException;

	//分页查询
	public PageInfo<Coupon> selectCouponListByPageOne(Map<String, Object> map) throws DaoException;
}