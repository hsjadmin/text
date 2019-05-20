package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.MarketingCourse;
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
 * @Description 营销素材-课程控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/MarketingCourse")
@Api(tags="【后台】-营销素材-课程")
public class MarketingCourseController extends BaseController {

   	@Resource(name="getMarketingCourseListService")
    private AbstractService getMarketingCourseListService;
    
    @Resource(name="getMarketingCourseByIdService")
    private AbstractService getMarketingCourseByIdService;
    
   	@Resource(name="addMarketingCourseService")
    private AbstractService addMarketingCourseService;
	
	@Resource(name="removeMarketingCourseService")
    private AbstractService removeMarketingCourseService;
    
    @Resource(name="updateMarketingCourseService")
    private AbstractService updateMarketingCourseService;

    @Resource(name = "getLGSService")
	private AbstractService getLGSService;


	/**
	*@note 查询年级科目
	*@auhtor 卢祖飞
	*@date 2019/1/5,11:16
	*@version 1.0
	*/
	@RequestMapping(value = "/getLGS")
	@ApiOperation(value = "查询年级科目",response=Result.class, httpMethod = "POST")
    public Result getLGS() throws Exception{
    	return getLGSService.execute(new WebClient(request));
	}


	/**
	 * @Description  营销素材-课程列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingCourseList")
	@ApiOperation(value = "营销素材-课程列表查询",response=Result.class, httpMethod = "POST")
	public Result MarketingCourseList() throws Exception {
		return getMarketingCourseListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  营销素材-课程根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMarketingCourseById")
	@ApiOperation(value = "营销素材-课程根据id查询",response=Result.class, httpMethod = "POST")
	public Result MarketingCoursegetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getMarketingCourseByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  营销素材-课程添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMarketingCourse")
	@Transactional
	@ApiOperation(value = "营销素材-课程添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加营销素材-课程",url="/manage/MarketingCourse/addMarketingCourse")
	public Result addMarketingCourse(MarketingCourse entity) throws Exception {
		return addMarketingCourseService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  营销素材-课程修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMarketingCourse")
	@Transactional
	@ApiOperation(value = "营销素材-课程修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改营销素材-课程",url="/manage/MarketingCourse/updateMarketingCourse")
	public Result updateMarketingCourse(MarketingCourse entity) throws Exception {
		return updateMarketingCourseService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  营销素材-课程删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMarketingCourse")
	@Transactional
	@ApiOperation(value = "营销素材-课程删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除营销素材-课程",url="/manage/MarketingCourse/removeMarketingCourse")
	public Result removeMarketingCourse(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeMarketingCourseService.execute(new WebClient(request));
	}	
	
}