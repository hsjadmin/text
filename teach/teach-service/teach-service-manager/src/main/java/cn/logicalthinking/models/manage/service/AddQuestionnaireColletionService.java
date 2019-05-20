package cn.logicalthinking.models.manage.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
import cn.logicalthinking.common.dao.QuestionnaireColletionDao;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  问卷情况汇总表 添加
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class AddQuestionnaireColletionService extends AbstractService{
	
	@Resource
	private QuestionnaireColletionDao questionnaireColletionDao;
	
	private IClientData data;
	
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		QuestionnaireColletion questionnaireColletion=(QuestionnaireColletion)data.getObject();
		
		questionnaireColletionDao.addQuestionnaireColletion(questionnaireColletion);
		
		return  Result.jsonMsg(CODE.CODE_200_ADD.getKey(),CODE.CODE_200_ADD.getValue());
		
	}
	
	

}
