package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.CourseComment;
import cn.logicalthinking.common.entity.OrderCourse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】约课
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/course")
@Api(tags = "【学生】约课")
public class StCourseController extends BaseController {

    @Resource(name = "stGetCourseBannerListService")
    private AbstractService getMarketingCourseListService;

    @Resource(name = "stReadCourseBannerService")
    private AbstractService stReadCourseBannerService;

    @Resource(name = "stGetMyCourseListService")
    private AbstractService StGetMyCourseListService;

    @Resource(name = "stAddCourseCommentService")
    private AbstractService stAddCourseCommentService;

    @Resource(name = "stGetTeacherListService")
    private AbstractService stGetTeacherListService;

    @Resource(name = "stGetTeacherByIdService")
    private AbstractService stGetTeacherByIdService;

    @Resource(name = "stGetPlaybackListByIdService")
    private AbstractService stGetPlaybackListByIdService;

    @Resource(name = "stGetPlaybackByIdService")
    private AbstractService stGetPlaybackByIdService;

    @Resource(name = "stGetCourseOutlineAndTeacherService")
    private AbstractService stGetCourseOutlineAndTeacherService;

    @Resource(name = "stAddStudentHasTeacherService")
    private AbstractService stAddStudentHasTeacherService;

    @Resource(name = "stGetCourseListService")
    private AbstractService stGetCourseListService;

    @Resource(name = "stGetCourseByIdService")
    private AbstractService stGetCourseByIdService;

    @Resource(name = "stGetCourseCommentListService")
    private AbstractService stGetCourseCommentListService;

    @Resource(name = "stViewCourseInfoService")
    private AbstractService stViewCourseInfoService;

    @Resource(name = "stViewCourseCouponService")
    private AbstractService stViewCourseCouponService;

    @Resource(name = "stPayCourseOrderService")
    private AbstractService stPayCourseOrderService;

    @Resource(name = "stPayCourseOrderQueryService")
    private AbstractService stPayCourseOrderQueryService;

    @Resource(name = "stPayCourseOrderCallbackService")
    private AbstractService stPayCourseOrderCallbackService;

    /**
     * @throws Exception
     * @Description 查询banner
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMarketingCourseList")
    @ApiOperation(value = "查询banner", response = Result.class, httpMethod = "POST")
    public Result MarketingCourseList() throws Exception {
        return getMarketingCourseListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 课程广告计数
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "广告Id", name = "id", paramType = "query", defaultValue = "1"),
    })
    @RequestMapping(value = "/readCourseBanner")
    @ApiOperation(value = "课程广告计数", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "添加", operationName = "添加广告计数", url = "student/course/readCourseBanner")
    public Result readCourseBanner() throws Exception {
        return stReadCourseBannerService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 查询我的课程
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(value = "是否完成，默认查未完成(0否，1是)", name = "isFinish", paramType = "query")
    })
    @RequestMapping(value = "/getMyCourseListService")
    @ApiOperation(value = "查询我的课程", response = Result.class, httpMethod = "POST")
    public Result StGetMyCourseListService() throws Exception {
        return StGetMyCourseListService.execute(new WebClient(request));
    }

    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 课程评论表添加
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "orderCourseId", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "星数", name = "start", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(value = "留言", name = "comment", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/addCourseComment")
    @Transactional
    @ApiOperation(value = "课程评论表添加", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加课程评论表", url = "student/course/addCourseComment")
    public Result addCourseComment(@ApiIgnore CourseComment entity) throws Exception {
        return stAddCourseCommentService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 查询约课老师列表
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
    @ApiOperation(value = "查询约课老师列表(fully 0 可预约，1约满2，没有课程)", response = Result.class, httpMethod = "POST")
    public Result getTeacherList() throws Exception {
        return stGetTeacherListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询老师详情接口，返回订阅数followingNum，是否订阅isFollowing，评论数commentCount
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getTeacherById")
    @ApiOperation(value = "查询老师详情接口，返回订阅数followingNum，是否订阅isFollowing，评论数commentCount", response = Result.class, httpMethod = "POST")
    public Result getTeachergetById(@RequestParam(value = "id", required = true) String id) throws Exception {
        return stGetTeacherByIdService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询章节视频回放列表
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程类型id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/getPlaybackList")
    @ApiOperation(value = "查询章节视频回放列表", response = Result.class, httpMethod = "POST")
    public Result getPlaybackList() throws Exception {
        return stGetPlaybackListByIdService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 查询章节视频回放
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "章节id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/getCourseOutlineAndTeacher")
    @ApiOperation(value = "查询章节信息 和老师信息", response = Result.class, httpMethod = "POST")
    public Result getCourseOutlineAndTeacher() throws Exception {
        return stGetCourseOutlineAndTeacherService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询章节视频回放
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "章节id", name = "id", paramType = "query"),
    })
    @RequestMapping(value = "/getPlaybackById")
    @ApiOperation(value = "查询章节视频回放", response = Result.class, httpMethod = "POST")
    public Result getPlayback() throws Exception {
        return stGetPlaybackByIdService.execute(new WebClient(request));
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
    @ServiceLogAnn(OPType = "添加", operationName = "  关注/取消关注老师", url = "student/course/follow")
    public Result stAddStudentHasTeacher(@RequestParam(value = "teacherId", required = true) String teacherId) throws Exception {
        return stAddStudentHasTeacherService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 查询指定老师开设的课程
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getCourseListByTeacherId")
    @ApiOperation(value = "查询指定老师开设的课程", response = Result.class, httpMethod = "POST")
    public Result stGetCourseListService(@RequestParam(value = "id", required = true) String id) throws Exception {
        return stGetCourseListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 根据id查询课程详情，带课程大纲，是否报名hasApply，评论数appriseNum
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/stGetCourseById")
    @ApiOperation(value = "根据id查询课程详情，带课程大纲，是否报名hasApply，评论数appriseNum", response = Result.class, httpMethod = "POST")
    public Result stGetCourseById(@RequestParam(value = "id", required = true) String id) throws Exception {
        return stGetCourseByIdService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 查询课程评论
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id", name = "courseId", paramType = "query"),
            @ApiImplicitParam(value = "评论类型 （0好评1中评2差评 不传查全部）", name = "type", paramType = "query"),
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/stGetCourseCommentList")
    @ApiOperation(value = "查询课程评论", response = Result.class, httpMethod = "POST")
    public Result stGetCourseCommentList() throws Exception {
        return stGetCourseCommentListService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 立即报名付款界面查询接口
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/viewOrderInfo")
    @ApiOperation(value = "  立即报名付款界面查询接口", response = Result.class, httpMethod = "POST")
    public Result stViewCourseInfo(@RequestParam(value = "courseTypeId", required = true) String courseTypeId) throws Exception {
        return stViewCourseInfoService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 课程订单支付接口
     * @author xhx
     * @version 1.0
     * @date 2018-12-25
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程类型id", name = "courseTypeId", paramType = "query"),
            @ApiImplicitParam(value = "是否使用优惠券（0否，1是）", name = "isCoupon", paramType = "query"),
            @ApiImplicitParam(value = "优惠券id", name = "couponId", paramType = "query"),
            @ApiImplicitParam(value = "姓名", name = "name", paramType = "query"),
            @ApiImplicitParam(value = "手机号", name = "phone", paramType = "query"),
            @ApiImplicitParam(value = "地址", name = "address", paramType = "query"),
            @ApiImplicitParam(value = "付款方式,0自己支付，1好友代付, 2余额支付", name = "payment", paramType = "query")
    })
    @RequestMapping(value = "/payCourseOrder")
    @ApiOperation(value = "课程订单支付接口", response = Result.class, httpMethod = "POST")
    public Result recharge(@ApiIgnore OrderCourse orderCourse) throws Exception {
        return stPayCourseOrderService.execute(new WebClient(request, orderCourse));
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
    @RequestMapping(value = "/courseOrderQuery")
    @ApiOperation(value = "充值订单查询接口", response = Result.class, httpMethod = "POST")
    public Result stPayCourseOrderQueryService() throws Exception {
        return stPayCourseOrderQueryService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 课程订单支付回调接口
     * @author xhx
     * @version 1.0
     * @date 2018-12-25
     */
    @RequestMapping(value = "/payCourseOrderPayCallback")
    @ApiOperation(value = "课程订单支付回调接口", response = Result.class, httpMethod = "POST")
    public Result rechargeCallback(HttpServletResponse response) throws Exception {
        return stPayCourseOrderCallbackService.execute(new WebClient(request, response));
    }

    /**
     * @throws Exception
     * @Description 查询指定订单可用优惠券接口
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getAvailableCoupon")
    @ApiOperation(value = "  查询指定订单可用优惠券接口", response = Result.class, httpMethod = "POST")
    public Result apply(@RequestParam(value = "courseTypeId", required = true) String courseTypeId) throws Exception {
        return stViewCourseCouponService.execute(new WebClient(request));
    }


}