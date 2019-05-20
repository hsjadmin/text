package cn.logicalthinking.models.manage.service;


import io.jsonwebtoken.Claims;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.exception.PermissionException;
import cn.logicalthinking.common.util.JwtUtils;
/**
 * @Description  后台用户退出登录
 * @author 黄世杰
 * @date   2018-08-23
 * @version  1.0
 */
@Service
public class AdminUserOutLoginSerivce extends AbstractService{
	
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String token=this.data.getRequest().getHeader("token");
		if(StringUtils.isBlank(token))
			throw new PermissionException("登录超时，请重新登录");
		Claims claims = JwtUtils.parseJWT(token);
		if(claims==null)
			throw new PermissionException("登录超时，请重新登录");
		if(new Date().getTime()>claims.getExpiration().getTime())
			throw new PermissionException("当前用户已经失效");
		
		
		return  Result.jsonMsg("200","退出成功");
		
	}
	
}
