package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Finance;
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
 * @Description 【后台】财务管理
 * 
 * @author 肖宏鑫
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/finance")
@Api(tags="【后台】财务管理")
public class MaFinanceController extends BaseController {

   	@Resource(name="maGetFinanceListService")
    private AbstractService maGetFinanceListService;
    
    @Resource(name="maGetFinanceByIdService")
    private AbstractService maGetFinanceByIdService;
    
   	@Resource(name="maAddFinanceService")
    private AbstractService maAddFinanceService;
	
	@Resource(name="maRemoveFinanceService")
    private AbstractService maRemoveFinanceService;
    
    @Resource(name="maUpdateFinanceService")
    private AbstractService maUpdateFinanceService;
    

	/**
	 * @Description  系统的财务管理列表查询
	 * @author  肖宏鑫
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getFinanceList")
	@ApiOperation(value = "系统的财务管理列表查询",response=Result.class, httpMethod = "POST")
	public Result FinanceList() throws Exception {
		return maGetFinanceListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  系统的财务管理根据id查询
	 * @author  肖宏鑫
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFinanceById")
	@ApiOperation(value = "系统的财务管理根据id查询",response=Result.class, httpMethod = "POST")
	public Result FinancegetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return maGetFinanceByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  系统的财务管理添加
	 * @author  肖宏鑫
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFinance")
	@Transactional
	@ApiOperation(value = "系统的财务管理添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加系统的财务管理",url="admin/addFinance") 
	public Result addFinance(Finance entity) throws Exception {
		return maAddFinanceService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  系统的财务管理修改
	 * @author  肖宏鑫
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateFinance")
	@Transactional
	@ApiOperation(value = "系统的财务管理修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改系统的财务管理",url="admin/updateFinance") 
	public Result updateFinance(Finance entity) throws Exception {
		return maUpdateFinanceService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  系统的财务管理删除
	 * @author  肖宏鑫
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeFinance")
	@Transactional
	@ApiOperation(value = "系统的财务管理删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除系统的财务管理",url="admin/removeFinance")
	public Result removeFinance(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return maRemoveFinanceService.execute(new WebClient(request));
	}	
	
}