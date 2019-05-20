package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.QuestionnaireDao;
import cn.logicalthinking.common.entity.Questionnaire;
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
public class QuestionnaireDaoImpl extends BaseDao implements QuestionnaireDao{

	public int addQuestionnaire(Questionnaire questionnaire) throws DaoException{
		return super.getSqlSession().insert("addQuestionnaire", questionnaire);
	}
	public int batchQuestionnaire(List<Questionnaire> list) throws DaoException{
		return super.getSqlSession().insert("batchQuestionnaire",list);
	}

	public int updateQuestionnaire(Questionnaire questionnaire) throws DaoException{
		return super.getSqlSession().update("updateQuestionnaire", questionnaire);
	}

	public int removeQuestionnaire(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeQuestionnaire", map);
	}

	public Questionnaire selectQuestionnaireById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectQuestionnaireById",id);
	}

	public List<Questionnaire> selectQuestionnaireListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectQuestionnaireListAll",map);
	}
	
	public PageInfo<Questionnaire> selectQuestionnaireListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<Questionnaire> selectList = super.getSqlSession().selectList("selectQuestionnaireListByPage", map);
        return new PageInfo<Questionnaire>(selectList);
	}

	public int selectQuestionnaireCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectQuestionnaireCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/

	public int removeAllQuestionnaire() throws DaoException{
		return super.getSqlSession().delete("removeAllQuestionnaire");
	}
}