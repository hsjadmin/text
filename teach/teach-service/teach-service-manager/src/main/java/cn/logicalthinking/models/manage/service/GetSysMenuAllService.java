package cn.logicalthinking.models.manage.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.MenuZ_Tree;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysMenuDao;
import cn.logicalthinking.common.entity.SysMenu;
/**
 * @Description  查询所有的菜单
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysMenuAllService extends AbstractService{
	
	@Resource
	private SysMenuDao sysMenuDao;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		List<SysMenu> menuList = sysMenuDao.selectSysMenuListAll(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), getMenu(menuList));
	}
	
	private List<MenuZ_Tree> getMenu(List<SysMenu> menuList){
		List<MenuZ_Tree> list=new ArrayList<MenuZ_Tree>();
		for (int i = 0; i < menuList.size(); i++) {
			SysMenu sysMenu = menuList.get(i);
			MenuZ_Tree menu=new MenuZ_Tree();
			menu.setId(sysMenu.getId());
			menu.setOpen(false);
			if("1".equals(sysMenu.getType())){
				menu.setIcon("../../style/plugins/z-tree/css/zTreeStyle/img/diy/1_open.png");
			}else if("2".equals(sysMenu.getType())){
				menu.setIcon("../../style/plugins/z-tree/css/zTreeStyle/img/diy/6.png");
			}else if("3".equals(sysMenu.getType())){
				menu.setIcon("../../style/plugins/z-tree/css/zTreeStyle/img/diy/5.png");
			}else{
				menu.setIcon("../../style/plugins/z-tree/css/zTreeStyle/img/diy/3.png");
			}
			menu.setpId(sysMenu.getPId());
			menu.setName(sysMenu.getName());
			list.add(menu);
		}
		return list;
	}

}
