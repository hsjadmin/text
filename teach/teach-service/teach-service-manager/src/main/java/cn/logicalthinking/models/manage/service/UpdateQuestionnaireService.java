package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.QuestionnaireDao;
import cn.logicalthinking.common.entity.Questionnaire;
import cn.logicalthinking.common.exception.ValidataException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description  问卷题目表 修改
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class UpdateQuestionnaireService extends AbstractService{
	
	@Resource
	private QuestionnaireDao questionnaireDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Questionnaire questionnaire=(Questionnaire)data.getObject();
		
		if(questionnaire.getId() == null)
			throw new ValidataException("id不能为空");
		
		questionnaireDao.updateQuestionnaire(questionnaire);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
