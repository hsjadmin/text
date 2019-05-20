package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Teacher;
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
 * @Description 老师表控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/Teacher")
@Api(tags="【后台】--客户管理--老师管理")
public class TeacherController extends BaseController {

   	@Resource(name= "maGetTeacherListService")
    private AbstractService getTeacherListService;
    
    @Resource(name= "getTeacherFinanceByIdService")
    private AbstractService getTeacherFinanceByIdService;
    
   	@Resource(name="addTeacherService")
    private AbstractService addTeacherService;
	
	@Resource(name="removeTeacherService")
    private AbstractService removeTeacherService;
    
    @Resource(name="updateTeacherService")
    private AbstractService updateTeacherService;

    @Resource(name = "updateTeacherWithdrawalStatusService")
	private AbstractService updateTeacherWithdrawalStatusService;
    

	/**
	 * @Description  老师表列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getTeacherList")
	@ApiOperation(value = "老师表列表查询",response=Result.class, httpMethod = "POST")
	public Result TeacherList() throws Exception {
		return getTeacherListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  查询老师收支信息
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTeacherById")
	@ApiOperation(value = "查询老师收支信息",response=Result.class, httpMethod = "POST")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "账户类型(0收入1提现)", name = "type", paramType = "query"),
			@ApiImplicitParam(value = "开始时间", name = "startTime", paramType = "query"),
			@ApiImplicitParam(value = "结束时间", name = "endTime", paramType = "query"),
	})
	public Result TeachergetById(@RequestParam(value = "teacherId", required = true)String teacherId) throws Exception {
		return getTeacherFinanceByIdService.execute(new WebClient(request));
	}

	/**
	 * @Description  修改提现状态
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateTeacherWithdrawalStatus")
	@Transactional
	@ApiImplicitParams({
			@ApiImplicitParam(value = "明细id", name = "id", paramType = "query"),
			@ApiImplicitParam(value = "状态", name = "isFinish", paramType = "query"),
	})
	@ApiOperation(value = "修改提现状态", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="修改",operationName="修改提现状态",url="/manage/Teacher/updateTeacherWithdrawalStatus")
	public Result updateTeacherWithdrawalStatus() throws Exception{
		return updateTeacherWithdrawalStatusService.execute(new WebClient(request));
	}

	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  老师表添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addTeacher")
	@Transactional
	@ApiOperation(value = "老师表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加老师表",url="/manage/Teacher/addTeacher")
	public Result addTeacher(Teacher entity) throws Exception {
		return addTeacherService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  老师表修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateTeacher")
	@Transactional
	@ApiOperation(value = "老师表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改老师表",url="/manage/Teacher/updateTeacher")
	public Result updateTeacher(Teacher entity) throws Exception {
		return updateTeacherService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  老师表删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeTeacher")
	@Transactional
	@ApiOperation(value = "老师表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除老师表",url="/manage/Teacher/removeTeacher")
	public Result removeTeacher(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeTeacherService.execute(new WebClient(request));
	}	
	
}