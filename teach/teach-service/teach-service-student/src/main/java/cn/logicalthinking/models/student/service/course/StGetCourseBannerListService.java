package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.entity.MarketingCourse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author xhx
 * @version 1.0
 * @Description 营销素材-课程 列表查询
 * @date 2018-12-19
 */
@Service
public class StGetCourseBannerListService extends AbstractService {

    @Resource
    private MarketingCourseDao marketingCourseDao;

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
                "type",//课程类型，直接存语文数学等
                "url",//资料链接
                "content",//内容
                "status",//状态（0上架  1下架）
                "number",//观看人数
                "coverMap",//封面图
                "isHref",//是否是跳链接（0否  1是）
        };

        map.put("status", 0);
        data.setConditionMap(map, conditionArr);

        PageInfo<MarketingCourse> pageInfo = marketingCourseDao.selectMarketingCourseListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
