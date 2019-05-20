package cn.logicalthinking.models.manage.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.dao.UserRoleDao;
import cn.logicalthinking.common.entity.UserRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysRoleDao;
import cn.logicalthinking.common.exception.ValidataException;
/**
 * @Description  角色表 删除
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class RemoveSysRoleService extends AbstractService{
	
	@Resource
	private SysRoleDao sysRoleDao;

	@Resource
	private SysUserDao sysUserDao;

	@Resource
	private UserRoleDao userRoleDao;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String roleIds=data.getParameter("roleIds");
		if(StringUtils.isBlank(roleIds))
			throw new ValidataException("roleIds不能为空");
		
		map.put("roleIds",getIds(roleIds));

		//查询删除角色的用户角色表
		Map<String, Object> map1 = new HashMap<>();
		map1.put("roleId",roleIds);
		List<UserRole> userRoles = userRoleDao.selectUserRoleListAll(map1);
		String userId = "";
		for (UserRole userRole : userRoles) {
			userId = userId + "," + userRole.getUserId();
		}

		HashMap<String, Object> map2 = new HashMap<>();
		map2.put("userIds",getIds(userId));
		//删除用户
		sysUserDao.removeSysUser(map2);
		//删除用户角色
		userRoleDao.removeUserRoleByRoleId(roleIds);
		
		sysRoleDao.removeSysRole(map);
		
		return  Result.jsonMsg(CODE.CODE_200_DELETE.getKey(),"删除成功");
		
	}
	
	private static String getIds(String idsStr){
		String ids="";
		if(StringUtils.isBlank(idsStr))
			return ids;
		
		String[] arr=idsStr.split(",");
		for (int i = 0; i < arr.length; i++) {
			ids+="'"+arr[i]+"',";
		}
		if(StringUtils.isBlank(ids))
			return idsStr;
		if(ids.lastIndexOf(",")!=-1)
			ids=ids.substring(0,ids.lastIndexOf(","));
		return ids;
	}

}
