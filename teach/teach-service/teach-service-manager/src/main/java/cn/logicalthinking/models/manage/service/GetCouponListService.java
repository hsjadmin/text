package cn.logicalthinking.models.manage.service;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.dao.CouponGroupDao;
import cn.logicalthinking.common.entity.CouponGroup;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 卢祖飞
 * @version 1.0
 * @Description 优惠券 列表查询
 * @date 2018-12-19
 */
@Service
public class GetCouponListService extends AbstractService {

    @Resource
    private CouponGroupDao couponGroupDao;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        Map<String, Object> map = data.initMap();

        String[] conditionArr = new String[]{
                "couponName",//优惠券名称
                "couponType",//优惠券类型
                "ctStart",//优惠券类型
                "ctEnd",//优惠券类型
        };

        data.setConditionMap(map, conditionArr);

        PageInfo<CouponGroup> pageInfo = couponGroupDao.selectCouponGroupListByPage(map);

        return Result.jsonData(CODE.CODE_200_SELECT.getKey(), pageInfo.getList(), pageInfo.getTotal());
    }

}
