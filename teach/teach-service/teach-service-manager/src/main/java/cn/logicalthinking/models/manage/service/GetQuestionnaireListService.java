package cn.logicalthinking.models.manage.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.Questionnaire;
import cn.logicalthinking.common.dao.QuestionnaireDao;
import com.github.pagehelper.PageInfo;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  问卷题目表 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetQuestionnaireListService extends AbstractService{
	
	@Resource
	private QuestionnaireDao questionnaireDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"id" ,//主键
				"createTime" ,//创建时间
				"updateTime" ,//修改时间
				"question" ,//问题标题，固定八个题目
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<Questionnaire> pageInfo = questionnaireDao.selectQuestionnaireListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
