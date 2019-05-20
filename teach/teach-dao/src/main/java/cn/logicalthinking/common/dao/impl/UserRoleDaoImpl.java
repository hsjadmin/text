package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.UserRoleDao;
import cn.logicalthinking.common.entity.UserRole;
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
public class UserRoleDaoImpl extends BaseDao implements UserRoleDao{

	public int addUserRole(UserRole userRole) throws DaoException{
		return super.getSqlSession().insert("addUserRole", userRole);
	}
	public int batchUserRole(List<UserRole> list) throws DaoException{
		return super.getSqlSession().insert("batchUserRole",list);
	}

	public int updateUserRole(UserRole userRole) throws DaoException{
		return super.getSqlSession().update("updateUserRole", userRole);
	}

	public int removeUserRole(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeUserRole", map);
	}

	public UserRole selectUserRoleById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectUserRoleById",id);
	}

	public List<UserRole> selectUserRoleListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectUserRoleListAll",map);
	}
	
	public PageInfo<UserRole> selectUserRoleListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<UserRole> selectList = super.getSqlSession().selectList("selectUserRoleListByPage", map);
        return new PageInfo<UserRole>(selectList);
	}

	public int selectUserRoleCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectUserRoleCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	
	@Override
	public int removeUserRoleByUserId(String userId) throws DaoException {
		return super.getSqlSession().delete("removeUserRoleByUserId",userId);
	}
	@Override
	public int removeUserRoleByRoleId(String roleId) throws DaoException {
		return super.getSqlSession().delete("removeUserRoleByRoleId",roleId);
	}

}