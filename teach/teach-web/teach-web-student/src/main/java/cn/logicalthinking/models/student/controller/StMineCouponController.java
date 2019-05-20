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
 * @Description 优惠券控制器
 * 
 * @author xhx
 * @date 2018-12-19
 * @version  1.0
 */
@RestController
@RequestMapping("/student/mine/coupon")
@Api(tags="【学生】我的-优惠券")
public class StMineCouponController extends BaseController {

   	@Resource(name="stGetCouponListService")
    private AbstractService stGetCouponListService;
    
	/**
	 * @Description  优惠券列表查询
	 * @author  xhx
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
	public Result getCouponList() throws Exception {
		return stGetCouponListService.execute(new WebClient(request));
	}
}