package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.RoleMenu;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-26
 * @version  1.0
 */

public interface RoleMenuDao {

	//新增
	public int addRoleMenu(RoleMenu roleMenu) throws DaoException;
	
	//批量添加
	public int batchRoleMenu(List<RoleMenu> list) throws DaoException;

	//修改
	public int updateRoleMenu(RoleMenu roleMenu) throws DaoException;

	//删除
	public int removeRoleMenu(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public RoleMenu selectRoleMenuById(String id) throws DaoException;

	//条件查询全部
	public List<RoleMenu> selectRoleMenuListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<RoleMenu> selectRoleMenuListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectRoleMenuCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	
	//根据角色id删除所有权限
	public int removeRoleMenuByRoleId(String roleId) throws DaoException;
	
}