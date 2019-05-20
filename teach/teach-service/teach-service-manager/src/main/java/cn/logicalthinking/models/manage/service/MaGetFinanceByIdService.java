package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.FinanceDao;
import cn.logicalthinking.common.entity.Finance;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description  系统的财务管理 根据id查询
 * @author xhx
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class MaGetFinanceByIdService extends AbstractService{
	
	@Resource
	private FinanceDao financeDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String id=data.getParameter("id");
		if(StringUtils.isBlank(id))
			throw new ValidataException("id不能为空");
		
		Finance finance = financeDao.selectFinanceById(id);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), finance);
	}

}
