package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.MarketingCourse;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface MarketingCourseDao {

	//新增
	public int addMarketingCourse(MarketingCourse marketingCourse) throws DaoException;
	
	//批量添加
	public int batchMarketingCourse(List<MarketingCourse> list) throws DaoException;

	//修改
	public int updateMarketingCourse(MarketingCourse marketingCourse) throws DaoException;

	//删除
	public int removeMarketingCourse(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public MarketingCourse selectMarketingCourseById(Integer id) throws DaoException;

	//条件查询全部
	public List<MarketingCourse> selectMarketingCourseListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<MarketingCourse> selectMarketingCourseListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectMarketingCourseCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}