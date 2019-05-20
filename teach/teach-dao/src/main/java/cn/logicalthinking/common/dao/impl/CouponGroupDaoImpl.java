package cn.logicalthinking.common.dao.impl;


import cn.logicalthinking.common.entity.CouponGroup;
import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.CouponGroupDao;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2019-01-19
 * @version  1.0
 */
@Component
public class CouponGroupDaoImpl extends BaseDao implements CouponGroupDao {

	public int addCouponGroup(CouponGroup couponGroup) throws DaoException {
		return super.getSqlSession().insert("addCouponGroup", couponGroup);
	}
	public int batchCouponGroup(List<CouponGroup> list) throws DaoException{
		return super.getSqlSession().insert("batchCouponGroup",list);
	}

	public int updateCouponGroup(CouponGroup couponGroup) throws DaoException{
		return super.getSqlSession().update("updateCouponGroup", couponGroup);
	}

	public int removeCouponGroup(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeCouponGroup", map);
	}

	public CouponGroup selectCouponGroupById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectCouponGroupById",id);
	}

	public List<CouponGroup> selectCouponGroupListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectCouponGroupListAll",map);
	}
	
	public PageInfo<CouponGroup> selectCouponGroupListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<CouponGroup> selectList = super.getSqlSession().selectList("selectCouponGroupListByPage", map);
        return new PageInfo<CouponGroup>(selectList);
	}

	public int selectCouponGroupCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectCouponGroupCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}