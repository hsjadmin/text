package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.OrderRechargeDao;
import cn.logicalthinking.common.entity.OrderRecharge;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-29
 * @version  1.0
 */
@Component
public class OrderRechargeDaoImpl extends BaseDao implements OrderRechargeDao {

	public int addOrderRecharge(OrderRecharge orderRecharge) throws DaoException {
		return super.getSqlSession().insert("addOrderRecharge", orderRecharge);
	}
	public int batchOrderRecharge(List<OrderRecharge> list) throws DaoException{
		return super.getSqlSession().insert("batchOrderRecharge",list);
	}

	public int updateOrderRecharge(OrderRecharge orderRecharge) throws DaoException{
		return super.getSqlSession().update("updateOrderRecharge", orderRecharge);
	}

	public int removeOrderRecharge(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeOrderRecharge", map);
	}

	public OrderRecharge selectOrderRechargeById(Integer id) throws DaoException{
		return super.getSqlSession().selectOne("selectOrderRechargeById",id);
	}

	public List<OrderRecharge> selectOrderRechargeListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectOrderRechargeListAll",map);
	}
	
	public PageInfo<OrderRecharge> selectOrderRechargeListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<OrderRecharge> selectList = super.getSqlSession().selectList("selectOrderRechargeListByPage", map);
        return new PageInfo<OrderRecharge>(selectList);
	}

	public int selectOrderRechargeCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectOrderRechargeCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	@Override
	public int removeOrderRechargeBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeOrderRechargeBysId",studentId);
	}
}