package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.SysRole;
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
 * @Description 角色表控制器
 * 
 * @author 黄世杰
 * @date 2018-09-28
 * @version  1.0
 */
@RestController
@RequestMapping("/manage/role")
@Api(tags="【后台】-角色管理")
public class MaSysRoleController extends BaseController {

   	@Resource(name="getSysRoleListService")
    private AbstractService getSysRoleListService;
    
    @Resource(name="getSysRoleByIdService")
    private AbstractService getSysRoleByIdService;
    
   	@Resource(name="addSysRoleService")
    private AbstractService addSysRoleService;
	
	@Resource(name="removeSysRoleService")
    private AbstractService removeSysRoleService;
    
    @Resource(name="updateSysRoleService")
    private AbstractService updateSysRoleService;
    
    @Resource(name="getSysMenuByRoleIdService")
    private AbstractService getSysMenuByRoleIdService;
    
    @Resource(name="getSysMenuAllService")
    private AbstractService getSysMenuAllService;

    @Resource(name="roleDistributionMenuService")
    private AbstractService roleDistributionMenuService;
    

	/**
	 * @Description  角色表列表查询
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getSysRoleList")
	@ApiOperation(value = "角色表列表查询",response=Result.class, httpMethod = "POST")
	public Result SysRoleList() throws Exception {
		return getSysRoleListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  角色表根据id查询
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSysRoleById")
	@ApiOperation(value = "角色表根据id查询",response=Result.class, httpMethod = "POST")
	public Result SysRolegetById(@RequestParam(value = "roleId", required = true)String roleId) throws Exception {
		return getSysRoleByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * @Description  角色表添加
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addSysRole")
	@Transactional
	@ApiOperation(value = "角色表添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加角色表",url="admin/addSysRole") 
	public Result addSysRole(SysRole entity) throws Exception {
		return addSysRoleService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  角色表修改
	 * @author  黄世杰
	 * @version  1.0
	 * @date 2018-09-28
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSysRole")
	@Transactional
	@ApiOperation(value = "角色表修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改角色表",url="admin/updateSysRole") 
	public Result updateSysRole(SysRole entity) throws Exception {
		return updateSysRoleService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  角色表删除
	 * @author  黄世杰
	 * @date 2018-09-28
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeSysRole")
	@Transactional
	@ApiOperation(value = "角色表删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除角色表",url="admin/removeSysRole")
	public Result removeSysRole(@RequestParam(value = "roleIds", required = true)String roleIds) throws Exception {
		return removeSysRoleService.execute(new WebClient(request));
	}	
	
	@RequestMapping(value = "/getSysMenuByRoleId")
	@ApiOperation(value = "根据角色id查询所有的菜单",response=Result.class, httpMethod = "POST")
	public Result getSysMenuByRoleId(@RequestParam(value = "roleId", required = true)String roleId) throws Exception {
		return getSysMenuByRoleIdService.execute(new WebClient(request));
	}	
	
	@RequestMapping(value = "/getSysMenuAll")
	@ApiOperation(value = "询所有的菜单",response=Result.class, httpMethod = "POST")
	public Result getSysMenuAll() throws Exception {
		return getSysMenuAllService.execute(new WebClient(request));
	}	
	@Transactional
	@RequestMapping(value = "/roleDistributionMenu")
	@ServiceLogAnn(OPType="角色分配权限",operationName="角色分配权限",url="admin/roleDistributionMenu")
	@ApiOperation(value = "角色分配权限",response=Result.class, httpMethod = "POST")
	public Result roleDistributionMenu() throws Exception {
		return roleDistributionMenuService.execute(new WebClient(request));
	}	
	
}