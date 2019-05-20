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
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 用户管理控制器
 * 
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */
@RestController
@RequestMapping("/manage")
@Api(tags="【后台】-用户管理控制器")
public class SysUserManageController extends BaseController {

   	@Resource(name="adminUserLoginService")
    private AbstractService adminUserLoginService;
   	
   	@Resource(name="updateAdminPasswordService")
   	private AbstractService updateAdminPasswordService;
   	
   	@Resource(name="adminUserOutLoginSerivce")
   	private AbstractService adminUserOutLoginSerivce;
   	
   	@Resource(name="getMenuByUserIdSerivce")
   	private AbstractService getMenuByUserIdSerivce;
   	
	@Resource(name="updateAdminStateService")
   	private AbstractService updateAdminStateService;
	
	@Resource(name="getUserInfoService")
	private AbstractService getUserInfoService;
	
	@Resource(name="getUserRoleBtnService")
	private AbstractService getUserRoleBtnService;

	@Resource(name = "updateUserHearImgService")
	private AbstractService updateUserHearImgService;
    
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  用户表添加
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminLogin")
	@Transactional
	@ApiImplicitParams({
		@ApiImplicitParam(value = "用户名", name = "userName", paramType = "query"),
		@ApiImplicitParam(value = "密码",name = "pwd",paramType = "query")
    })
	@ApiOperation(value = "后台用户登录", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="登录",operationName="后台用户登录",url="admin/adminUserLogin") 
	public Result adminUserLogin() throws Exception {
		return adminUserLoginService.execute(new WebClient(request));
	}	
	
	/**
	 * @Description 后台用户修改密码
	 * @author 黄世杰
	 * @下午4:16:37
	 * @version  1.0
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAdminPassword")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "旧密码", name = "oldPwd", paramType = "query"),
        @ApiImplicitParam(value = "新密码", name = "newPwd", paramType = "query"),
		@ApiImplicitParam(value = "确认密码",name = "truePwd",paramType = "query")
    })
	@Transactional
	@ApiOperation(value = "后台用户修改密码", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="修改",operationName="后台用户修改密码",url="admin/updateAdminPassword") 
	public Result updateAdminPasswordService() throws Exception {
		return updateAdminPasswordService.execute(new WebClient(request));
	}	
	
	@RequestMapping(value = "/updateAdminState")
	@ApiImplicitParams({
		@ApiImplicitParam(value = "用户主键", name = "userId", paramType = "query"),
		@ApiImplicitParam(value = "state", name = "状态（0启用  1禁用）", paramType = "query"),
	})
	@Transactional
	@ApiOperation(value = "后台用户启用禁用", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="启用/禁用",operationName="后台用户启用禁用",url="admin/updateAdminState") 
	public Result updateAdminStateService() throws Exception {
		return updateAdminStateService.execute(new WebClient(request));
	}	
	
	/**
	 * @Description 后台用户退出登录
	 * @author 黄世杰
	 * @下午4:16:37
	 * @version  1.0
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/adminUserOutLogin")
	@Transactional
	@ApiOperation(value = "后台用户退出登录", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="退出登录",operationName="后台用户退出登录",url="admin/adminUserOutLogin") 
	public Result adminUserOutLogin() throws Exception {
		return adminUserOutLoginSerivce.execute(new WebClient(request));
	}	
	

		/**
	 * @Description 根据当前用户id查询所有的权限
	 * @author 黄世杰
	 * @下午4:16:37
	 * @version  1.0
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMenuByuserId")
	@ApiOperation(value = "根据当前用户id查询所有的权限", httpMethod = "POST",response=Result.class)
	public Result getMenuByuserId() throws Exception {
		return getMenuByUserIdSerivce.execute(new WebClient(request));
	}
	
	/**
	 * @Description 根据当前用户id查询所有的功能菜单
	 * @author 黄世杰
	 * @下午4:16:37
	 * @version  1.0
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserRoleBtn")
	@ApiOperation(value = "根据当前用户id查询所有的按钮", httpMethod = "POST",response=Result.class)
	public Result getUserRoleBtn() throws Exception {
		return getUserRoleBtnService.execute(new WebClient(request));
	}
	
	/**
	 * @Description 获取当前登录用户信息
	 * @author 黄世杰
	 * @下午4:16:37
	 * @version  1.0
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getUserInfo")
	@ApiOperation(value = "获取当前登录用户信息", httpMethod = "POST",response=Result.class)
	public Result getUserInfo() throws Exception {
		return getUserInfoService.execute(new WebClient(request));
	}


	@RequestMapping(value = "/updateUserHearImg")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "头像信息", name = "fileInfo", paramType = "query"),
	})
	@Transactional
	@ApiOperation(value = "修改用户头像", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="修改",operationName="修改用户头像",url="manage/updateUserHearImg")
	public Result updateUserHearImg() throws Exception{
		return updateUserHearImgService.execute(new WebClient(request));
	}
}