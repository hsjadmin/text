package cn.logicalthinking.models.manage.service;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.entity.SysRole;
/**
 * @Description 查询所有角色
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysRoleAllService extends AbstractService{
	
	@Resource
	private SysRoleDao sysRoleDao;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		
		Map<String,Object> map=data.initMap();
		
		List<SysRole> sysRoles = sysRoleDao.selectSysRoleListAll(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),sysRoles);
	}

}
