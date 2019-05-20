package cn.logicalthinking.models.manage.controller;
import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
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
 * @Description 问卷情况汇总表控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/QuestionnaireColletion")
@Api(tags="【后台】-问卷管理-问卷情况汇总")
public class QuestionnaireColletionController extends BaseController {

   	@Resource(name="getQuestionnaireColletionListService")
    private AbstractService getQuestionnaireColletionListService;
    
    @Resource(name="getQuestionnaireColletionByIdService")
    private AbstractService getQuestionnaireColletionByIdService;
    
   	@Resource(name="addQuestionnaireColletionService")
    private AbstractService addQuestionnaireColletionService;
	
	@Resource(name="removeQuestionnaireColletionService")
    private AbstractService removeQuestionnaireColletionService;
    
    @Resource(name="updateQuestionnaireColletionService")
    private AbstractService updateQuestionnaireColletionService;
    

	/**
	 * @Description  问卷情况汇总表列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getQuestionnaireColletionList")
	@ApiOperation(value = "问卷情况汇总表列表查询",response=Result.class, httpMethod = "POST")
	public Result QuestionnaireColletionList() throws Exception {
		return getQuestionnaireColletionListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  问卷情况汇总表根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getQuestionnaireColletionById")
	@ApiOperation(value = "问卷情况汇总表根据id查询",response=Result.class, httpMethod = "POST")
	public Result QuestionnaireColletiongetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getQuestionnaireColletionByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  问卷情况汇总表添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addQuestionnaireColletion")
	@Transactional
	@ApiOperation(value = "问卷情况汇总表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加问卷情况汇总表",url="/manage/QuestionnaireColletion/addQuestionnaireColletion")
	public Result addQuestionnaireColletion(QuestionnaireColletion entity) throws Exception {
		return addQuestionnaireColletionService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  问卷情况汇总表修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateQuestionnaireColletion")
	@Transactional
	@ApiOperation(value = "问卷情况汇总表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改问卷情况汇总表",url="/manage/QuestionnaireColletion/updateQuestionnaireColletion")
	public Result updateQuestionnaireColletion(QuestionnaireColletion entity) throws Exception {
		return updateQuestionnaireColletionService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  问卷情况汇总表删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeQuestionnaireColletion")
	@Transactional
	@ApiOperation(value = "问卷情况汇总表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除问卷情况汇总表",url="/manage/QuestionnaireColletion/removeQuestionnaireColletion")
	public Result removeQuestionnaireColletion(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeQuestionnaireColletionService.execute(new WebClient(request));
	}	
	
}