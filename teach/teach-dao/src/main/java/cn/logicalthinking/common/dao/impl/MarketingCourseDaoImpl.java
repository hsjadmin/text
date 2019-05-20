package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.entity.MarketingCourse;
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
public class MarketingCourseDaoImpl extends BaseDao implements MarketingCourseDao{

	public int addMarketingCourse(MarketingCourse marketingCourse) throws DaoException{
		return super.getSqlSession().insert("addMarketingCourse", marketingCourse);
	}
	public int batchMarketingCourse(List<MarketingCourse> list) throws DaoException{
		return super.getSqlSession().insert("batchMarketingCourse",list);
	}

	public int updateMarketingCourse(MarketingCourse marketingCourse) throws DaoException{
		return super.getSqlSession().update("updateMarketingCourse", marketingCourse);
	}

	public int removeMarketingCourse(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeMarketingCourse", map);
	}

	public MarketingCourse selectMarketingCourseById(Integer id) throws DaoException{
		return super.getSqlSession().selectOne("selectMarketingCourseById",id);
	}

	public List<MarketingCourse> selectMarketingCourseListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectMarketingCourseListAll",map);
	}
	
	public PageInfo<MarketingCourse> selectMarketingCourseListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<MarketingCourse> selectList = super.getSqlSession().selectList("selectMarketingCourseListByPage", map);
        return new PageInfo<MarketingCourse>(selectList);
	}

	public int selectMarketingCourseCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectMarketingCourseCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}