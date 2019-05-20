package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import  cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  系统参数设置表 根据id查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetApplicationParameterByIdService extends AbstractService{
	
	@Resource
	private ApplicationParameterDao applicationParameterDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String id=data.getParameter("id");
		if(StringUtils.isBlank(id))
			throw new ValidataException("id不能为空");
		
		ApplicationParameter applicationParameter = applicationParameterDao.selectApplicationParameterById(id);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), applicationParameter);
	}

}
