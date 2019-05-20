package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.SysRole;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */

public interface SysRoleDao {

	//新增
	public int addSysRole(SysRole sysRole) throws DaoException;
	
	//批量添加
	public int batchSysRole(List<SysRole> list) throws DaoException;

	//修改
	public int updateSysRole(SysRole sysRole) throws DaoException;

	//删除
	public int removeSysRole(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public SysRole selectSysRoleById(String id) throws DaoException;

	//条件查询全部
	public List<SysRole> selectSysRoleListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<SysRole> selectSysRoleListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectSysRoleCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}