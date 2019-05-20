package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
import cn.logicalthinking.common.exception.ValidataException;
import cn.logicalthinking.common.util.DateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description  营销素材-学生端首页 修改
 * @author 卢祖飞
 * @date   2018-12-19
 * @version  1.0
 */
@Service
public class UpdateMarketingBannerService extends AbstractService{
	
	@Resource
	private MarketingBannerDao marketingBannerDao;
	
	private IClientData data;
	
	protected Result doWork(IClientData data) throws Exception {
		
		this.data=data;
		
		MarketingBanner marketingBanner=(MarketingBanner)data.getObject();
		
		if(marketingBanner.getId() == null)
			throw new ValidataException("id不能为空");

		marketingBanner.setUpdateTime(DateUtil.getDateStr(new Date(),DateUtil.DATE_TIME));
		
		marketingBannerDao.updateMarketingBanner(marketingBanner);
		
		return  Result.jsonMsg(CODE.CODE_200_UPDATE.getKey(),CODE.CODE_200_UPDATE.getValue());
		
	}

}
