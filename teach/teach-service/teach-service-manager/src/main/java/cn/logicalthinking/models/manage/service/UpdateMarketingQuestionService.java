package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingQuestionDao;
import cn.logicalthinking.common.entity.MarketingQuestion;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description  营销素材-难题 修改
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class UpdateMarketingQuestionService extends AbstractService{
	
	@Resource
	private MarketingQuestionDao marketingQuestionDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		MarketingQuestion marketingQuestion=(MarketingQuestion)data.getObject();
		
		if(marketingQuestion.getId() == null)
			throw new ValidataException("id不能为空");

		marketingQuestion.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		
		marketingQuestionDao.updateMarketingQuestion(marketingQuestion);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
