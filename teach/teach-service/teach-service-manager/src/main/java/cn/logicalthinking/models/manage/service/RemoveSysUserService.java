package cn.logicalthinking.models.manage.service;



import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.exception.ValidataException;
/**
 * @Description  用户表 删除
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class RemoveSysUserService extends AbstractService{
	
	@Resource
	private SysUserDao sysUserDao;
	
	private IClientData data;
	
	 
	protected Result doWork(IClientData data) throws Exception {
		this.data=data;
		
		Map<String,Object> map=new HashMap<String,Object>();
		
		String userIds=data.getParameter("userIds");
		if(StringUtils.isBlank(userIds))
			throw new ValidataException("userIds不能为空");
		
		map.put("userIds",getIds(userIds));	
		
		sysUserDao.removeSysUser(map);
		
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
