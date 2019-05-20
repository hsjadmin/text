package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.OrderQuestionDao;
import cn.logicalthinking.common.entity.OrderQuestion;
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
public class OrderQuestionDaoImpl extends BaseDao implements OrderQuestionDao{

	public int addOrderQuestion(OrderQuestion orderQuestion) throws DaoException{
		return super.getSqlSession().insert("addOrderQuestion", orderQuestion);
	}
	public int batchOrderQuestion(List<OrderQuestion> list) throws DaoException{
		return super.getSqlSession().insert("batchOrderQuestion",list);
	}

	public int updateOrderQuestion(OrderQuestion orderQuestion) throws DaoException{
		return super.getSqlSession().update("updateOrderQuestion", orderQuestion);
	}

	public int removeOrderQuestion(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeOrderQuestion", map);
	}

	public OrderQuestion selectOrderQuestionById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectOrderQuestionById",id);
	}

	public List<OrderQuestion> selectOrderQuestionListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectOrderQuestionListAll",map);
	}
	
	public PageInfo<OrderQuestion> selectOrderQuestionListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<OrderQuestion> selectList = super.getSqlSession().selectList("selectOrderQuestionListByPage", map);
        return new PageInfo<OrderQuestion>(selectList);
	}

	public int selectOrderQuestionCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectOrderQuestionCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	@Override
	public int getOrderQuestionCount(Map<String, Object> map) throws DaoException {
		Object obj = super.getSqlSession().selectOne("getOrderQuestionCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
}