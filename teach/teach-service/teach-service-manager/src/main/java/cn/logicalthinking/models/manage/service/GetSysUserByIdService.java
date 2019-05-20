package cn.logicalthinking.models.manage.service;



import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.exception.ValidataException;
/**
 * @Description  用户表 根据id查询
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetSysUserByIdService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String userId=data.getParameter("userId");
		if(StringUtils.isBlank(userId))
			throw new ValidataException("userId不能为空");
		
		SysUser sysUser = sysUserDao.selectSysUserById(userId);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), sysUser);
	}

}
