package cn.logicalthinking.common.dao;

import cn.logicalthinking.common.entity.MarketingBanner;
import cn.logicalthinking.common.exception.DaoException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */

public interface MarketingBannerDao {

	//新增
	public int addMarketingBanner(MarketingBanner marketingBanner) throws DaoException;
	
	//批量添加
	public int batchMarketingBanner(List<MarketingBanner> list) throws DaoException;

	//修改
	public int updateMarketingBanner(MarketingBanner marketingBanner) throws DaoException;

	//删除
	public int removeMarketingBanner(Map<String, Object> map) throws DaoException;
	
	//查询单个
	public MarketingBanner selectMarketingBannerById(Integer id) throws DaoException;

	//条件查询全部
	public List<MarketingBanner> selectMarketingBannerListAll(Map<String, Object> map) throws DaoException;
	
	//分页查询
	public PageInfo<MarketingBanner> selectMarketingBannerListByPage(Map<String, Object> map) throws DaoException;

	//查询总数
	public int selectMarketingBannerCount(Map<String, Object> map) throws DaoException;
	
	
	/*********************************************自定义扩展sql***********************************************/
}