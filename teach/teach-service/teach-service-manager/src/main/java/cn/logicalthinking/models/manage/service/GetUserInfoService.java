package cn.logicalthinking.models.manage.service;



import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.entity.SysUser;
/**
 * @Description  获取当前用户信息
 * @author 黄世杰
 * @date   2018-09-28
 * @version  1.0
 */
@Service
public class GetUserInfoService extends AbstractService{
	
	private IClientData data;
	
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		SysUser user = data.getUser();
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), user);
	}

}
