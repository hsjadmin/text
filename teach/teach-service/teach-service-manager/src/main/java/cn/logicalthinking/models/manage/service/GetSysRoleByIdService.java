package cn.logicalthinking.models.manage.service;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.entity.SysRole;
import cn.logicalthinking.common.exception.ValidataException;
/**
 * @Description  角色表 根据id查询
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysRoleByIdService extends AbstractService{
	
	@Resource
	private SysRoleDao sysRoleDao;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		String roleId=data.getParameter("roleId");
		if(StringUtils.isBlank(roleId))
			throw new ValidataException("roleId不能为空");
		
		SysRole sysRole = sysRoleDao.selectSysRoleById(roleId);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), sysRole);
	}

}
