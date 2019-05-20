package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.entity.Teacher;
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
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/25,15:30
 */
@RestController
@RequestMapping("/teacher/teacherInfo")
@Api(tags = "【教师端】-个人信息")
public class TeacherInfoController extends BaseController {

    @Resource(name = "getTeacherInfoService")
    private AbstractService getTeacherInfoService;

    @Resource(name = "teacherSelfInfoService")
    private AbstractService teacherSelfInfoService;

    @Resource(name = "updeteTeacherInfoService")
    private AbstractService updeteTeacherInfoService;

    @Resource(name = "getServicePhoneService")
    private AbstractService getServicePhoneService;

    @Resource(name = "setUpTeacherAddressService")
    private AbstractService setUpTeacherAddressService;

    /**
    *@note 查询我的上课信息
    *@auhtor 卢祖飞
    *@date 2018/12/25,16:45
    *@version 1.0
    */
    @RequestMapping("/getTeacherInfo")
    @ApiOperation(value = "查询我的上课信息",httpMethod = "POST",response = Result.class)
    public Result getTeacherInfo() throws Exception{
        return getTeacherInfoService.execute(new WebClient(request));
    }

    /**
     *@note 查询教师个人信息
     *@auhtor 卢祖飞
     *@date 2018/12/25,16:45
     *@version 1.0
     */
    @RequestMapping("/getTeacherSelfInfo")
    @ApiOperation(value = "查询教师个人信息",httpMethod = "POST",response = Result.class)
    public Result getTeacherSelfInfo() throws Exception{
        return teacherSelfInfoService.execute(new WebClient(request));
    }


    /**
    *@note 修改教师信息
    *@auhtor 卢祖飞
    *@date 2018/12/27,18:39
    *@version 1.0
    */
    @RequestMapping("/updateTeacherInfo")
    @ApiOperation(value = "修改教师信息",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "头像路径",name = "hearImg",paramType = "query")
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改个人信息",url = "/teacher/teacherInfo/updateTeacherInfo")
    public Result updateTeacherInfo(Teacher teacher) throws Exception{
        return updeteTeacherInfoService.execute(new WebClient(request,teacher));
    }
    /**
     *@note 查询客服电话
     *@auhtor 卢祖飞
     *@date 2018/12/25,16:45
     *@version 1.0
     */
    @RequestMapping("/getServicePhone")
    @ApiOperation(value = "查询客服电话",httpMethod = "POST",response = Result.class)
    public Result getServicePhone() throws Exception{
        return getServicePhoneService.execute(new WebClient(request));
    }

    /**
     *@note 主页修改地址
     *@auhtor 卢祖飞
     *@date 2018/12/27,18:39
     *@version 1.0
     */
    @RequestMapping("/setUpTeacherAddress")
    @ApiOperation(value = "主页修改地址",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "省市区",name = "region",paramType = "query"),
            @ApiImplicitParam(value = "详细地址",name = "address",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改地址",url = "/teacher/teacherInfo/setUpTeacherAddress")
    public Result setUpTeacherAddress() throws Exception{
        return setUpTeacherAddressService.execute(new WebClient(request));
    }


}
