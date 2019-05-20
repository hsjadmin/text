package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.QuestionComment;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface QuestionCommentDao {

	//新增
	public int addQuestionComment(QuestionComment questionComment) throws DaoException;
	
	//批量添加
	public int batchQuestionComment(List<QuestionComment> list) throws DaoException;

	//修改
	public int updateQuestionComment(QuestionComment questionComment) throws DaoException;

	//删除
	public int removeQuestionComment(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public QuestionComment selectQuestionCommentById(String id) throws DaoException;

	//条件查询全部
	public List<QuestionComment> selectQuestionCommentListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<QuestionComment> selectQuestionCommentListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectQuestionCommentCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/

	int removeQuestionCommentBytId(String teacherId) throws DaoException;
}