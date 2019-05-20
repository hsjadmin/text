package cn.logicalthinking.models.manage.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
import cn.logicalthinking.common.dao.QuestionnaireColletionDao;
import com.github.pagehelper.PageInfo;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  问卷情况汇总表 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetQuestionnaireColletionListService extends AbstractService{
	
	@Resource
	private QuestionnaireColletionDao questionnaireColletionDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"name" ,//用户名
				"account" ,//账号
				"satisfied" ,//满意度
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<QuestionnaireColletion> pageInfo = questionnaireColletionDao.selectQuestionnaireColletionListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
