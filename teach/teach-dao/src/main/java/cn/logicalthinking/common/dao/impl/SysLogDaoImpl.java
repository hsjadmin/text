package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.SysLogDao;
import cn.logicalthinking.common.entity.SysLog;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */
@Component
public class SysLogDaoImpl extends BaseDao implements SysLogDao{

	public int addSysLog(SysLog sysLog) throws DaoException{
		return super.getSqlSession().insert("addSysLog", sysLog);
	}
	public int batchSysLog(List<SysLog> list) throws DaoException{
		return super.getSqlSession().insert("batchSysLog",list);
	}

	public int updateSysLog(SysLog sysLog) throws DaoException{
		return super.getSqlSession().update("updateSysLog", sysLog);
	}

	public int removeSysLog(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeSysLog", map);
	}

	public SysLog selectSysLogById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectSysLogById",id);
	}

	public List<SysLog> selectSysLogListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectSysLogListAll",map);
	}
	
	public PageInfo<SysLog> selectSysLogListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<SysLog> selectList = super.getSqlSession().selectList("selectSysLogListByPage", map);
        return new PageInfo<SysLog>(selectList);
	}

	public int selectSysLogCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectSysLogCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}

	@Override
	public PageInfo<SysLog> selectSysLogListByPageCus(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<SysLog> selectList = super.getSqlSession().selectList("selectSysLogListByPageCus", map);
        return new PageInfo<SysLog>(selectList);
	}

	/*********************************************自定义扩展sql***********************************************/
	

}