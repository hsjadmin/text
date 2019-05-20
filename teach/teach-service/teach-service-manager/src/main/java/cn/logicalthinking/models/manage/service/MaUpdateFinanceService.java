package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.entity.Finance;
import cn.logicalthinking.common.exception.ValidataException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  系统的财务管理 修改
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaUpdateFinanceService extends AbstractService{
	
	@Resource
	private FinanceDao financeDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Finance finance=(Finance)data.getObject();
		
		if(finance.getId() == null)
			throw new ValidataException("id不能为空");
		
		financeDao.updateFinance(finance);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
