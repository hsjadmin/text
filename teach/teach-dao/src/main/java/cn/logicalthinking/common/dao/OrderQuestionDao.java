package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.OrderQuestion;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface OrderQuestionDao {

	//新增
	public int addOrderQuestion(OrderQuestion orderQuestion) throws DaoException;
	
	//批量添加
	public int batchOrderQuestion(List<OrderQuestion> list) throws DaoException;

	//修改
	public int updateOrderQuestion(OrderQuestion orderQuestion) throws DaoException;

	//删除
	public int removeOrderQuestion(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public OrderQuestion selectOrderQuestionById(String id) throws DaoException;

	//条件查询全部
	public List<OrderQuestion> selectOrderQuestionListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<OrderQuestion> selectOrderQuestionListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectOrderQuestionCount(Map<String, Object> map) throws DaoException;


	/*********************************************自定义扩展sql***********************************************/
	public int getOrderQuestionCount(Map<String, Object> map) throws DaoException;
}