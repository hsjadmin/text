package cn.logicalthinking.common.dao;


import cn.logicalthinking.common.entity.CouponGroup;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2019-01-19
 * @version  1.0
 */

public interface CouponGroupDao {

	//新增
	public int addCouponGroup(CouponGroup couponGroup) throws DaoException;
	
	//批量添加
	public int batchCouponGroup(List<CouponGroup> list) throws DaoException;

	//修改
	public int updateCouponGroup(CouponGroup couponGroup) throws DaoException;

	//删除
	public int removeCouponGroup(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public CouponGroup selectCouponGroupById(String id) throws DaoException;

	//条件查询全部
	public List<CouponGroup> selectCouponGroupListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<CouponGroup> selectCouponGroupListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectCouponGroupCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}