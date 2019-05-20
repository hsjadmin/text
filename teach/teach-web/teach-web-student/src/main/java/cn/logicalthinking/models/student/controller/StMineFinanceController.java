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
import javax.servlet.http.HttpServletResponse;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】我的-我的余额
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/finance")
@Api(tags = "【学生】我的-我的余额")
public class StMineFinanceController extends BaseController {

    @Resource(name = "stGetFinanceStudentListService")
    private AbstractService stGetFinanceStudentListService;

    @Resource(name = "stRechargeService")
    private AbstractService stRechargeService;

    @Resource(name = "stRechargeQueryService")
    private AbstractService stRechargeQueryService;

    @Resource(name = "stRechargeCallbackService")
    private AbstractService stRechargeCallbackService;

    /**
     * @throws Exception
     * @Description 充值
     * @author xhx
     * @version 1.0
     * @date 2018-12-25
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "充值金额", name = "amount", paramType = "query"),
            @ApiImplicitParam(value = "付款方式,0自己支付，1好友代付", name = "payment", paramType = "query")
    })
    @RequestMapping(value = "/recharge")
    @ApiOperation(value = "充值", response = Result.class, httpMethod = "POST")
    public Result recharge() throws Exception {
        return stRechargeService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 充值回调
     * @author xhx
     * @version 1.0
     * @date 2018-12-25
     */
    @RequestMapping(value = "/rechargePayCallback")
    @ApiOperation(value = "充值回调", response = Result.class, httpMethod = "POST")
    public Result rechargeCallback(HttpServletResponse response) throws Exception {
        return stRechargeCallbackService.execute(new WebClient(request, response));
    }

    /**
     * @throws Exception
     * @Description 充值订单查询接口
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/rechargeQuery")
    @ApiOperation(value = "充值订单查询接口", response = Result.class, httpMethod = "POST")
    public Result stRechargeQueryService() throws Exception {
        return stRechargeQueryService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 学生的账户费用明细列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getFinanceStudentList")
    @ApiOperation(value = "学生的账户费用明细列表查询", response = Result.class, httpMethod = "POST")
    public Result FinanceStudentList() throws Exception {
        return stGetFinanceStudentListService.execute(new WebClient(request));
    }

}