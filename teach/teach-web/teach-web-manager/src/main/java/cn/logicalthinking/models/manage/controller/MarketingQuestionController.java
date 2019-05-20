package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.MarketingQuestion;
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
 * @Description 营销素材-难题控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/MarketingQuestion")
@Api(tags="【后台】-营销素材-难题")
public class MarketingQuestionController extends BaseController {

   	@Resource(name="getMarketingQuestionListService")
    private AbstractService getMarketingQuestionListService;
    
    @Resource(name="getMarketingQuestionByIdService")
    private AbstractService getMarketingQuestionByIdService;
    
   	@Resource(name="addMarketingQuestionService")
    private AbstractService addMarketingQuestionService;
	
	@Resource(name="removeMarketingQuestionService")
    private AbstractService removeMarketingQuestionService;
    
    @Resource(name="updateMarketingQuestionService")
    private AbstractService updateMarketingQuestionService;
    

	/**
	 * @Description  营销素材-难题列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingQuestionList")
	@ApiOperation(value = "营销素材-难题列表查询",response=Result.class, httpMethod = "POST")
	public Result MarketingQuestionList() throws Exception {
		return getMarketingQuestionListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  营销素材-难题根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMarketingQuestionById")
	@ApiOperation(value = "营销素材-难题根据id查询",response=Result.class, httpMethod = "POST")
	public Result MarketingQuestiongetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getMarketingQuestionByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  营销素材-难题添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMarketingQuestion")
	@Transactional
	@ApiOperation(value = "营销素材-难题添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加营销素材-难题",url="/manage/MarketingQuestion/addMarketingQuestion")
	public Result addMarketingQuestion(MarketingQuestion entity) throws Exception {
		return addMarketingQuestionService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  营销素材-难题修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMarketingQuestion")
	@Transactional
	@ApiOperation(value = "营销素材-难题修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改营销素材-难题",url="/manage/MarketingQuestion/updateMarketingQuestion")
	public Result updateMarketingQuestion(MarketingQuestion entity) throws Exception {
		return updateMarketingQuestionService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  营销素材-难题删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeMarketingQuestion")
	@Transactional
	@ApiOperation(value = "营销素材-难题删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除营销素材-难题",url="/manage/MarketingQuestion/removeMarketingQuestion")
	public Result removeMarketingQuestion(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeMarketingQuestionService.execute(new WebClient(request));
	}	
	
}