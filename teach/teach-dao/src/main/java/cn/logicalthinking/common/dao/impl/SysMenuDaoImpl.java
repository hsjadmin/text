package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.SysMenuDao;
import cn.logicalthinking.common.entity.SysMenu;
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
public class SysMenuDaoImpl extends BaseDao implements SysMenuDao{

	public int addSysMenu(SysMenu sysMenu) throws DaoException{
		return super.getSqlSession().insert("addSysMenu", sysMenu);
	}
	public int batchSysMenu(List<SysMenu> list) throws DaoException{
		return super.getSqlSession().insert("batchSysMenu",list);
	}

	public int updateSysMenu(SysMenu sysMenu) throws DaoException{
		return super.getSqlSession().update("updateSysMenu", sysMenu);
	}

	public int removeSysMenu(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeSysMenu", map);
	}

	public SysMenu selectSysMenuById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectSysMenuById",id);
	}

	public List<SysMenu> selectSysMenuListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectSysMenuListAll",map);
	}
	
	public PageInfo<SysMenu> selectSysMenuListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<SysMenu> selectList = super.getSqlSession().selectList("selectSysMenuListByPage", map);
        return new PageInfo<SysMenu>(selectList);
	}

	public int selectSysMenuCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectSysMenuCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	
	public List<SysMenu> selectSysMenuByRoleId(String roleId) throws DaoException{
		return super.getSqlSession().selectList("selectSysMenuByRoleId",roleId);
	}
	@Override
	public List<SysMenu> selectSysMenuByUserId(String userId)
			throws DaoException {
		return super.getSqlSession().selectList("selectSysMenuByUserId",userId);
	}
	@Override
	public List<SysMenu> selectSysMenuBtnByUserId(String userId)
			throws DaoException {
		return super.getSqlSession().selectList("selectSysMenuBtnByUserId",userId);
	}
	
}