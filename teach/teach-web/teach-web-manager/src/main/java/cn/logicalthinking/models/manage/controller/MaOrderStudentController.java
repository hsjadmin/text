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

/**
 * @Description 学生订单控制器
 * 
 * @author xhx
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/order/student")
@Api(tags="【后台】-订单管理-学生订单")
public class MaOrderStudentController extends BaseController {

   	@Resource(name= "maGetOrderStudentListService")
    private AbstractService maGetOrderStudentListService;
    
	@Resource(name="maRemoveOrderStudentService")
    private AbstractService maRemoveOrderStudentService;

	/**
	 * @Description  学生订单列表查询
	 * @author  xhx
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getOrderStudentList")
	@ApiOperation(value = "学生订单列表查询",response=Result.class, httpMethod = "POST")
	public Result OrderStudentList() throws Exception {
		return maGetOrderStudentListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  学生订单删除
	 * @author  xhx
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeOrderStudent")
	@Transactional
	@ApiOperation(value = "学生订单删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除学生订单",url="manage/removeOrderStudent")
	public Result removeOrderStudent(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return maRemoveOrderStudentService.execute(new WebClient(request));
	}	
	
}