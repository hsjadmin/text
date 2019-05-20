package cn.logicalthinking.models.manage.controller;
import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Coupon;
import cn.logicalthinking.common.entity.SysUser;
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
 * @Description 优惠券控制器
 * 
 * @author 卢祖飞
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/examine")
@Api(tags="【后台】-审核管理-审核页面")
public class examineUserInfoController extends BaseController {

   	@Resource(name="getExamineUserInfoService")
    private AbstractService getexamineUserInfoService;

   	@Resource(name="examineUserInfoService")
    private AbstractService examineUserInfoService;

	/**
	 * @Description  用户信息列表查询
	 * @author  黄世杰
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getexamineUserInfo")
	@ApiOperation(value = "用户信息列表查询",response=Result.class, httpMethod = "POST")
	public Result getexamineUserInfo() throws Exception {
		return getexamineUserInfoService.execute(new WebClient(request));
	}

	/**
	 * @Description  审核教师
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @return Result
	 * @throws Exception    String id=data.getParameter("id");
	 * 		String type=data.getParameter("type");
	 * 		String reason=data.getParameter("reason");
	 */
	@RequestMapping(value = "/examineUserInfo")
	@Transactional
	@ApiImplicitParams({
			@ApiImplicitParam(value = "id", name = "id", paramType = "query", defaultValue = "1"),
			@ApiImplicitParam(value = "操作类型（0审核通过  1审核不通过）", name = "type", paramType = "query", defaultValue = "10"),
			@ApiImplicitParam(value = "不通过内容", name = "reason", paramType = "query", defaultValue = "10")
	})
	@ApiOperation(value = " 审核教师", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="修改",operationName=" 审核教师",url="admin/examineUserInfo")
	public Result examineUserInfo() throws Exception {
		return examineUserInfoService.execute(new WebClient(request));
	}



}