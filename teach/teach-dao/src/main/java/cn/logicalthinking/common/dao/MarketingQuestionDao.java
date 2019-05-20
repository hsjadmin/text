package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.MarketingQuestion;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface MarketingQuestionDao {

	//新增
	public int addMarketingQuestion(MarketingQuestion marketingQuestion) throws DaoException;
	
	//批量添加
	public int batchMarketingQuestion(List<MarketingQuestion> list) throws DaoException;

	//修改
	public int updateMarketingQuestion(MarketingQuestion marketingQuestion) throws DaoException;

	//删除
	public int removeMarketingQuestion(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public MarketingQuestion selectMarketingQuestionById(String id) throws DaoException;

	//条件查询全部
	public List<MarketingQuestion> selectMarketingQuestionListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<MarketingQuestion> selectMarketingQuestionListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectMarketingQuestionCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}