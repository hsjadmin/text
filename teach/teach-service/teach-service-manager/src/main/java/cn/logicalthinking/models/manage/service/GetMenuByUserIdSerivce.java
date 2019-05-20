package cn.logicalthinking.models.manage.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.entity.SysMenu;
import cn.logicalthinking.common.exception.DaoException;
import cn.logicalthinking.common.util.AdminUtil;
/**
 * @Description  用户查询所有的权限
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetMenuByUserIdSerivce extends AbstractService{

	@Override
	protected Result doWork(IClientData data) throws Exception {

		List<SysMenu> menus =AdminUtil.getUserMenu(data.getRequest());

		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),getMenu(menus));
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
			if("0".equals(menu.getPId())){
				menu.setSpread(false);
				menu.setTitle(menu.getName());
				menu.setChildren(getMenu(menu.getId(),list));
				menus.add(menu);
			/*	list.remove(menu);*/
			}

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
	private List<SysMenu> getMenu(String id,List<SysMenu> list){
		List<SysMenu> menusList=new ArrayList<SysMenu>();
		for(int i=0;i<list.size();i++){
			SysMenu menu=list.get(i);
			if(id.equals(menu.getPId())){
				menu.setSpread(false);
				menu.setTitle(menu.getName());
				menu.setChildren(getMenu(menu.getId(),menusList));
				menusList.add(menu);
				/*list.remove(menu);*/
			}
		}
		return menusList;
	}
}
