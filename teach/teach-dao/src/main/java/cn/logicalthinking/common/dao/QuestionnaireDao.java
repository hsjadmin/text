package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.Questionnaire;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface QuestionnaireDao {

	//新增
	public int addQuestionnaire(Questionnaire questionnaire) throws DaoException;
	
	//批量添加
	public int batchQuestionnaire(List<Questionnaire> list) throws DaoException;

	//修改
	public int updateQuestionnaire(Questionnaire questionnaire) throws DaoException;

	//删除
	public int removeQuestionnaire(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public Questionnaire selectQuestionnaireById(String id) throws DaoException;

	//条件查询全部
	public List<Questionnaire> selectQuestionnaireListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<Questionnaire> selectQuestionnaireListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectQuestionnaireCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
	//删除
	public int removeAllQuestionnaire() throws DaoException;
}