package cn.logicalthinking.models.manage.service;

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.entity.MarketingBanner;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import com.github.pagehelper.PageInfo;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.enums.CODE;
/**
 * @Description  营销素材-学生端首页 列表查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetMarketingBannerListService extends AbstractService{
	
	@Resource
	private MarketingBannerDao marketingBannerDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		Map<String,Object> map=data.initMap();
		
		String[] conditionArr = new String[]{
				"createTime" ,//创建时间
				"name" ,//课程名
				"url" ,//资料链接
				"status" ,//状态,0上架  1下架
				"userName",//发布人
				"materialType",
		};
		
		data.setConditionMap(map,conditionArr);
		
		PageInfo<MarketingBanner> pageInfo = marketingBannerDao.selectMarketingBannerListByPage(map);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(),pageInfo.getList(),pageInfo.getTotal());
	}

}
