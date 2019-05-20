package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.OrderStudentDao;
import cn.logicalthinking.common.entity.OrderStudent;
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
public class OrderStudentDaoImpl extends BaseDao implements OrderStudentDao{

	public int addOrderStudent(OrderStudent orderStudent) throws DaoException{
		return super.getSqlSession().insert("addOrderStudent", orderStudent);
	}
	public int batchOrderStudent(List<OrderStudent> list) throws DaoException{
		return super.getSqlSession().insert("batchOrderStudent",list);
	}

	public int updateOrderStudent(OrderStudent orderStudent) throws DaoException{
		return super.getSqlSession().update("updateOrderStudent", orderStudent);
	}

	public int removeOrderStudent(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeOrderStudent", map);
	}

	public OrderStudent selectOrderStudentById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectOrderStudentById",id);
	}

	public List<OrderStudent> selectOrderStudentListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectOrderStudentListAll",map);
	}
	
	public PageInfo<OrderStudent> selectOrderStudentListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<OrderStudent> selectList = super.getSqlSession().selectList("selectOrderStudentListByPage", map);
        return new PageInfo<OrderStudent>(selectList);
	}

	public int selectOrderStudentCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectOrderStudentCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}