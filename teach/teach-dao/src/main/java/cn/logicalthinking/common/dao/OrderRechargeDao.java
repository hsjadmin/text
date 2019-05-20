package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.OrderRecharge;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-29
 * @version  1.0
 */

public interface OrderRechargeDao {

	//新增
	public int addOrderRecharge(OrderRecharge orderRecharge) throws DaoException;
	
	//批量添加
	public int batchOrderRecharge(List<OrderRecharge> list) throws DaoException;

	//修改
	public int updateOrderRecharge(OrderRecharge orderRecharge) throws DaoException;

	//删除
	public int removeOrderRecharge(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public OrderRecharge selectOrderRechargeById(Integer id) throws DaoException;

	//条件查询全部
	public List<OrderRecharge> selectOrderRechargeListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<OrderRecharge> selectOrderRechargeListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectOrderRechargeCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	int removeOrderRechargeBysId(String studentId) throws DaoException;
}