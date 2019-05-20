package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.OrderStudent;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface OrderStudentDao {

	//新增
	public int addOrderStudent(OrderStudent orderStudent) throws DaoException;
	
	//批量添加
	public int batchOrderStudent(List<OrderStudent> list) throws DaoException;

	//修改
	public int updateOrderStudent(OrderStudent orderStudent) throws DaoException;

	//删除
	public int removeOrderStudent(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public OrderStudent selectOrderStudentById(String id) throws DaoException;

	//条件查询全部
	public List<OrderStudent> selectOrderStudentListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<OrderStudent> selectOrderStudentListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectOrderStudentCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}