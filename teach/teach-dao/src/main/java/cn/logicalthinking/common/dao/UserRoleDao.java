package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.UserRole;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-26
 * @version  1.0
 */

public interface UserRoleDao {

	//新增
	public int addUserRole(UserRole userRole) throws DaoException;
	
	//批量添加
	public int batchUserRole(List<UserRole> list) throws DaoException;

	//修改
	public int updateUserRole(UserRole userRole) throws DaoException;

	//删除
	public int removeUserRole(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public UserRole selectUserRoleById(String id) throws DaoException;

	//条件查询全部
	public List<UserRole> selectUserRoleListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<UserRole> selectUserRoleListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectUserRoleCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	//根据userId 删除所有的数据
	public int removeUserRoleByUserId(String userId)throws DaoException;

	//根据角色id删除
	public int removeUserRoleByRoleId(String roleId)throws DaoException;


}