package cn.logicalthinking.models.student.service.home;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingBannerDao;
import cn.logicalthinking.common.entity.MarketingBanner;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 学生端首页 列表查询 banner
 * @date 2018-12-19
 */
@Service
public class StGetHomeBannerListService extends AbstractService {

    @Resource
    private MarketingBannerDao marketingBannerDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "id",//主键
                "createTime",//创建时间
                "updateTime",//修改时间
                "name",//课程名
                "url",//资料链接
                "content",//内容
                "status",//状态,0上架  1下架
                "number",//观看人数
                "coverMap",//封面图
                "isHref",//是否是跳链接（0否  1是）
                "materialType",//素材类型(0:轮播 1:视频)
                "datum",//资料地址
        };

        map.put("materialType", "0");
        map.put("status", "0");
        data.setConditionMap(map, conditionArr);

        PageInfo<MarketingBanner> pageInfo = marketingBannerDao.selectMarketingBannerListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
