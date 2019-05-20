package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.entity.Finance;
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
public class FinanceDaoImpl extends BaseDao implements FinanceDao{

	public int addFinance(Finance finance) throws DaoException{
		return super.getSqlSession().insert("addFinance", finance);
	}
	public int batchFinance(List<Finance> list) throws DaoException{
		return super.getSqlSession().insert("batchFinance",list);
	}

	public int updateFinance(Finance finance) throws DaoException{
		return super.getSqlSession().update("updateFinance", finance);
	}

	public int removeFinance(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeFinance", map);
	}

	public Finance selectFinanceById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectFinanceById",id);
	}

	public List<Finance> selectFinanceListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectFinanceListAll",map);
	}
	
	public PageInfo<Finance> selectFinanceListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<Finance> selectList = super.getSqlSession().selectList("selectFinanceListByPage", map);
        return new PageInfo<Finance>(selectList);
	}

	public int selectFinanceCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectFinanceCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}