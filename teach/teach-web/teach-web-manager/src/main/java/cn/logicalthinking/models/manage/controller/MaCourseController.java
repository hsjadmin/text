package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.CourseType;
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
 * @Description 【后台】--订单管理-课程订单
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/manage/course")
@Api(tags = "【后台】--课程管理-课程管理")
public class MaCourseController extends BaseController {

    @Resource(name = "maGetCourseTypeListService")
    private AbstractService getCourseTypeListService;

    @Resource(name = "maGetCourseTypeByIdService")
    private AbstractService getCourseTypeByIdService;

    @Resource(name = "maAddCourseService")
    private AbstractService addCourseService;

    @Resource(name = "maRemoveCourseService")
    private AbstractService removeCourseService;

    @Resource(name = "maUpdateCourseTypeService")
    private AbstractService updateCourseTypeService;

    @Resource(name = "maSwitchShowService")
    private AbstractService maSwitchShowService;

    @Resource(name = "maGetTeacherListService")
    private AbstractService getTeacherListService;

    /**
     * @throws Exception
     * @Description 课程类型表，即大班课小班课一对一等课程列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(value = "年纪", name = "grade", paramType = "query"),
            @ApiImplicitParam(value = "科目", name = "subject", paramType = "query"),
            @ApiImplicitParam(value = "课程类型", name = "courseType", paramType = "query"),
            @ApiImplicitParam(value = "课程时长", name = "duration", paramType = "query"),
            @ApiImplicitParam(value = "授课课时", name = "periods", paramType = "query"),
            @ApiImplicitParam(value = "上课时间", name = "courseTime", paramType = "query"),
            @ApiImplicitParam(value = "老师姓名", name = "teacherName", paramType = "query"),
    })
    @RequestMapping(value = "/getCourseTypeList")
    @ApiOperation(value = "课程类型表，即大班课小班课一对一等课程列表查询", response = Result.class, httpMethod = "POST")
    public Result CourseTypeList() throws Exception {
        return getCourseTypeListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 课程类型表，即大班课小班课一对一等课程根据id查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getCourseTypeById")
    @ApiOperation(value = "课程类型表，即大班课小班课一对一等课程根据id查询", response = Result.class, httpMethod = "POST")
    public Result CourseTypegetById(@RequestParam(value = "id", required = true) String id) throws Exception {
        return getCourseTypeByIdService.execute(new WebClient(request));
    }

    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 添加课程
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/addCourse")
    @Transactional
    @ApiOperation(value = "添加课程", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加课程", url = "manage/addCourse")
    public Result addCourse() throws Exception {
        return addCourseService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 课程类型表，即大班课小班课一对一等课程修改
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updateCourseType")
    @Transactional
    @ApiOperation(value = "课程类型表，即大班课小班课一对一等课程修改", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改课程类型表，即大班课小班课一对一等课程", url = "manage/updateCourseType")
    public Result updateCourseType(CourseType entity) throws Exception {
        return updateCourseTypeService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 删除课程
     * @author xhx
     * @date 2018-12-19
     * @version 1.0
     */
    @RequestMapping(value = "/removeCourse")
    @Transactional
    @ApiOperation(value = "删除课程", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "删除", operationName = "删除课程", url = "manage/removeCourse")
    public Result removeCourse(@RequestParam(value = "ids", required = true) String ids) throws Exception {
        return removeCourseService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 老师表列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getTeacherList")
    @ApiOperation(value = "老师表列表查询", response = Result.class, httpMethod = "POST")
    public Result TeacherList() throws Exception {
        return getTeacherListService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 隐藏或显示老师课程
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/switchShow")
    @Transactional
    @ApiOperation(value = "隐藏或显示老师课程", response = Result.class, httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id", name = "courseId", paramType = "query", defaultValue = "1"),
    })
    @ServiceLogAnn(OPType = "修改", operationName = "隐藏或显示老师课程", url = "manage/updateCourseType")
    public Result switchShow() throws Exception {
        return maSwitchShowService.execute(new WebClient(request));
    }

}