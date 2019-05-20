package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.RoleMenuDao;
import cn.logicalthinking.common.entity.RoleMenu;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-26
 * @version  1.0
 */
@Component
public class RoleMenuDaoImpl extends BaseDao implements RoleMenuDao{

	public int addRoleMenu(RoleMenu roleMenu) throws DaoException{
		return super.getSqlSession().insert("addRoleMenu", roleMenu);
	}
	public int batchRoleMenu(List<RoleMenu> list) throws DaoException{
		return super.getSqlSession().insert("batchRoleMenu",list);
	}

	public int updateRoleMenu(RoleMenu roleMenu) throws DaoException{
		return super.getSqlSession().update("updateRoleMenu", roleMenu);
	}

	public int removeRoleMenu(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeRoleMenu", map);
	}

	public RoleMenu selectRoleMenuById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectRoleMenuById",id);
	}

	public List<RoleMenu> selectRoleMenuListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectRoleMenuListAll",map);
	}
	
	public PageInfo<RoleMenu> selectRoleMenuListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<RoleMenu> selectList = super.getSqlSession().selectList("selectRoleMenuListByPage", map);
        return new PageInfo<RoleMenu>(selectList);
	}

	public int selectRoleMenuCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectRoleMenuCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	
	public int removeRoleMenuByRoleId(String roleId) throws DaoException{
		return super.getSqlSession().delete("removeRoleMenuByRoleId", roleId);
	}
	
}