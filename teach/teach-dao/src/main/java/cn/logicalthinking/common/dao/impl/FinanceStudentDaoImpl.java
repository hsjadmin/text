package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.FinanceStudentDao;
import cn.logicalthinking.common.entity.FinanceStudent;
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
public class FinanceStudentDaoImpl extends BaseDao implements FinanceStudentDao{

	public int addFinanceStudent(FinanceStudent financeStudent) throws DaoException{
		return super.getSqlSession().insert("addFinanceStudent", financeStudent);
	}
	public int batchFinanceStudent(List<FinanceStudent> list) throws DaoException{
		return super.getSqlSession().insert("batchFinanceStudent",list);
	}

	public int updateFinanceStudent(FinanceStudent financeStudent) throws DaoException{
		return super.getSqlSession().update("updateFinanceStudent", financeStudent);
	}

	public int removeFinanceStudent(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeFinanceStudent", map);
	}

	public FinanceStudent selectFinanceStudentById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectFinanceStudentById",id);
	}

	public List<FinanceStudent> selectFinanceStudentListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectFinanceStudentListAll",map);
	}
	
	public PageInfo<FinanceStudent> selectFinanceStudentListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<FinanceStudent> selectList = super.getSqlSession().selectList("selectFinanceStudentListByPage", map);
        return new PageInfo<FinanceStudent>(selectList);
	}

	public int selectFinanceStudentCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectFinanceStudentCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	@Override
	public PageInfo<FinanceStudent> selectFinanceStudentByStudentId(Map<String, Object> map) throws DaoException {
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<FinanceStudent> selectList = super.getSqlSession().selectList("selectFinanceStudentByStudentId", map);
		return new PageInfo<FinanceStudent>(selectList);
	}

	@Override
	public int removeFinanceStudentBysId(String studentId) throws DaoException {
		return super.getSqlSession().delete("removeFinanceStudentBysId",studentId);
	}
}