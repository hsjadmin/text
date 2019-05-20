package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】-首页
 * @date 2018-12-26
 */
@RestController
@RequestMapping("/student/home")
@Api(tags = "【学生】-首页")
public class StHomeController extends BaseController {

    @Resource(name = "stGetHomeBannerListService")
    private AbstractService stGetMarketingBannerListService;

    @Resource(name = "stGetHomeVideoListService")
    private AbstractService stGetHomeVideoListService;

    @Resource(name = "stReadHomeMarketingService")
    private AbstractService stReadHomeMarketingService;

    @Resource(name = "stUpdateStudentAddressService")
    private AbstractService stUpdateStudentAddressService;

    @Resource(name = "stGetUnPaidOrderListService")
    private AbstractService stGetUnPaidOrderListService;

    /**
     * @throws Exception
     * @Description 查询banner
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingBannerList")
    @ApiOperation(value = "查询banner", response = Result.class, httpMethod = "POST")
    public Result MarketingBannerList() throws Exception {
        return stGetMarketingBannerListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询视频
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingVideoList")
    @ApiOperation(value = "查询视频", response = Result.class, httpMethod = "POST")
    public Result stGetHomeVideoList() throws Exception {
        return stGetHomeVideoListService.execute(new WebClient(request));
    }
    /**
     * @throws Exception
     * @Description 查询未支付订单
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @RequestMapping(value = "/getUnPaidOrderListService")
    @ApiOperation(value = "查询未支付订单", response = Result.class, httpMethod = "POST")
    public Result stGetUnPaidOrderListService() throws Exception {
        return stGetUnPaidOrderListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 主页广告计数
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "广告bannerId", name = "id", paramType = "query", defaultValue = "1"),
    })
    @RequestMapping(value = "/readHomeMarketing")
    @ApiOperation(value = "主页广告计数", response = Result.class, httpMethod = "POST")
    public Result readCourseBanner() throws Exception {
        return stReadHomeMarketingService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 修改学生地址信息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updateStudentAddress")
    @Transactional
    @ApiOperation(value = "修改学生地址信息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改学生地址信息", url = "student/home/updateStudentAddress")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "区域", name = "region", paramType = "query"),
            @ApiImplicitParam(value = "地址", name = "address", paramType = "query"),
    })
    public Result stUpdateStudentAddressService() throws Exception {
        return stUpdateStudentAddressService.execute(new WebClient(request));
    }


}