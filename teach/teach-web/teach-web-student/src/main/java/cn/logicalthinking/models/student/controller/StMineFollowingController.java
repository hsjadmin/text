package cn.logicalthinking.models.student.controller;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 【学生】我的-我的关注
 * 
 * @author 黄世杰
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/student/following")
@Api(tags="【学生】我的-我的关注")
public class StMineFollowingController extends BaseController {

   	@Resource(name="stGetFollowingListService")
    private AbstractService stGetFollowingListService;
    
//    @Resource(name="getStudentHasTeacherByIdService")
//    private AbstractService getStudentHasTeacherByIdService;
    


	/**
	 * @Description  学生关注老师表列表查询
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getStudentHasTeacherList")
	@ApiOperation(value = "学生关注老师表列表查询",response=Result.class, httpMethod = "POST")
	public Result StudentHasTeacherList() throws Exception {
		return stGetFollowingListService.execute(new WebClient(request));
	}
	
//	/**
//	 * @Description  学生关注老师表根据id查询
//	 * @author  黄世杰
//	 * @version  1.0
//	 * @date 2018-12-19
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/getStudentHasTeacherById")
//	@ApiOperation(value = "学生关注老师表根据id查询",response=Result.class, httpMethod = "POST")
//	public Result StudentHasTeachergetById(@RequestParam(value = "studentId", required = true)String studentId) throws Exception {
//		return getStudentHasTeacherByIdService.execute(new WebClient(request));
//	}
//

}