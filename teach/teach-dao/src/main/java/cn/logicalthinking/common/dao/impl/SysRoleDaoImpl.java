package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.entity.SysRole;
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
public class SysRoleDaoImpl extends BaseDao implements SysRoleDao{

	public int addSysRole(SysRole sysRole) throws DaoException{
		return super.getSqlSession().insert("addSysRole", sysRole);
	}
	public int batchSysRole(List<SysRole> list) throws DaoException{
		return super.getSqlSession().insert("batchSysRole",list);
	}

	public int updateSysRole(SysRole sysRole) throws DaoException{
		return super.getSqlSession().update("updateSysRole", sysRole);
	}

	public int removeSysRole(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeSysRole", map);
	}

	public SysRole selectSysRoleById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectSysRoleById",id);
	}

	public List<SysRole> selectSysRoleListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectSysRoleListAll",map);
	}
	
	public PageInfo<SysRole> selectSysRoleListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<SysRole> selectList = super.getSqlSession().selectList("selectSysRoleListByPage", map);
        return new PageInfo<SysRole>(selectList);
	}

	public int selectSysRoleCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectSysRoleCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}