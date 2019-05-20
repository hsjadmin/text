package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */

public interface SysUserDao {

	//新增
	public int addSysUser(SysUser sysUser) throws DaoException;
	
	//批量添加
	public int batchSysUser(List<SysUser> list) throws DaoException;

	//修改
	public int updateSysUser(SysUser sysUser) throws DaoException;

	//删除
	public int removeSysUser(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public SysUser selectSysUserById(String id) throws DaoException;

	//条件查询全部
	public List<SysUser> selectSysUserListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<SysUser> selectSysUserListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectSysUserCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	
	//分页查询多表查询到角色
	public PageInfo<SysUser> selectSysUserRoleListByPage(Map<String, Object> map) throws DaoException;

}