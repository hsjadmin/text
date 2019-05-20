package cn.logicalthinking.models.manage.service;



import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.util.ParamValidation;
/**
 * @Description  后台用户启用禁用
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class UpdateAdminStateService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		String userId=data.getParameter("userId");
		String state=data.getParameter("state");
		
		//判空
		isNull(userId,state);
		
		//修改用户 
		updateUser(userId,state);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	/**
	 * @Description 判空
	 * @author 黄世杰
	 * @下午12:54:25
	 * @version  1.0
	 * @param user
	 */
	private void isNull(String userId,String state){
	   ParamValidation.isNotNull(userId,"主键不能为空");
	   ParamValidation.isNotNull(state, "state不能为空");
	}
	
	private void updateUser(String userId,String state){
		SysUser sysUser=new SysUser();
		sysUser.setUserId(userId);
		sysUser.setState(state);
		sysUserDao.updateSysUser(sysUser);
	}
	

}
