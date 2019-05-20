package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.ApplicationParameter;
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
 * @Description 系统参数设置表控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/ApplicationParameter")
@Api(tags="【后台】-设置管理-平台基础设置")
public class ApplicationParameterController extends BaseController {

   	@Resource(name="getApplicationParameterListService")
    private AbstractService getApplicationParameterListService;
    
    @Resource(name="getApplicationParameterByIdService")
    private AbstractService getApplicationParameterByIdService;
    
   	@Resource(name="addApplicationParameterService")
    private AbstractService addApplicationParameterService;
	
	@Resource(name="removeApplicationParameterService")
    private AbstractService removeApplicationParameterService;
    
    @Resource(name="updateApplicationParameterService")
    private AbstractService updateApplicationParameterService;
    

	/**
	 * @Description  系统参数设置表列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getApplicationParameterList")
	@ApiOperation(value = "系统参数设置表列表查询",response=Result.class, httpMethod = "POST")
	public Result ApplicationParameterList() throws Exception {
		return getApplicationParameterListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  系统参数设置表根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getApplicationParameterById")
	@ApiOperation(value = "系统参数设置表根据id查询",response=Result.class, httpMethod = "POST")
	public Result ApplicationParametergetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getApplicationParameterByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  系统参数设置表添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addApplicationParameter")
	@Transactional
	@ApiOperation(value = "系统参数设置表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加系统参数设置表",url="/manage/ApplicationParameter/addApplicationParameter")
	public Result addApplicationParameter(ApplicationParameter entity) throws Exception {
		return addApplicationParameterService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  系统参数设置表修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateApplicationParameter")
	@Transactional
	@ApiOperation(value = "系统参数设置表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改系统参数设置表",url="/manage/ApplicationParameter/updateApplicationParameter")
	public Result updateApplicationParameter(ApplicationParameter entity) throws Exception {
		return updateApplicationParameterService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  系统参数设置表删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeApplicationParameter")
	@Transactional
	@ApiOperation(value = "系统参数设置表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除系统参数设置表",url="/manage/ApplicationParameter/removeApplicationParameter")
	public Result removeApplicationParameter(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeApplicationParameterService.execute(new WebClient(request));
	}	
	
}