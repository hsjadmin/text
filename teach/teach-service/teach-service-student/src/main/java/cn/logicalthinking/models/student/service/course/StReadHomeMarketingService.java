package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 主页广告计数
 * @date 2018-12-19
 */
@Service
public class StReadHomeMarketingService extends AbstractService {

    @Resource
    private MarketingBannerDao marketingBannerDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        int i = Integer.parseInt(id);

        MarketingBanner marketingBanner = new MarketingBanner();
        marketingBanner.setId(i);

        MarketingBanner selectMarketingBannerById = marketingBannerDao.selectMarketingBannerById(i);
        Integer number = selectMarketingBannerById.getNumber();

        marketingBanner.setNumber(number + 1);

        if (marketingBannerDao.updateMarketingBanner(marketingBanner) == 0) {
            throw new BusinessException("计数失败");
        }

        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(), "计数成功");
    }

}
