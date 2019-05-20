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
 * @date 2018/12/25,9:38
 */
@RestController
@RequestMapping("/teacher/teacherLogin")
@Api(tags = "【教师端】-用户登录")
public class TeacherLoginController extends BaseController {

    @Resource(name = "teachUserLoginSendCodeService")
    private AbstractService teachUserLoginSendCodeService;

    @Resource(name = "teacherUserLoginService")
    private AbstractService teacherUserLoginService;

    @Resource(name = "teacherPerfectInfoService")
    private AbstractService teacherPerfectInfoService;

    @Resource(name = "getTeacherInfosService")
    private AbstractService getTeacherInfosService;


    /**
     *@note 手机发送验证码
     *@auhtor 卢祖飞
     *@date 2018/10/16,10:53
     *@version 1.0
     */
    @RequestMapping("/sendCode")
    @ApiOperation(value = "手机发送验证码",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "手机号",name = "teacherPhone",paramType = "query")
    public Result sendCode() throws Exception{
        return teachUserLoginSendCodeService.execute(new WebClient(request));
    }

    /**
     *@note 教师登录
     *@auhtor 卢祖飞
     *@date 2018/10/16,10:53
     *@version 1.0
     */
    @RequestMapping("/login")
    @ApiOperation(value = "教师登录",httpMethod = "POST",response = Result.class)
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号",name = "teacherPhone",paramType = "query"),
            @ApiImplicitParam(value = "验证码",name = "code",paramType = "query")
    })
    @ServiceLogAnn(OPType = "登录/注册",operationName = "存在用户时登录,不存在用户时注册",url = "/teacher/teacherLogin/login")
    public Result login()throws Exception{
        return teacherUserLoginService.execute(new WebClient(request));
    }
    /**
    *@note 完善审核信息
    *@auhtor 卢祖飞
    *@date 2018/12/25,14:29
    *@version 1.0
    */
    @RequestMapping("/perfectInfo")
    @ApiOperation(value = "完善审核信息",httpMethod = "POST",response = Result.class)
    @Transactional
    @ApiImplicitParam(value = "文件上传信息",name = "fileInfo",paramType = "query")
    @ServiceLogAnn(OPType = "完善审核信息",operationName = "完善审核信息",url = "/teacher/teacherLogin/perfectInfo")
    public Result PerfectInfo(Teacher teacher) throws Exception{
        return teacherPerfectInfoService.execute(new WebClient(request,teacher));
    }

    /**
     *@note 查询审核信息
     *@auhtor 卢祖飞
     *@date 2018/10/16,10:53
     *@version 1.0
     */
    @RequestMapping("/getTeacherInfos")
    @ApiOperation(value = "查询审核信息",httpMethod = "POST",response = Result.class)
    public Result getTeacherInfos() throws Exception{
        return getTeacherInfosService.execute(new WebClient(request));
    }

}
