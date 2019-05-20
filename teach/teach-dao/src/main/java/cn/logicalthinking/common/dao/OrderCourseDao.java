package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.OrderCourse;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface OrderCourseDao {

	//新增
	public int addOrderCourse(OrderCourse orderCourse) throws DaoException;
	
	//批量添加
	public int batchOrderCourse(List<OrderCourse> list) throws DaoException;

	//修改
	public int updateOrderCourse(OrderCourse orderCourse) throws DaoException;

	//删除
	public int removeOrderCourse(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public OrderCourse selectOrderCourseById(Integer id) throws DaoException;

	//条件查询全部
	public List<OrderCourse> selectOrderCourseListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<OrderCourse> selectOrderCourseListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectOrderCourseCount(Map<String, Object> map) throws DaoException;

	/*********************************************自定义扩展sql***********************************************/


	PageInfo<OrderCourse> selectOrderCourseListByPageWithCourseType(Map<String, Object> map);

	PageInfo<OrderCourse> selectOrderCourseListByPageWithCourseTypeAndCourse(Map<String, Object> map);
}