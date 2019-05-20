package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.entity.SysRole;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description  角色表 添加
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class AddSysRoleService extends AbstractService{
	
	@Resource
	private SysRoleDao sysRoleDao;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		
		SysRole sysRole=(SysRole)data.getObject();
		
		//校验证
		isNull(sysRole);
		
		//添加角色
		addRole(sysRole);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),"添加成功");
		
	}
	
	private void isNull(SysRole sysRole){
		   ParamValidation.isNotNull(sysRole.getRoleName(), "角色名不能为空");
	}
	
	private void addRole(SysRole sysRole){
		
		sysRole.setRoleId(Tools.UUID());
		sysRoleDao.addSysRole(sysRole);
	
	}
	
	

}
