package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.FinanceTeacherDao;
import cn.logicalthinking.common.entity.FinanceTeacher;
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
public class FinanceTeacherDaoImpl extends BaseDao implements FinanceTeacherDao{

	public int addFinanceTeacher(FinanceTeacher financeTeacher) throws DaoException{
		return super.getSqlSession().insert("addFinanceTeacher", financeTeacher);
	}
	public int batchFinanceTeacher(List<FinanceTeacher> list) throws DaoException{
		return super.getSqlSession().insert("batchFinanceTeacher",list);
	}

	public int updateFinanceTeacher(FinanceTeacher financeTeacher) throws DaoException{
		return super.getSqlSession().update("updateFinanceTeacher", financeTeacher);
	}

	public int removeFinanceTeacher(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeFinanceTeacher", map);
	}

	public FinanceTeacher selectFinanceTeacherById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectFinanceTeacherById",id);
	}

	public List<FinanceTeacher> selectFinanceTeacherListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectFinanceTeacherListAll",map);
	}
	
	public PageInfo<FinanceTeacher> selectFinanceTeacherListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<FinanceTeacher> selectList = super.getSqlSession().selectList("selectFinanceTeacherListByPage", map);
        return new PageInfo<FinanceTeacher>(selectList);
	}

	public int selectFinanceTeacherCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectFinanceTeacherCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	@Override
	public PageInfo<FinanceTeacher> selectFinanceTeacherbyTeacherId(Map<String, Object> map) throws DaoException {
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<FinanceTeacher> selectList = super.getSqlSession().selectList("selectFinanceTeacherbyTeacherId", map);
		return new PageInfo<FinanceTeacher>(selectList);
	}

	@Override
	public PageInfo<FinanceTeacher> getFinanceTeacherbyTeacherId(Map<String, Object> map) throws DaoException {
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
		PageHelper.startPage(map);
		List<FinanceTeacher> selectList = super.getSqlSession().selectList("getFinanceTeacherbyTeacherId", map);
		return new PageInfo<FinanceTeacher>(selectList);
	}

	@Override
	public int removeFinanceTeacgerBytId(String teacherId) throws DaoException {
		return super.getSqlSession().delete("removeFinanceTeacgerBytId",teacherId);
	}
}