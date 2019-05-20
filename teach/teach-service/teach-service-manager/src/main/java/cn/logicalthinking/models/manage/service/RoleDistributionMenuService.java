package cn.logicalthinking.models.manage.service;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.RoleMenuDao;
import cn.logicalthinking.common.entity.RoleMenu;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;
/**
 * @Description  角色分配权限
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class RoleDistributionMenuService extends AbstractService{
	
	@Resource
	private RoleMenuDao roleMenuDao;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		
		String roleId=data.getParameter("roleId");
		String menuIds=data.getParameter("menuIds");
		
		
		//校验证
		isNull(roleId,menuIds);
		
		//先根据角色删除所有的旧权限 
		removeRoleMenuByRoleId(roleId);
		
		//再重新给该用户分配新权限
		addRoleMenuByRoleId(roleId, menuIds);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	private void isNull(String roleId,String menuIds){
	   ParamValidation.isNotNull(roleId, "角色id不能为空");
	   ParamValidation.isNotNull(menuIds, "menuIds不能为空");
	}
	
	private void removeRoleMenuByRoleId(String roleId){
		roleMenuDao.removeRoleMenuByRoleId(roleId);
	}
	
	private void addRoleMenuByRoleId(String roleId,String menuIds){
		String[] menuArr = menuIds.split(",");
		List<RoleMenu> roleMenus=new ArrayList<RoleMenu>();
		for (int i = 0; i < menuArr.length; i++){
			RoleMenu roleMenu=new RoleMenu();
			roleMenu.setId(Tools.UUID());
			roleMenu.setMenuId(menuArr[i]);
			roleMenu.setRoleId(roleId);
			roleMenus.add(roleMenu);
		}
		roleMenuDao.batchRoleMenu(roleMenus);
	}
	
}
