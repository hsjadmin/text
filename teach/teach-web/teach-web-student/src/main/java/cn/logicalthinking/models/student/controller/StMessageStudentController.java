package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
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
 * @Description 【学生】我的-我的消息
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/msg")
@Api(tags = "【学生】我的-我的消息")
public class StMessageStudentController extends BaseController {

    @Resource(name = "stGetMessageStudentListService")
    private AbstractService stGetMessageStudentListService;

    @Resource(name = "stRemoveMessageStudentService")
    private AbstractService removeMessageStudentService;

    @Resource(name = "stReadMessageService")
    private AbstractService stReadMessageService;

    @Resource(name = "stReadAllMessageService")
    private AbstractService stReadAllMessageService;

    @Resource(name = "stRemoveAllMessageService")
    private AbstractService stRemoveAllMessageService;


    /**
     * @throws Exception
     * @Description 学生的消息列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMessageStudentList")
    @ApiOperation(value = "学生的消息列表查询", response = Result.class, httpMethod = "POST")
    public Result MessageStudentList() throws Exception {
        return stGetMessageStudentListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 读取消息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/readMessage")
    @Transactional
    @ApiOperation(value = "读取消息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "读取消息", url = "student/msg/readMessage")
    public Result readMessage(@RequestParam(value = "id", required = true) String id) throws Exception {
        return stReadMessageService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 全部已读
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/readAll")
    @Transactional
    @ApiOperation(value = "全部已读", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "全部已读", url = "student/msg/readAll")
    public Result readAll() throws Exception {
        return stReadAllMessageService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 删除消息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "消息id", name = "ids", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/remove")
    @Transactional
    @ApiOperation(value = "删除消息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "删除", operationName = "删除消息", url = "student/msg/remove")
    public Result removeMessageStudentService() throws Exception {
        return removeMessageStudentService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 全部删除消息
     * @author xhx
     * @date 2018-12-19
     * @version 1.0
     */
    @RequestMapping(value = "/removeAll")
    @Transactional
    @ApiOperation(value = "删除消息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "删除", operationName = "删除消息", url = "student/msg/removeAll")
    public Result removeMessageStudent() throws Exception {
        return stRemoveAllMessageService.execute(new WebClient(request));
    }

}