package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
import cn.logicalthinking.common.exception.ValidataException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Description  营销素材-学生端首页 根据id查询
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class GetMarketingBannerByIdService extends AbstractService{
	
	@Resource
	private MarketingBannerDao marketingBannerDao;
	
	private IClientData data;
	  
	@Override
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		String id=data.getParameter("id");
		if(StringUtils.isBlank(id))
			throw new ValidataException("id不能为空");

		int i = Integer.parseInt(id);
		MarketingBanner marketingBanner = marketingBannerDao.selectMarketingBannerById(i);
		
		return  Result.jsonData(CODE.CODE_200_SELECT.getKey(), marketingBanner);
	}

}
