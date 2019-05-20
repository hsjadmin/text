package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Address;
import cn.logicalthinking.common.entity.QuestionnaireColletion;
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
 * @author xhx
 * @version 1.0
 * @Description 【学生】我的-设置
 * @date 2018-12-26
 */
@RestController
@RequestMapping("/student/setting")
@Api(tags = "【学生】我的-设置")
public class StMineSettingController extends BaseController {

    @Resource(name = "stGetAddressListService")
    private AbstractService stGetAddressListService;

    @Resource(name = "stAddAddressService")
    private AbstractService stAddAddressService;

    @Resource(name = "stUpdateAddressService")
    private AbstractService stUpdateAddressService;

    @Resource(name = "stSetDefaultAddressService")
    private AbstractService stSetDefaultAddressService;

    @Resource(name = "stRemoveAddressService")
    private AbstractService stRemoveAddressService;

    @Resource(name = "stGetQuestionnaireListService")
    private AbstractService stGetQuestionnaireListService;

    @Resource(name = "stVerifyOldPINService")
    private AbstractService stVerifyOldPINService;

    @Resource(name = "stVerifySMSCodeService")
    private AbstractService stVerifySMSCodeService;

    @Resource(name = "stUpdatePINService")
    private AbstractService stUpdatePINService;

    @Resource(name = "stForget2SendSMService")
    private AbstractService stForget2SendSMService;

    @Resource(name = "stAddQuestionnaireColletionService")
    private AbstractService stAddQuestionnaireColletionService;

    /**
     * @throws Exception
     * @Description 学生的收货地址表列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getAddressList")
    @ApiOperation(value = "学生的收货地址表列表查询", response = Result.class, httpMethod = "POST")
    public Result AddressList() throws Exception {
        return stGetAddressListService.execute(new WebClient(request));
    }

    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 学生的收货地址表添加
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @RequestMapping(value = "/addAddress")
    @Transactional
    @ApiOperation(value = "学生的收货地址表添加", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加学生的收货地址表", url = "student/setting/addAddress")
    public Result addAddress(Address entity) throws Exception {
        return stAddAddressService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 学生的收货地址表修改
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @RequestMapping(value = "/updateAddress")
    @Transactional
    @ApiOperation(value = "学生的收货地址表修改", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改学生的收货地址表", url = "student/setting/updateAddress")
    public Result updateAddress(Address entity) throws Exception {
        return stUpdateAddressService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 设置为默认地址
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "地址id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/setDefaultAddress")
    @Transactional
    @ApiOperation(value = "设置为默认地址", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "设置为默认地址", url = "student/setting/setDefaultAddress")
    public Result setDefaultAddress() throws Exception {
        return stSetDefaultAddressService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 学生的收货地址表删除
     * @author xhx
     * @date 2018-12-26
     * @version 1.0
     */
    @RequestMapping(value = "/removeAddress")
    @Transactional
    @ApiOperation(value = "学生的收货地址表删除", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "删除", operationName = "删除学生的收货地址表", url = "student/setting/removeAddress")
    public Result removeAddress(@RequestParam(value = "ids", required = true) String ids) throws Exception {
        return stRemoveAddressService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询问卷题目
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @RequestMapping(value = "/getQuestionnaireList")
    @ApiOperation(value = "查询问卷题目", response = Result.class, httpMethod = "POST")
    public Result stGetQuestionnaireListService() throws Exception {
        return stGetQuestionnaireListService.execute(new WebClient(request));
    }

    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 问卷情况汇总表添加
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @RequestMapping(value = "/addQuestionnaireColletion")
    @Transactional
    @ApiOperation(value = "问卷情况汇总表添加", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加问卷情况汇总表", url = "student/setting/addQuestionnaireColletion")
    public Result addQuestionnaireColletion(QuestionnaireColletion entity) throws Exception {
        return stAddQuestionnaireColletionService.execute(new WebClient(request, entity));
    }


    /**
     * @throws Exception
     * @Description 校验旧密码
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "旧密码", name = "oldPIN", paramType = "query"),
    })
    @RequestMapping(value = "/verifyOldPIN")
    @ApiOperation(value = "校验旧密码", response = Result.class, httpMethod = "POST")
    public Result verifyOldPINService() throws Exception {
        return stVerifyOldPINService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 修改支付密码
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updatePIN")
    @Transactional
    @ApiOperation(value = "修改支付密码", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改支付密码", url = "student/setting/updatePIN")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "新密码", name = "newPIN")
    })
    public Result updatePIN() throws Exception {
        return stUpdatePINService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 忘记密码或首次设置密码，发送验证码
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phone", paramType = "query"),
    })
    @RequestMapping(value = "/sendSMS")
    @ApiOperation(value = "忘记密码或首次设置密码，发送验证码", response = Result.class, httpMethod = "POST")
    public Result sendSMS() throws Exception {
        return stForget2SendSMService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 校验验证码
     * @author xhx
     * @version 1.0
     * @date 2018-12-26
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phone", paramType = "query"),
            @ApiImplicitParam(value = "验证码", name = "SMSCode", paramType = "query"),
    })
    @RequestMapping(value = "/verifySMSCode")
    @ApiOperation(value = "校验验证码", response = Result.class, httpMethod = "POST")
    public Result verifySMSCodeService() throws Exception {
        return stVerifySMSCodeService.execute(new WebClient(request));
    }


}