package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.QuestionCommentDao;
import cn.logicalthinking.common.entity.QuestionComment;
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
public class QuestionCommentDaoImpl extends BaseDao implements QuestionCommentDao{

	public int addQuestionComment(QuestionComment questionComment) throws DaoException{
		return super.getSqlSession().insert("addQuestionComment", questionComment);
	}
	public int batchQuestionComment(List<QuestionComment> list) throws DaoException{
		return super.getSqlSession().insert("batchQuestionComment",list);
	}

	public int updateQuestionComment(QuestionComment questionComment) throws DaoException{
		return super.getSqlSession().update("updateQuestionComment", questionComment);
	}

	public int removeQuestionComment(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeQuestionComment", map);
	}

	public QuestionComment selectQuestionCommentById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectQuestionCommentById",id);
	}

	public List<QuestionComment> selectQuestionCommentListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectQuestionCommentListAll",map);
	}
	
	public PageInfo<QuestionComment> selectQuestionCommentListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<QuestionComment> selectList = super.getSqlSession().selectList("selectQuestionCommentListByPage", map);
        return new PageInfo<QuestionComment>(selectList);
	}

	public int selectQuestionCommentCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectQuestionCommentCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	@Override
	public int removeQuestionCommentBytId(String teacherId) throws DaoException {
		return super.getSqlSession().delete("removeQuestionCommentBytId",teacherId);
	}
}