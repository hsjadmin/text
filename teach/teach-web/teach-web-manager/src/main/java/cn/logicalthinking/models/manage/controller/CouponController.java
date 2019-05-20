package cn.logicalthinking.models.manage.controller;
import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.entity.CouponGroup;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Coupon;
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
@RequestMapping("/manage/Coupon")
@Api(tags="【后台】-优惠券设置-优惠券管理")
public class CouponController extends BaseController {

   	@Resource(name="getCouponListService")
    private AbstractService getCouponListService;
    
    @Resource(name="getCouponByIdService")
    private AbstractService getCouponByIdService;
    
   	@Resource(name="addCouponService")
    private AbstractService addCouponService;
	
	@Resource(name="removeCouponService")
    private AbstractService removeCouponService;
    
    @Resource(name="updateCouponService")
    private AbstractService updateCouponService;
    

	/**
	 * @Description  优惠券列表查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@ApiImplicitParams({
		@ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
        @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getCouponList")
	@ApiOperation(value = "优惠券列表查询",response=Result.class, httpMethod = "POST")
	public Result CouponList() throws Exception {
		return getCouponListService.execute(new WebClient(request));
	}
	
	/**
	 * @Description  优惠券根据id查询
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCouponById")
	@ApiOperation(value = "优惠券根据id查询",response=Result.class, httpMethod = "POST")
	public Result CoupongetById(@RequestParam(value = "id", required = true)String id) throws Exception {
		return getCouponByIdService.execute(new WebClient(request));
	}
	
	 /**
	 * swagger 测试参数需要加上   @RequestBody
	 * @Description  优惠券添加
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @return Result
	 * @throws Exception
	 */
	@RequestMapping(value = "/addCoupon")
	@Transactional
	@ApiOperation(value = "优惠券添加", httpMethod = "POST",response=Result.class)
	@ServiceLogAnn(OPType="添加",operationName="添加优惠券",url="/manage/Coupon/addCoupon")
	public Result addCoupon(CouponGroup entity) throws Exception {
		return addCouponService.execute(new WebClient(request,entity));
	}	
	
	 /**
	 * @Description  优惠券修改
	 * @author  卢祖飞
	 * @version  1.0
	 * @date 2018-12-19
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCoupon")
	@Transactional
	@ApiOperation(value = "优惠券修改",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="修改",operationName="修改优惠券",url="/manage/Coupon/updateCoupon")
	public Result updateCoupon(Coupon entity) throws Exception {
		return updateCouponService.execute(new WebClient(request,entity));
	}	
	
	/**
	 * @Description  优惠券删除
	 * @author  卢祖飞
	 * @date 2018-12-19
	 * @version  1.0
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeCoupon")
	@Transactional
	@ApiOperation(value = "优惠券删除",response=Result.class, httpMethod = "POST")
	@ServiceLogAnn(OPType="删除",operationName="删除优惠券",url="/manage/Coupon/removeCoupon")
	public Result removeCoupon(@RequestParam(value = "ids", required = true)String ids) throws Exception {
		return removeCouponService.execute(new WebClient(request));
	}	
	
}