package cn.logicalthinking.models.manage.service;


import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
import cn.logicalthinking.common.exception.DaoException;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import cn.logicalthinking.common.util.JwtUtils;
import cn.logicalthinking.common.util.MD5;
import cn.logicalthinking.common.util.Tools;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Description  后台用户登录
 * @author 黄世杰
 * @date   2018-08-23
 * @version  1.0
 */
@Service
public class AdminUserLoginService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	private Long time=604800000L;//token失效时间为1周
	
	protected Result doWork(IClientData data) throws Exception {
		
		
		String pwd=data.getParameter("pwd");
		String userName=data.getParameter("userName");
		
		//判空
		isNull(userName, pwd);

		pwd=pwd.trim();
		userName=userName.trim();
		//获取登录方式
		Map<String, Object> map = getMap(userName);
		
		//验证用户名是否存在
		SysUser sysUser = isUserName(map);
		
		//验证密码是否正确
		isPwd(sysUser,pwd);
		
		//验证用户状态
		isState(sysUser);
		
		//生成token
		String token = generateToken(sysUser);
		
		//修改用户上一次登上时间
		updateUser(sysUser);
		
		return  Result.jsonMsg("200","登录成功","token",token);
		
	}
	
	private void isNull(String userName,String pwd) throws ValidataException{
		if(StringUtils.isBlank(userName))
			throw new ValidataException("用户名不能为空");
		if(StringUtils.isBlank(pwd))
			throw new ValidataException("密码不能为空");
	}
	
	
	private Map<String,Object> getMap(String userName){
		Map<String,Object> map=new HashMap<String, Object>();
		if(Tools.isEmail(userName))
			map.put("email",userName);
		else if(Tools.isMobile(userName))
			map.put("phone",userName);
		else
			map.put("userName",userName);
		return map;	
	}
	
	private SysUser isUserName(Map<String,Object> map) throws DaoException, ValidataException{
		List<SysUser> users = sysUserDao.selectSysUserListAll(map);
		if(users.size()<=0)
			throw new ValidataException("用户名不存在");
		if(users.size()>1)
			throw new ValidataException("多个用户名存在无法识别");
		return users.get(0);
	}
	private void isPwd(SysUser user,String pwd) throws ValidataException{
		if(!MD5.md5(pwd).equals(user.getPwd()))
			throw new ValidataException("密码不正确");
	}
	
	private void isState(SysUser user) throws ValidataException{
		
		if(!"0".equals(user.getState()))
			throw new ValidataException("该用户已被禁用");
	}
	
	private String generateToken(SysUser user){
		JSONObject userJson=new JSONObject();
		userJson.put("userId",user.getUserId());
		userJson.put("userName",user.getUserName());
		userJson.put("phone",user.getPhone());
		userJson.put("email",user.getEmail());
		
		return JwtUtils.createJWT(user.getUserId(),user.getUserName(), userJson.toString(), time);
	}
	
	
	private void updateUser(SysUser user) throws DaoException{
		user.setLastLoginTime(user.getLoginTime());
		user.setLoginTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		sysUserDao.updateSysUser(user);
	}
	
}
