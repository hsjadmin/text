package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Student;
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
 * @Description 学生管理
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/Student")
@Api(tags="【后台】-客户管理-学生管理")
public class StudentController extends BaseController {

   	@Resource(name="getStudentListService")
    private AbstractService getStudentListService;
    
    @Resource(name="getStudentfinanceByIdService")
    private AbstractService getStudentfinanceByIdService;
    
   	@Resource(name="addStudentService")
    private AbstractService addStudentService;
	
	@Resource(name="removeStudentService")
    private AbstractService removeStudentService;
    
    @Resource(name="updateStudentService")
    private AbstractService updateStudentService;

    @Resource(name = "removeFinanceStudentByid")
	private AbstractService removeFinanceStudentByid;
    

	/**
	 * @Description  学生表列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getStudentList")
	@ApiOperation(value = "学生表列表查询",response=Result.class, httpMethod = "POST")
	public Result StudentList() throws Exception {
		return getStudentListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description 查询学生收支
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getStudentfinanceById")
	@ApiOperation(value = "查询学生收支",response=Result.class, httpMethod = "POST")
	public Result StudentgetById(@RequestParam(value = "studentId", required = true)String studentId) throws Exception {
		return getStudentfinanceByIdService.execute(new WebClient(request));
	}

	/**
	 * @Description  学生收支删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeStudentfinance")
	@Transactional
	@ApiOperation(value = "学生收支删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="学生收支删除",url="/manage/Student/removeStudentfinance")
	public Result removeStudentfinance(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeFinanceStudentByid.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  学生表添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addStudent")
	@Transactional
	@ApiOperation(value = "学生表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加学生表",url="/manage/Student/addStudent")
	public Result addStudent(Student entity) throws Exception {
		return addStudentService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  学生表修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateStudent")
	@Transactional
	@ApiOperation(value = "学生表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改学生表",url="/manage/Student/updateStudent")
	public Result updateStudent(Student entity) throws Exception {
		return updateStudentService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  学生表删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeStudent")
	@Transactional
	@ApiOperation(value = "学生表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除学生表",url="/manage/Student/removeStudent")
	public Result removeStudent(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeStudentService.execute(new WebClient(request));
	}	
	
}