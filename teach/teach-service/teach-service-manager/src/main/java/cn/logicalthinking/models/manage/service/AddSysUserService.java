package cn.logicalthinking.models.manage.service;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.dao.UserRoleDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.entity.UserRole;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;
import cn.logicalthinking.common.util.UploadFileUtil;
/**
 * @Description  用户表 添加
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class AddSysUserService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	@Resource
	private UserRoleDao userRoleDao;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		String roleId=data.getParameter("roleId");
		
		SysUser sysUser=(SysUser)data.getObject();
		
		//判空
		isNull(sysUser);
		
		//验证用户名是否已经存在
		isUserName(sysUser);
		
		//验证手机号是否存在
		isPhone(sysUser);
		
		//验证邮箱是否存在
		isEmail(sysUser);
		
		//添加用户 
		String userId = addUser(sysUser);
		
		//给用户分配角色
		addUserRole(roleId, userId);
		
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),"添加成功");
		
	}
	
	
	/**
	 * @Description 判空
	 * @author 黄世杰
	 * @下午12:54:25
	 * @version  1.0
	 * @param user
	 */
	private void isNull(SysUser user){
	   ParamValidation.isNotNull(user.getUserName(), "用户名不能为空");
	   ParamValidation.isNotNull(user.getPhone(), "手机号不能为空");
	   ParamValidation.isNotNull(user.getPwd(), "密码不能为空");
	}
	
	/**
	 * @Description 验证用户名是否存在
	 * @author 黄世杰
	 * @下午12:59:59
	 * @version  1.0
	 * @param user
	 */
	private void isUserName(SysUser user){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("userName",user.getUserName());
		List<SysUser> users = sysUserDao.selectSysUserListAll(map);
		if(users.size()>0)
			throw new ValidataException("用户名已经存在");
	}
	
	/**
	 * @Description 验证手机号是否存在
	 * @author 黄世杰
	 * @下午12:59:45
	 * @version  1.0
	 * @param user
	 */
	private void isPhone(SysUser user){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("phone",user.getPhone());
		List<SysUser> users = sysUserDao.selectSysUserListAll(map);
		if(users.size()>0)
			throw new ValidataException("手机号已经存在");
	}
	
	/**
	 * @Description 验证邮箱是否存在
	 * @author 黄世杰
	 * @下午12:59:29
	 * @version  1.0
	 * @param user
	 */
	private void isEmail(SysUser user){
		if(StringUtils.isBlank(user.getEmail()))
			return ;
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("email",user.getEmail());
		List<SysUser> users = sysUserDao.selectSysUserListAll(map);
		if(users.size()>0)
			throw new ValidataException("邮箱已经存在");
	}
	
	
	/**
	 * @Description 添加用户
	 * @author 黄世杰
	 * @下午12:50:01
	 * @version  1.0
	 * @param sysUser
	 */
	private String addUser(SysUser sysUser){
		String userId=Tools.UUID();
		sysUser.setUserId(userId);
		sysUser.setType("0");
		sysUser.setPicturePath(UploadFileUtil.head_portrait);
		sysUser.setPwd(MD5.md5(sysUser.getPwd()));
		sysUserDao.addSysUser(sysUser);
		return userId;
	}
	
	/**
	 * @Description 给用户分配角色
	 * @author 黄世杰
	 * @下午12:47:51
	 * @version  1.0
	 * @param roleId
	 * @param userId
	 */
	private void addUserRole(String roleId,String userId){
		UserRole userRole=new UserRole();
		userRole.setId(Tools.UUID());
		userRole.setRoleId(roleId);
		userRole.setUserId(userId);
		userRoleDao.addUserRole(userRole);
	}
}
