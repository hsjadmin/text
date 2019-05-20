package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.OrderCourse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xhx
 * @version 1.0
 * @Description 【web后台】--订单管理-课程订单
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/manage/order/course")
@Api(tags = "【后台】--订单管理-课程订单")
public class MaOrderCourseController extends BaseController {

    @Resource(name = "maGetOrderCourseListService")
    private AbstractService maGetOrderCourseListService;

    @Resource(name = "maOrderCourseExportService")
    private AbstractService maOrderCourseExportService;

    @Resource(name = "maGetOrderCourseByIdService")
    private AbstractService maGetOrderCourseByIdService;

    @Resource(name = "maAddOrderCourseService")
    private AbstractService maAddOrderCourseService;

    @Resource(name = "maRemoveOrderCourseService")
    private AbstractService maRemoveOrderCourseService;

    @Resource(name = "maUpdateOrderCourseService")
    private AbstractService maUpdateOrderCourseService;

    @Resource(name = "maGetTeacherListService")
    private AbstractService getTeacherListService;

    /**
     * @throws Exception
     * @Description 课程订单列表查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getOrderCourseList")
    @ApiOperation(value = "课程订单列表查询", response = Result.class, httpMethod = "POST")
    public Result OrderCourseList() throws Exception {
        return maGetOrderCourseListService.execute(new WebClient(request));
    }


    /**
     * @throws Exception
     * @Description 课程订单导出
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/export")
    @ApiOperation(value = "课程订单导出", response = Result.class, httpMethod = "POST")
    public Result OrderCourseExport(HttpServletResponse response) throws Exception {

        return maOrderCourseExportService.execute(new WebClient(request, response));
    }

    /**
     * @throws Exception
     * @Description 课程订单根据id查询
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getOrderCourseById")
    @ApiOperation(value = "课程订单根据id查询", response = Result.class, httpMethod = "POST")
    public Result OrderCoursegetById(@RequestParam(value = "id", required = true) String id) throws Exception {
        return maGetOrderCourseByIdService.execute(new WebClient(request));
    }

    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 课程订单添加
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/addOrderCourse")
    @Transactional
    @ApiOperation(value = "课程订单添加", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加课程订单", url = "admin/addOrderCourse")
    public Result addOrderCourse(OrderCourse entity) throws Exception {
        return maAddOrderCourseService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 课程订单修改
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updateOrderCourse")
    @Transactional
    @ApiOperation(value = "课程订单修改", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改课程订单", url = "admin/updateOrderCourse")
    public Result updateOrderCourse(OrderCourse entity) throws Exception {
        return maUpdateOrderCourseService.execute(new WebClient(request, entity));
    }

    /**
     * @throws Exception
     * @Description 课程订单删除
     * @author xhx
     * @date 2018-12-19
     * @version 1.0
     */
    @RequestMapping(value = "/removeOrderCourse")
    @Transactional
    @ApiOperation(value = "课程订单删除", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "删除", operationName = "删除课程订单", url = "admin/removeOrderCourse")
    public Result removeOrderCourse(@RequestParam(value = "ids", required = true) String ids) throws Exception {
        return maRemoveOrderCourseService.execute(new WebClient(request));
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
}