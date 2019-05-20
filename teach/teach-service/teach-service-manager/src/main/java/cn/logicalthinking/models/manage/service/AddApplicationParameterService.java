package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description  系统参数设置表 添加
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class AddApplicationParameterService extends AbstractService{
	
	@Resource
	private ApplicationParameterDao applicationParameterDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		ApplicationParameter applicationParameter=(ApplicationParameter)data.getObject();

		applicationParameter.setCreateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));

		applicationParameterDao.addApplicationParameter(applicationParameter);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}

	

}
