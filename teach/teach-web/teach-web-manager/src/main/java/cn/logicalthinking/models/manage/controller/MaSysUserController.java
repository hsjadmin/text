package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
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
 * @Description 用户表控制器
 * 
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/user")
@Api(tags="【后台】-后台用户管理")
public class MaSysUserController extends BaseController {

   	@Resource(name="getSysUserListService")
    private AbstractService getSysUserListService;
    
    @Resource(name="getSysUserByIdService")
    private AbstractService getSysUserByIdService;
    
   	@Resource(name="addSysUserService")
    private AbstractService addSysUserService;
	
	@Resource(name="removeSysUserService")
    private AbstractService removeSysUserService;
    
    @Resource(name="updateSysUserService")
    private AbstractService updateSysUserService;
    
    @Resource(name="getSysRoleAllService")
    private AbstractService getSysRoleAllService;
    

	/**
	 * @Description  用户表列表查询
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getSysUserList")
	@ApiOperation(value = "用户表列表查询",response=Result.class, httpMethod = "POST")
	public Result SysUserList() throws Exception {
		return getSysUserListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  用户表根据id查询
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSysUserById")
	@ApiOperation(value = "用户表根据id查询",response=Result.class, httpMethod = "POST")
	public Result SysUsergetById(@RequestParam(value = "userId", required = true)String userId) throws Exception {
		return getSysUserByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * @Description  用户表添加
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSysUser")
	@Transactional
	@ApiOperation(value = "用户表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加用户表",url="admin/addSysUser") 
	public Result addSysUser(SysUser entity) throws Exception {
		return addSysUserService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  用户表修改
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSysUser")
	@Transactional
	@ApiOperation(value = "用户表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改用户表",url="admin/updateSysUser") 
	public Result updateSysUser(SysUser entity) throws Exception {
		return updateSysUserService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  用户表删除
	 * @author  黄世杰
	 * @date 2018-09-28
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeSysUser")
	@Transactional
	@ApiOperation(value = "用户表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除用户表",url="admin/removeSysUser")
	public Result removeSysUser(@RequestParam(value = "userIds", required = true)String userIds) throws Exception {
		return removeSysUserService.execute(new WebClient(request));
	}	
	
	
	@RequestMapping(value = "/getSysRoleAll")
	@ApiOperation(value = "查询所有角色",response=Result.class, httpMethod = "POST")
	public Result getSysRoleAllService() throws Exception {
		return getSysRoleAllService.execute(new WebClient(request));
	}	
	
}