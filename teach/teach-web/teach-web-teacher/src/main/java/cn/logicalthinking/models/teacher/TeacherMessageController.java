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
 * @note
 * @auhtor 卢祖飞
 * @date 2018/12/26,15:31
 */
@RestController
@RequestMapping("/teacher/teacherMessage")
@Api(tags = "【教师端】-消息")
public class TeacherMessageController extends BaseController {

    @Resource(name = "getTeacherMessageService")
    private AbstractService getTeacherMessageService;

    @Resource(name = "getMessageInfoService")
    private AbstractService getMessageInfoService;

    @Resource(name = "removeTeacherMessageService")
    private AbstractService removeTeacherMessageService;

    @Resource(name = "setReadStatusService")
    private AbstractService setReadStatusService;

    @Resource(name = "removeDoctorMessageService")
    private AbstractService removeDoctorMessageService;


    /**
     *@note 查询每个老师所有消息
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getTeacherMessage")
    @ApiOperation(value = "查询每个老师所有消息",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    public Result getTeacherMessage() throws Exception{
        return getTeacherMessageService.execute(new WebClient(request));
    }

    /**
     *@note 查看消息详情
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getMessageInfo")
    @ApiOperation(value = "查看消息详情",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "消息id",name = "messageId",paramType = "query")
    public Result getMessageInfo() throws Exception{
        return getMessageInfoService.execute(new WebClient(request));
    }

    /**
     *@note 删除当前用户的所有消息
     *@auhtor 卢祖飞
     *@date 2018/10/17,17:23
     *@version 1.0
     */
    @RequestMapping("/removeMessage")
    @ApiOperation(value = "删除当前用户所有信息",httpMethod = "POST",response = Result.class)
    @Transactional
    @ServiceLogAnn(OPType = "删除",operationName = "删除当前用户的所有消息",url = "/teacher/teacherMessage/removeMessage")
    public Result removeMessage() throws Exception{
        return removeTeacherMessageService.execute(new WebClient(request));
    }

    /**
     *@note 修改已读状态
     *@auhtor 卢祖飞
     *@date 2018/10/17,17:42
     *@version 1.0
     */
    @RequestMapping("/updateReadStatus")
    @ApiOperation(value = "修改已读状态",httpMethod = "POST",response = Result.class)
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改已读状态",url = "/teacher/teacherMessage/updateReadStatus")
    public Result updateReadStatus() throws Exception{
        return setReadStatusService.execute(new WebClient(request));
    }

    /**
     *@note 删除指定消息
     *@auhtor 卢祖飞
     *@date 2018/10/17,17:23
     *@version 1.0
     */
    @RequestMapping("/removeDoctorMessage")
    @ApiOperation(value = "删除指定消息",httpMethod = "POST",response = Result.class)
    @Transactional
    @ApiImplicitParam(value = "消息id",name = "ids",paramType = "query")
    @ServiceLogAnn(OPType = "删除",operationName = "删除指定消息",url = "/teacher/teacherMessage/removeDoctorMessage")
    public Result removeDoctorMessage() throws Exception{
        return removeDoctorMessageService.execute(new WebClient(request));
    }

}
