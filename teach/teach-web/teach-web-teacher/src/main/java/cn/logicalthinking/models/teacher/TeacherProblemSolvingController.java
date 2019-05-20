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
 * @date 2018/12/27,18:00
 */
@RestController
@RequestMapping("/teacher/teacherProblemSolving")
@Api(tags = "【教师端】-难题解疑")
public class TeacherProblemSolvingController extends BaseController {

    @Resource(name = "getProblemSolvingStatusService")
    private AbstractService getProblemSolvingStatusService;

    @Resource(name = "setProblemSolvingStatusService")
    private AbstractService setProblemSolvingStatusService;

    @Resource(name = "setUpProblemTimeService")
    private AbstractService setUpProblemTimeService;

    @Resource(name = "processingOrderService")
    private AbstractService processingOrderService;

    @Resource(name = "onLineQuestionService")
    private AbstractService onLineQuestionService;


    /**
     *@note 查询疑难收费
     *@auhtor 卢祖飞
     *@date 2018/12/28,10:56
     *@version 1.0
     */
    @RequestMapping("/getProblemSolving")
    @ApiOperation(value = "查询疑难收费",httpMethod = "POST",response = Result.class)
    public Result getProblemSolving() throws Exception{
        return getProblemSolvingStatusService.execute(new WebClient(request));
    }


    /**
    *@note 设置疑难收费
    *@auhtor 卢祖飞
    *@date 2018/12/28,10:56
    *@version 1.0
    */
    @RequestMapping("/setProblemSolving")
    @ApiOperation(value = "设置疑难收费",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起步价",name = "startingPrice",paramType = "query"),
            @ApiImplicitParam(value = "超时收费",name = "chargeSettings",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改疑难收费",url = "/teacher/teacherProblemSolving/setProblemSolving")
    public Result setProblemSolving() throws Exception{
        return setProblemSolvingStatusService.execute(new WebClient(request));
    }
    /**
     *@note 修改疑难订单计时
     *@auhtor 卢祖飞
     *@date 2018/12/28,10:56
     *@version 1.0
     */
    @RequestMapping("/setUpProblemTime")
    @ApiOperation(value = "修改疑难订单计时",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id",name = "id",paramType = "query"),
            @ApiImplicitParam(value = "计时时间",name = "answerTime",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改疑难收费",url = "/teacher/teacherProblemSolving/setUpProblemTime")
    public Result setUpProblemTime() throws Exception{
        return setUpProblemTimeService.execute(new WebClient(request));
    }

    /**
     *@note 处理订单
     *@auhtor 卢祖飞
     *@date 2018/12/28,10:56
     *@version 1.0
     */
    @RequestMapping("/processingOrder")
    @ApiOperation(value = "处理订单",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id",name = "id",paramType = "query"),
            @ApiImplicitParam(value = "订单状态(1:解疑中 4:未通过)",name = "status",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改订单状态",url = "/teacher/teacherProblemSolving/processingOrder")
    public Result processingOrder() throws Exception{
        return processingOrderService.execute(new WebClient(request));
    }

    /**
     *@note 疑难上下线
     *@auhtor 卢祖飞
     *@date 2018/12/28,10:56
     *@version 1.0
     */
    @RequestMapping("/onLineQuestion")
    @ApiOperation(value = "疑难上下线",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "上线状态(0否1解疑中2是)",name = "isOnlineStatus",paramType = "query"),
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改上下线",url = "/teacher/teacherProblemSolving/onLineQuestion")
    public Result onLineQuestion() throws Exception{
        return onLineQuestionService.execute(new WebClient(request));
    }

}
