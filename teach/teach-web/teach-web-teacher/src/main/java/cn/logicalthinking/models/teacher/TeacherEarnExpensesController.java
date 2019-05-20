package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.models.common.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @note 赚取费用
 * @auhtor 卢祖飞
 * @date 2018/12/26,16:49
 */
@RestController
@RequestMapping("/teacher/teacherEarnExpenses")
@Api(tags = "【教师端】-赚取费用")
public class TeacherEarnExpensesController extends BaseController {


    @Resource(name = "getTeacherBalanceService")
    private AbstractService getTeacherBalanceService;

    @Resource(name = "getTeacherDetailedService")
    private AbstractService getTeacherDetailedService;

    @Resource(name = "verificationPhontService")
    private AbstractService verificationPhontService;

    @Resource(name = "setPayPwdService")
    private AbstractService setPayPwdService;

    @Resource(name = "changePasswordService")
    private AbstractService changePasswordService;

    @Resource(name = "verificationOldPwdService")
    private AbstractService verificationOldPwdService;

    @Resource(name = "updatePwdSetPwdService")
    private AbstractService updatePwdSetPwdService;

    @Resource(name = "isSetUpPayPwdService")
    private AbstractService isSetUpPayPwdService;

    /**
    *@note 查询老师余额与明细
    *@auhtor 卢祖飞
    *@date 2018/12/26,17:23
    *@version 1.0
    */
    @RequestMapping("/getTeacherBalance")
    @ApiOperation(value = "查询老师账户明细",httpMethod = "POST",response = Result.class)
    public Result getTeacherBalance()throws Exception{
        return getTeacherBalanceService.execute(new WebClient(request));
    }

    /**
     *@note 查询老师所有明细
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/getTeacherDetailed")
    @ApiOperation(value = "查询老师所有明细",httpMethod = "POST",response = Result.class)
    public Result getTeacherDetailed() throws Exception{
        return getTeacherDetailedService.execute(new WebClient(request));
    }

    /**
     *@note 验证手机发送验证码
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/checkPhone")
    @ApiOperation(value = "验证手机发送验证码",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "手机哈",name = "teacherPhone",paramType = "query")
    public Result checkPhone() throws Exception{
        return updatePwdSetPwdService.execute(new WebClient(request));
    }

    /**
     *@note 忘记密码/首次设置密码验证身份
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/verificationPhont")
    @ApiOperation(value = "忘记密码/首次设置密码验证身份",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "电话号码",name = "phone",paramType = "query"),
            @ApiImplicitParam(value = "验证码",name = "code",paramType = "query"),
    })
    public Result verificationPhont() throws Exception{
        return verificationPhontService.execute(new WebClient(request));
    }

    /**
     *@note 验证是否设置提现密码
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/isSetUpPayPwd")
    @ApiOperation(value = "验证是否设置提现密码",httpMethod = "POST",response = Result.class)
    public Result isSetUpPayPwd() throws Exception{
        return isSetUpPayPwdService.execute(new WebClient(request));
    }

    /**
     *@note 忘记密码/首次设置密码
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/updatePayPwd")
    @ApiOperation(value = "修改密码/首次设置密码",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "密码",name = "pwd",paramType = "query")
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改密码",url = "/teacher/teacherEarnExpenses/updatePayPwd")
    public Result updatePayPwd() throws Exception {
        return setPayPwdService.execute(new WebClient(request));
    }



    /**
     *@note 验证旧密码
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/verificationOldPwd")
    @ApiOperation(value = "验证旧密码",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "旧密码",name = "oldPwd",paramType = "query")
    public Result verificationOldPwd() throws Exception{
        return verificationOldPwdService.execute(new WebClient(request));
    }

    /**
     *@note 修改密码
     *@auhtor 卢祖飞
     *@date 2018/12/26,17:23
     *@version 1.0
     */
    @RequestMapping("/changePassword")
    @ApiOperation(value = "修改密码",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "新密码",name = "pwd1",paramType = "query"),
            @ApiImplicitParam(value = "确认新密码",name = "pwd2",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改密码",url = "/teacher/teacherEarnExpenses/changePassword")
    public Result changePassword() throws Exception{
        return changePasswordService.execute(new WebClient(request));
    }



}
