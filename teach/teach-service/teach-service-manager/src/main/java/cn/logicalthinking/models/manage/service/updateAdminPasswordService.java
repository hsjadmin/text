package cn.logicalthinking.models.manage.service;


import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.MD5;

/**
 * @Description  系统用户修改密码
 * @author 黄世杰
 * @date   2018-08-23
 * @version  1.0
 */
@Service
public class updateAdminPasswordService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	protected Result doWork(IClientData data) throws Exception {
		
		String oldPwd=data.getParameter("oldPwd");
		String newPwd=data.getParameter("newPwd");
		String truePwd=data.getParameter("truePwd");
		if(StringUtils.isBlank(oldPwd))
			throw new ValidataException("旧密码不能为空");
		if(StringUtils.isBlank(newPwd))
			throw new ValidataException("新密码不能为空");
		if(StringUtils.isBlank(truePwd))
			throw new ValidataException("确认密码不能为空");
		if(!newPwd.equals(truePwd))
			throw new ValidataException("两次密码不一致");
		
		SysUser sysUser = data.getUser();
		
		if(!MD5.md5(oldPwd).equals(sysUser.getPwd()))
			throw new ValidataException("原密码不正确");
		
		sysUser.setPwd(MD5.md5(truePwd));
		
		sysUserDao.updateSysUser(sysUser);
		
		return  Result.jsonMsg("200","修改成功");
		
	}

}
