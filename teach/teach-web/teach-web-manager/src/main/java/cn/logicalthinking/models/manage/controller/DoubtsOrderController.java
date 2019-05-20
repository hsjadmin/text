package cn.logicalthinking.models.manage.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 解疑订单
 * 
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/doubts")
@Api(tags="【后台】-订单管理-解疑订单")
public class DoubtsOrderController extends BaseController {

   	@Resource(name="getDoubtsOrderService")
    private AbstractService getDoubtsOrderService;

	@Resource(name="removeOrderQuestionService")
	private AbstractService removeOrderQuestionService;

	@Resource(name = "exceptDoubtsService")
	private AbstractService exceptDoubtsService;

	/**
	 * @Description  解疑订单列表查询
	 * @author  黄世杰
	 * */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10"),
        @ApiImplicitParam(value = "学生姓名", name = "studentName", paramType = "query"),
        @ApiImplicitParam(value = "老师姓名", name = "teacherName", paramType = "query"),
        @ApiImplicitParam(value = "审核状态", name = "status", paramType = "query"),
        @ApiImplicitParam(value = "价格", name = "price", paramType = "query")
    })
    @RequestMapping(value = "/getDoubtsOrder")
	@ApiOperation(value = "解疑订单列表查询",response=Result.class, httpMethod = "POST")
	public Result getDoubtsOrder() throws Exception {
		return getDoubtsOrderService.execute(new WebClient(request));
	}

	/**
	 * @Description  疑难解答订单删除
	 * @author  黄世杰
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeOrderQuestion")
	@Transactional
	@ApiOperation(value = "疑难解答订单删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除疑难解答订单",url="/manage/doubts/removeOrderQuestion")
	public Result removeOrderQuestion(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeOrderQuestionService.execute(new WebClient(request));
	}
	/**
	*@note 导出
	*@auhtor 卢祖飞
	*@date 2019/1/4,14:55
	*@version 1.0
	*/
	@RequestMapping(value = "/exceptDoubts")
	@Transactional
	@ApiOperation(value = "导出",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="导出",operationName="导出",url="/manage/doubts/exceptDoubts")
	public Result exceptDoubts(HttpServletResponse response) throws Exception{
		return exceptDoubtsService.execute(new WebClient(request,response));
	}

}