package cn.logicalthinking.models.manage.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysMenuDao;
import cn.logicalthinking.common.entity.SysMenu;
import cn.logicalthinking.common.exception.DaoException;
import cn.logicalthinking.common.exception.ValidataException;
/**
 * @Description  根据角色id查询所有的菜单
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysMenuByRoleIdService extends AbstractService{
	
	@Resource
	private SysMenuDao sysMenuDao;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		String roleId=data.getParameter("roleId");
		if(StringUtils.isBlank(roleId))
			throw new ValidataException("roleId不能为空");
		
		List<SysMenu> menuList = sysMenuDao.selectSysMenuByRoleId(roleId);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),getMenu(menuList));
	}
	
	/**
	 * 作用：处理一级菜单
	 * @param list
	 * @return
	 * @throws DaoException
	 * @下午7:33:26
	 */
	private List<SysMenu> getMenu(List<SysMenu> list) throws DaoException{
		List<SysMenu> menus=new ArrayList<SysMenu>();
		for(int i=0;i<list.size();i++){
			SysMenu menu=list.get(i);
			if(!getMenu(menu.getId(),list))
				continue;
			menus.add(menu);
		}
		return menus;
	}

	/**
	 *
	 * 作用：处理子级菜单
	 * @param id
	 * @param list
	 * @return
	 * @下午7:33:42
	 */
	private boolean getMenu(String id,List<SysMenu> list){
		for(int i=0;i<list.size();i++){
			SysMenu menu=list.get(i);
			if(id.equals(menu.getPId()))
				return false;
		}
		return true;
	}
}
