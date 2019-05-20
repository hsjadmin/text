package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】我的
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/mine")
@Api(tags = "【学生】我的")
public class StMineController extends BaseController {

    @Resource(name = "stGetMineInfoService")
    private AbstractService stGetMineInfoService;

    @Resource(name = "stUpdateStudentService")
    private AbstractService updateStudentService;

    /**
     * @throws Exception
     * @Description 查询用户信息，包含累计上课数和答疑
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getMineInfo")
    @ApiOperation(value = "查询用户信息", response = Result.class, httpMethod = "POST")
    public Result stGetMineInfoService() throws Exception {
        return stGetMineInfoService.execute(new WebClient(request));
    }

    /**
     * @throws Exception
     * @Description 修改信息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updateStudent")
    @Transactional
    @ApiOperation(value = "修改信息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "修改信息", url = "student/updateStudent")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "头像", name = "fileInfo")
    })
    public Result updateStudent(Student entity) throws Exception {
        return updateStudentService.execute(new WebClient(request, entity));
    }

}