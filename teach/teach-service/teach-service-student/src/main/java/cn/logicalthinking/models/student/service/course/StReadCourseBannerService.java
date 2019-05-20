package cn.logicalthinking.models.student.service.course;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.MarketingCourseDao;
import cn.logicalthinking.common.entity.MarketingCourse;
import cn.logicalthinking.common.exception.BusinessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 营销素材-课程 列表查询
 * @date 2018-12-19
 */
@Service
public class StReadCourseBannerService extends AbstractService {

    @Resource
    private MarketingCourseDao marketingCourseDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String id = data.getParameter("id");
        int i = Integer.parseInt(id);

        MarketingCourse marketingCourse = new MarketingCourse();
        marketingCourse.setId(i);

        MarketingCourse selectMarketingCourseById = marketingCourseDao.selectMarketingCourseById(i);
        Integer number = selectMarketingCourseById.getNumber();

        marketingCourse.setNumber(number + 1);

        if (marketingCourseDao.updateMarketingCourse(marketingCourse) == 0) {
            throw new BusinessException("计数失败");
        }

        return Result.jsonMsg(CODE.CODE_200_SELECT.getKey(), "计数成功");
    }

}
