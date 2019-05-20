package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.MarketingQuestionDao;
import cn.logicalthinking.common.entity.MarketingQuestion;
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
public class MarketingQuestionDaoImpl extends BaseDao implements MarketingQuestionDao{

	public int addMarketingQuestion(MarketingQuestion marketingQuestion) throws DaoException{
		return super.getSqlSession().insert("addMarketingQuestion", marketingQuestion);
	}
	public int batchMarketingQuestion(List<MarketingQuestion> list) throws DaoException{
		return super.getSqlSession().insert("batchMarketingQuestion",list);
	}

	public int updateMarketingQuestion(MarketingQuestion marketingQuestion) throws DaoException{
		return super.getSqlSession().update("updateMarketingQuestion", marketingQuestion);
	}

	public int removeMarketingQuestion(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeMarketingQuestion", map);
	}

	public MarketingQuestion selectMarketingQuestionById(String id) throws DaoException{
		return super.getSqlSession().selectOne("selectMarketingQuestionById",id);
	}

	public List<MarketingQuestion> selectMarketingQuestionListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectMarketingQuestionListAll",map);
	}
	
	public PageInfo<MarketingQuestion> selectMarketingQuestionListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<MarketingQuestion> selectList = super.getSqlSession().selectList("selectMarketingQuestionListByPage", map);
        return new PageInfo<MarketingQuestion>(selectList);
	}

	public int selectMarketingQuestionCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectMarketingQuestionCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}