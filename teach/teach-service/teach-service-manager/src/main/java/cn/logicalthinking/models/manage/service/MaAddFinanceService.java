package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.entity.Finance;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  系统的财务管理 添加
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaAddFinanceService extends AbstractService{
	
	@Resource
	private FinanceDao financeDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Finance finance=(Finance)data.getObject();
		
		financeDao.addFinance(finance);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
