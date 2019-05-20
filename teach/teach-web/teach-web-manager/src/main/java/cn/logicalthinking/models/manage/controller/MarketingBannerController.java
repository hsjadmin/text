package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.MarketingBanner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 营销素材-学生端首页控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/MarketingBanner")
@Api(tags="【后台】-营销素材-学生端首页")
public class MarketingBannerController extends BaseController {

   	@Resource(name="getMarketingBannerListService")
    private AbstractService getMarketingBannerListService;
    
    @Resource(name="getMarketingBannerByIdService")
    private AbstractService getMarketingBannerByIdService;
    
   	@Resource(name="addMarketingBannerService")
    private AbstractService addMarketingBannerService;
	
	@Resource(name="removeMarketingBannerService")
    private AbstractService removeMarketingBannerService;
    
    @Resource(name="updateMarketingBannerService")
    private AbstractService updateMarketingBannerService;
    

	/**
	 * @Description  营销素材-学生端首页列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingBannerList")
	@ApiOperation(value = "营销素材-学生端首页列表查询",response=Result.class, httpMethod = "POST")
	public Result MarketingBannerList() throws Exception {
		return getMarketingBannerListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  营销素材-学生端首页根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMarketingBannerById")
	@ApiOperation(value = "营销素材-学生端首页根据id查询",response=Result.class, httpMethod = "POST")
	public Result MarketingBannergetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getMarketingBannerByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  营销素材-学生端首页添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMarketingBanner")
	@Transactional
	@ApiImplicitParam(value = "文件上传",name = "fileInfo",paramType = "query")
	@ApiOperation(value = "营销素材-学生端首页添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加营销素材-学生端首页",url="/manage/MarketingBanner/addMarketingBanner")
	public Result addMarketingBanner(MarketingBanner entity) throws Exception {
		return addMarketingBannerService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  营销素材-学生端首页修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMarketingBanner")
	@Transactional
	@ApiOperation(value = "营销素材-学生端首页修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改营销素材-学生端首页",url="/manage/MarketingBanner/updateMarketingBanner")
	public Result updateMarketingBanner(MarketingBanner entity) throws Exception {
		return updateMarketingBannerService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  营销素材-学生端首页删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMarketingBanner")
	@Transactional
	@ApiOperation(value = "营销素材-学生端首页删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除营销素材-学生端首页",url="/manage/MarketingBanner/removeMarketingBanner")
	public Result removeMarketingBanner(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeMarketingBannerService.execute(new WebClient(request));
	}	
	
}