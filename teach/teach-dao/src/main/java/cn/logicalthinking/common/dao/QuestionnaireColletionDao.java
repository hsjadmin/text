package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.QuestionnaireColletion;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface QuestionnaireColletionDao {

	//新增
	public int addQuestionnaireColletion(QuestionnaireColletion questionnaireColletion) throws DaoException;
	
	//批量添加
	public int batchQuestionnaireColletion(List<QuestionnaireColletion> list) throws DaoException;

	//修改
	public int updateQuestionnaireColletion(QuestionnaireColletion questionnaireColletion) throws DaoException;

	//删除
	public int removeQuestionnaireColletion(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public QuestionnaireColletion selectQuestionnaireColletionById(String id) throws DaoException;

	//条件查询全部
	public List<QuestionnaireColletion> selectQuestionnaireColletionListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<QuestionnaireColletion> selectQuestionnaireColletionListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectQuestionnaireColletionCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}