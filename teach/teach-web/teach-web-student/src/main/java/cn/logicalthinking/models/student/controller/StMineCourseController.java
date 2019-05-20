package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.CourseComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】登录
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/mine/course")
@Api(tags = "【学生】我的-我的课程")
public class StMineCourseController extends BaseController {

    @Resource(name = "stGetMineCourseListService")
    private AbstractService stGetMineCourseListService;

    @Resource(name = "stAddCourseCommentService")
    private AbstractService stAddCourseCommentService;

    /**
     * @Description  查询我的课程
     * @author  xhx
     * @version  1.0
     * @date 2018-12-19
     * @throws Exception
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMineCourseList")
    @ApiOperation(value = "查询我的课程",response=Result.class, httpMethod = "POST")
    public Result getMineCourseList() throws Exception {
        return stGetMineCourseListService.execute(new WebClient(request));
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
    @ServiceLogAnn(OPType = "添加", operationName = "添加课程评论表", url = "student/mine/course/addCourseComment")
    public Result addCourseComment(@ApiIgnore CourseComment entity) throws Exception {
        return stAddCourseCommentService.execute(new WebClient(request, entity));
    }

}