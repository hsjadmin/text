package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Questionnaire;
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
 * @Description 问卷题目表控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/Questionnaire")
@Api(tags="【后台】-问卷管理-问卷题目")
public class QuestionnaireController extends BaseController {

   	@Resource(name="getQuestionnaireListService")
    private AbstractService getQuestionnaireListService;
    
    @Resource(name="getQuestionnaireByIdService")
    private AbstractService getQuestionnaireByIdService;
    
   	@Resource(name="addQuestionnaireService")
    private AbstractService addQuestionnaireService;
	
	@Resource(name="removeQuestionnaireService")
    private AbstractService removeQuestionnaireService;
    
    @Resource(name="updateQuestionnaireService")
    private AbstractService updateQuestionnaireService;
    

	/**
	 * @Description  问卷题目表列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getQuestionnaireList")
	@ApiOperation(value = "问卷题目表列表查询",response=Result.class, httpMethod = "POST")
	public Result QuestionnaireList() throws Exception {
		return getQuestionnaireListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  问卷题目表根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getQuestionnaireById")
	@ApiOperation(value = "问卷题目表根据id查询",response=Result.class, httpMethod = "POST")
	public Result QuestionnairegetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getQuestionnaireByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * @Description  问卷题目表添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addQuestionnaire")
	@Transactional
	@ApiOperation(value = "问卷题目表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加问卷题目表",url="/manage/Questionnaire/addQuestionnaire")
	public Result addQuestionnaire(Questionnaire entity) throws Exception {
		return addQuestionnaireService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  问卷题目表修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateQuestionnaire")
	@Transactional
	@ApiOperation(value = "问卷题目表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改问卷题目表",url="/manage/Questionnaire/updateQuestionnaire")
	public Result updateQuestionnaire(Questionnaire entity) throws Exception {
		return updateQuestionnaireService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  问卷题目表删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeQuestionnaire")
	@Transactional
	@ApiOperation(value = "问卷题目表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除问卷题目表",url="/manage/Questionnaire/removeQuestionnaire")
	public Result removeQuestionnaire(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeQuestionnaireService.execute(new WebClient(request));
	}	
	
}