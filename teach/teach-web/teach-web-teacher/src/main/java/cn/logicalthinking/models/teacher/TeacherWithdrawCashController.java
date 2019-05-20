package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.models.common.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @note 提现
 * @auhtor 卢祖飞
 * @date 2018/12/28,11:05
 */
@RestController
@RequestMapping("/teacher/teacherWithdrawCash")
@Api(tags = "【教师端】-提现")
public class TeacherWithdrawCashController extends BaseController {

    @Resource(name = "teacherSelfInfoService")
    private AbstractService teacherSelfInfoService;

    @Resource(name = "cashWithdrawalService")
    private AbstractService cashWithdrawalService;

    /**
    *@note 查询余额
    *@auhtor 卢祖飞
    *@date 2018/12/28,11:34
    *@version 1.0
    */
    @RequestMapping("/teacherSelfInfo")
    @ApiOperation(value = "查询余额",httpMethod = "POST",response = Result.class)
    public Result teacherSelfInfo() throws Exception{
        return teacherSelfInfoService.execute(new WebClient(request));
    }

    /**
     *@note 申请提现
     *@auhtor 卢祖飞
     *@date 2018/12/28,11:34
     *@version 1.0
     */
    @RequestMapping("/cashWithdrawal")
    @ApiOperation(value = "申请提现",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "提现金额",name = "money",paramType = "query"),
            @ApiImplicitParam(value = "提现密码",name = "payPwd",paramType = "query")
    })
    public Result cashWithdrawal() throws Exception{
        return cashWithdrawalService.execute(new WebClient(request));
    }



}
