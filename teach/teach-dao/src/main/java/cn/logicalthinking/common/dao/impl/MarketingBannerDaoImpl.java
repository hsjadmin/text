package cn.logicalthinking.common.dao.impl;

import cn.logicalthinking.common.dao.BaseDao;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
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
public class MarketingBannerDaoImpl extends BaseDao implements MarketingBannerDao{

	public int addMarketingBanner(MarketingBanner marketingBanner) throws DaoException{
		return super.getSqlSession().insert("addMarketingBanner", marketingBanner);
	}
	public int batchMarketingBanner(List<MarketingBanner> list) throws DaoException{
		return super.getSqlSession().insert("batchMarketingBanner",list);
	}

	public int updateMarketingBanner(MarketingBanner marketingBanner) throws DaoException{
		return super.getSqlSession().update("updateMarketingBanner", marketingBanner);
	}

	public int removeMarketingBanner(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().delete("removeMarketingBanner", map);
	}

	public MarketingBanner selectMarketingBannerById(Integer id) throws DaoException{
		return super.getSqlSession().selectOne("selectMarketingBannerById",id);
	}

	public List<MarketingBanner> selectMarketingBannerListAll(Map<String, Object> map) throws DaoException{
		return super.getSqlSession().selectList("selectMarketingBannerListAll",map);
	}
	
	public PageInfo<MarketingBanner> selectMarketingBannerListByPage(Map<String, Object> map) throws DaoException{
		if(!map.containsKey("pageNum"))
			map.put("pageNum",0);
		if(!map.containsKey("pageSize"))
			map.put("pageSize",0);
    	PageHelper.startPage(map);
        List<MarketingBanner> selectList = super.getSqlSession().selectList("selectMarketingBannerListByPage", map);
        return new PageInfo<MarketingBanner>(selectList);
	}

	public int selectMarketingBannerCount(Map<String, Object> map) throws DaoException{
		Object obj = super.getSqlSession().selectOne("selectMarketingBannerCount",map);
		if(obj != null){
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	/*********************************************自定义扩展sql***********************************************/
	

}