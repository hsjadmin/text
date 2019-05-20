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
 * @Description 【学生】疑难解答
 * @date 2018-12-29
 */
@RestController
@RequestMapping("/student/question")
@Api(tags = "【学生】疑难解答")
public class StQuestionController extends BaseController {

    @Resource(name = "stGetMarketingQuestionListService")
    private AbstractService stGetMarketingQuestionListService;

    @Resource(name = "stGetTeacherListQuestionService")
    private AbstractService stGetTeacherListQuestionService;

    @Resource(name = "stGetTeacherByIdQuestionService")
    private AbstractService stGetTeacherByIdQuestionService;

    @Resource(name = "stAddStudentHasTeacherService")
    private AbstractService stAddStudentHasTeacherService;

    @Resource(name = "stGetQuestionCommentListService")
    private AbstractService stGetQuestionCommentListService;

    @Resource(name = "stStartQuestionService")
    private AbstractService stStartQuestionService;

    @Resource(name = "stPreQuestionOrderService")
    private AbstractService stPreQuestionOrderService;

    @Resource(name = "stCancelQuestionOrderService")
    private AbstractService stCancelQuestionOrderService;

    @Resource(name = "stGetAddressListService")
    private AbstractService stGetAddressListService;

    @Resource(name = "stViewCourseCouponQuestionService")
    private AbstractService stViewCourseCouponQuestionService;

    @Resource(name = "stGetPayInfoService")
    private AbstractService stGetPayInfoService;

    @Resource(name = "stPayCourseQuestionService")
    private AbstractService stPayCourseQuestionService;

    @Resource(name = "stSetUpProblemTimeService")
    private AbstractService stSetUpProblemTimeService;

    @Resource(name = "stToCommentService")
    private AbstractService stToCommentService;

    @Resource(name = "stUpdateTeacherToBuizService")
    private AbstractService stUpdateTeacherToBuizService;

    @Resource(name = "stSendReminderService")
    private AbstractService stSendReminderService;

    /**
     * @throws Exception
     * @Description 营销素材-难题列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-29
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingQuestionList")
    @ApiOperation(value = "营销素材-难题列表查询", response = Result.class, httpMethod = "POST")
    public Result MarketingQuestionList() throws Exception {
        return stGetMarketingQuestionListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询答疑老师列表
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "级别", name = "level", paramType = "query"),
            @ApiImplicitParam(value = "年级", name = "grade", paramType = "query"),
            @ApiImplicitParam(value = "科目", name = "subject", paramType = "query"),
            @ApiImplicitParam(value = "老师名字", name = "name", paramType = "query"),
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getTeacherList")
    @ApiOperation(value = "查询答疑老师列表", response = Result.class, httpMethod = "POST")
    public Result getTeacherList() throws Exception {
        return stGetTeacherListQuestionService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询老师详情接口，返回订阅数followingNum，是否订阅isFollowing,起步时间questionStartTime，评论数commentCount
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getTeacherById")
    @ApiOperation(value = "查询老师详情接口，返回订阅数followingNum，是否订阅isFollowing,起步时间questionStartTime，评论数commentCount", response = Result.class, httpMethod = "POST")
    public Result stGetTeacherByIdQuestionService(@RequestParam(value = "id", required = true) String id) throws Exception {
        return stGetTeacherByIdQuestionService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 关注/取消关注老师
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/follow")
    @ApiOperation(value = "  关注/取消关注老师", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "添加", operationName = "  关注/取消关注老师", url = "student/question/follow")
    public Result stAddStudentHasTeacher(@RequestParam(value = "teacherId", required = true) String teacherId) throws Exception {
        return stAddStudentHasTeacherService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询答疑评论
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老师id", name = "teacherId", paramType = "query"),
            @ApiImplicitParam(value = "评论类型 （0好评1中评2差评）", name = "type", paramType = "query"),
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getQuestionCommentList")
    @ApiOperation(value = "查询答疑评论", response = Result.class, httpMethod = "POST")
    public Result stGetCourseCommentList() throws Exception {
        return stGetQuestionCommentListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 开始答疑
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老师id", name = "teacherId", paramType = "query"),
    })
    @RequestMapping(value = "/startQuestion")
    @ApiOperation(value = "开始答疑", response = Result.class, httpMethod = "POST")
    public Result startQuestion() throws Exception {
        return stStartQuestionService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 创建答疑订单
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老师id", name = "teacherId", paramType = "query"),
            @ApiImplicitParam(value = "上传数据", name = "fileInfo", paramType = "query")
    })
    @RequestMapping(value = "/preQuestionOrder")
    @ApiOperation(value = "创建答疑订单", response = Result.class, httpMethod = "POST")
    public Result preQuestionOrder() throws Exception {
        return stPreQuestionOrderService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 取消答疑订单
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "答疑订单id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/cancelQuestionOrder")
    @ApiOperation(value = "取消答疑订单", response = Result.class, httpMethod = "POST")
    public Result cancelQuestionOrder() throws Exception {
        return stCancelQuestionOrderService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 接通答疑订单
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老师手机号", name = "phone", paramType = "query"),
    })
    @RequestMapping(value = "/updateTeacherToBuiz")
    @ApiOperation(value = "接通答疑订单", response = Result.class, httpMethod = "POST")
    public Result stUpdateTeacherToBuizService() throws Exception {
        return stUpdateTeacherToBuizService.execute(new WebClient(request));
    }

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
     * @note 查询指定订单可用优惠券接口
     * @auhtor 卢祖飞
     * @date 2019/1/3,15:17
     * @version 1.0
     */
    @RequestMapping(value = "/getAvailableCoupon")
    @ApiOperation(value = "  查询指定订单可用优惠券接口", response = Result.class, httpMethod = "POST")
    @ApiImplicitParam(value = "订单id", name = "id", paramType = "query")
    public Result apply() throws Exception {
        return stViewCourseCouponQuestionService.execute(new WebClient(request));
    }

    /**
     * @note 查询订单价格信息
     * @auhtor 卢祖飞
     * @date 2019/1/3,15:17
     * @version 1.0
     */
    @RequestMapping(value = "/getPayInfo")
    @ApiOperation(value = "  查询订单价格信息", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "oid", paramType = "query"),
            @ApiImplicitParam(value = "优惠券id", name = "cid", paramType = "query")
    })
    public Result getPayInfo() throws Exception {
        return stGetPayInfoService.execute(new WebClient(request));
    }

    /**
     * @note 疑难支付
     * @auhtor 卢祖飞
     * @date 2019/1/3,15:17
     * @version 1.0
     */
    @RequestMapping (value = "/payCourseQuestion")
    @ApiOperation(value = "疑难支付", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "oid", paramType = "query"),
            @ApiImplicitParam(value = "优惠券id", name = "cid", paramType = "query"),
            @ApiImplicitParam(value = "收获地址id", name = "addressId", paramType = "query"),
            @ApiImplicitParam(value = "支付密码", name = "payPwd", paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "支付", operationName = "疑难支付", url = "/student/question/payCourseQuestion")
    public Result payCourseQuestion() throws Exception {
        return stPayCourseQuestionService.execute(new WebClient(request));
    }

    /**
     * @note 获取解答时间
     * @auhtor 卢祖飞
     * @date 2019/1/3,15:17
     * @version 1.0
     */
    @RequestMapping(value = "/setupProblemTime")
    @ApiOperation(value = "获取解答时间", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "oid", paramType = "query"),
            @ApiImplicitParam(value = "解答时间", name = "answerTime", paramType = "query"),
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改", operationName = "修改解答时间", url = "/student/question/setupProblemTime")
    public Result setUpProblemTime() throws Exception {
        return stSetUpProblemTimeService.execute(new WebClient(request));
    }

    /**
     * @note 去评论
     * @auhtor 卢祖飞
     * @date 2019/1/5,15:04
     * @version 1.0
     */
    @RequestMapping(value = "/ToComment")
    @ApiOperation(value = "去评论", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "oid", paramType = "query"),
            @ApiImplicitParam(value = "星数", name = "start", paramType = "query"),
            @ApiImplicitParam(value = "评论内容", name = "context", paramType = "query"),
    })
    @Transactional
    @ServiceLogAnn(OPType = "添加", operationName = "去评论", url = "/student/question/ToComment")
    public Result ToComment() throws Exception {
        return stToCommentService.execute(new WebClient(request));
    }

    /**
     * @note 发送疑难提醒
     * @auhtor 卢祖飞
     * @date 2019/1/5,15:04
     * @version 1.0
     */
    @RequestMapping(value = "/sendReminder")
    @ApiOperation(value = "发送疑难提醒", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "老师id", name = "teacherId", paramType = "query"),
    })
    @Transactional
    @ServiceLogAnn(OPType = "提醒", operationName = "发送疑难提醒", url = "/student/question/ sendReminder")
    public Result sendReminder() throws Exception{
        return stSendReminderService.execute(new WebClient(request));
    }

}