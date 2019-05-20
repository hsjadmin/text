package cn.logicalthinking.models.manage.service;



import java.util.List;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.entity.SysMenu;
import cn.logicalthinking.common.util.AdminUtil;
/**
 * @Description  用户查询所有的按钮
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetUserRoleBtnService extends AbstractService{

	@Override
	protected Result doWork(IClientData data) throws Exception {

		List<SysMenu> menus =AdminUtil.getUserRoleBtn(data.getRequest());

		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),getBtnStr(menus));
	}
	private String getBtnStr(List<SysMenu> menus){
		String btnStr="";
		for (int i = 0; i < menus.size(); i++) {
			SysMenu sysMenu = menus.get(i);
			btnStr+=sysMenu.getHref()+",";
		}
		return btnStr;
	}
}
