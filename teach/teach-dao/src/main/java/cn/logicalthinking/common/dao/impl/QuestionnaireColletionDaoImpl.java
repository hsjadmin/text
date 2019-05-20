package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.QuestionnaireColletionDao;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@Component
public class QuestionnaireColletionDaoImpl extends BaseDao implements QuestionnaireColletionDao{

	public int addQuestionnaireColletion(QuestionnaireColletion questionnaireColletion) throws DaoException{
		return super.getSqlSession().insert("addQuestionnaireColletion", questionnaireColletion);
	}
	public int batchQuestionnaireColletion(List<QuestionnaireColletion> list) throws DaoException{
		return super.getSqlSession().insert("batchQuestionnaireColletion",list);
	}

	public int updateQuestionnaireColletion(QuestionnaireColletion questionnaireColletion) throws DaoException{
		return super.getSqlSession().update("updateQuestionnaireColletion", questionnaireColletion);
	}

	public int removeQuestionnaireColletion(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeQuestionnaireColletion", map);
	}

	public QuestionnaireColletion selectQuestionnaireColletionById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectQuestionnaireColletionById",id);
	}

	public List<QuestionnaireColletion> selectQuestionnaireColletionListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectQuestionnaireColletionListAll",map);
	}
	
	public PageInfo<QuestionnaireColletion> selectQuestionnaireColletionListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<QuestionnaireColletion> selectList = super.getSqlSession().selectList("selectQuestionnaireColletionListByPage", map);
        return new PageInfo<QuestionnaireColletion>(selectList);
	}

	public int selectQuestionnaireColletionCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectQuestionnaireColletionCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}