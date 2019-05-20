package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.SysMenu;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */

public interface SysMenuDao {

	//新增
	public int addSysMenu(SysMenu sysMenu) throws DaoException;
	
	//批量添加
	public int batchSysMenu(List<SysMenu> list) throws DaoException;

	//修改
	public int updateSysMenu(SysMenu sysMenu) throws DaoException;

	//删除
	public int removeSysMenu(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public SysMenu selectSysMenuById(String id) throws DaoException;

	//条件查询全部
	public List<SysMenu> selectSysMenuListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<SysMenu> selectSysMenuListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectSysMenuCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	
	//根据roleid查询到所有的菜单
	public List<SysMenu> selectSysMenuByRoleId(String roleId) throws DaoException;
	
	//根据userId查询到所有的菜单
	public List<SysMenu> selectSysMenuByUserId(String userId) throws DaoException;
	//根据userId查询到所有的功能菜单
	public List<SysMenu> selectSysMenuBtnByUserId(String userId) throws DaoException;
	
}