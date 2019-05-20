package cn.logicalthinking.common.util;

import cn.logicalthinking.common.dao.SysMenuDao;
import cn.logicalthinking.common.entity.SysMenu;
import cn.logicalthinking.common.exception.PermissionException;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description
 * @author 黄世杰 
 * @2018-11-7
 * @version  1.0
 */
@Component
public class AdminUtil {
	
	
	public static SysMenuDao sysMenuDao;
	
	//public static RedisManager redisManager;
	//菜单前缀
	public static String USERMENU="USERMENU";
	//操作按钮前缀
	public static String USERMENUBTN="USERMENUBTN";
	//每一分钟重新插一遍   24*60*60 * 1000
	public static final Integer expires=60 * 1000;
	
	public static List<SysMenu> getUserMenu(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (StringUtils.isBlank(token))
			throw new PermissionException("登录超时，请重新登录");
		Claims claims = JwtUtils.parseJWT(token);
		if (claims == null)
			throw new PermissionException("登录超时，请重新登录");
		String userId = claims.getId();
		USERMENU=USERMENU+userId;
		//byte[] menuStr = redisManager.get(USERMENU.getBytes());
		
		//如果redisManager存在  则直接从redisManager取
//		if(menuStr!=null){
//			List<SysMenu> menus=(List<SysMenu>)SerializeUtil.deserialize(menuStr);
//			if(menus.size()<=0)
//				return getMenu(userId,USERMENU);
//			return menus;
//		}
		return getMenu(userId,USERMENU);
	}
	
	public static List<SysMenu> getUserRoleBtn(HttpServletRequest request) {
		String token = request.getHeader("token");
		if (StringUtils.isBlank(token))
			throw new PermissionException("登录超时，请重新登录");
		Claims claims = JwtUtils.parseJWT(token);
		if (claims == null)
			throw new PermissionException("登录超时，请重新登录");
		String userId = claims.getId();
		USERMENUBTN=USERMENUBTN+userId;
		//byte[] menuStr = redisManager.get(USERMENUBTN.getBytes());
		
		//如果redisManager存在  则直接从redisManager取
		/*if(menuStr!=null){
			List<SysMenu> menus=(List<SysMenu>)SerializeUtil.deserialize(menuStr);
			if(menus.size()<=0)
				return getUserBtn(userId,USERMENUBTN);
			return menus;
		}*/
		return getUserBtn(userId,USERMENUBTN);
	}
	
	private static List<SysMenu> getMenu(String userId,String str){
		List<SysMenu> sysMenus = sysMenuDao.selectSysMenuByUserId(userId);
		//redisManager.set(str.getBytes(),SerializeUtil.serialize(sysMenus),expires);
		return sysMenus;
	}
	private static List<SysMenu> getUserBtn(String userId,String str){
		List<SysMenu> sysMenus = sysMenuDao.selectSysMenuBtnByUserId(userId);
		//redisManager.set(str.getBytes(),SerializeUtil.serialize(sysMenus),expires);
		return sysMenus;
	}
	
	public SysMenuDao getSysMenuDao() {
		return sysMenuDao;
	}
	
	@Resource
	public void setSysMenuDao(SysMenuDao sysMenuDao) {
		AdminUtil.sysMenuDao = sysMenuDao;
	}

	/*public RedisManager getRedisManager() {
		return redisManager;
	}
	
	@Resource
	public void setRedisManager(RedisManager redisManager) {
		AdminUtil.redisManager = redisManager;
	}*/
	

}
