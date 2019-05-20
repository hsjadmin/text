package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】登录
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/login")
@Api(tags = "【学生】登录")
public class StLoginController extends BaseController {

    @Resource(name = "stGetOrAddStudentByOpenIdService")
    private AbstractService stGetOrAddStudentByOpenIdService;

    @Resource(name = "stUpdateStudentService")
    private AbstractService updateStudentService;

    /**
     * @throws Exception
     * @Description 完善信息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/getOrAddStudentByOpenId")
    @ApiOperation(value = "完善信息，参数包括openId，picture，userName", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "添加", operationName = "根据openId查询用户信息,未注册则进行注册", url = "student/login/getOrAddStudentByOpenId")
    public Result stGetOrAddStudentByOpenId(Student student) throws Exception {
        return stGetOrAddStudentByOpenIdService.execute(new WebClient(request, student));
    }

//    /**
//     * swagger 测试参数需要加上   @RequestBody
//     *
//     * @return Result
//     * @throws Exception
//     * @Description 学生表添加
//     * @author xhx
//     * @version 1.0
//     * @date 2018-12-19
//     */
//    @RequestMapping(value = "/addStudent")
//    @Transactional
//    @ApiOperation(value = "学生表添加", httpMethod = "POST", response = Result.class)
//    @ServiceLogAnn(OPType = "添加", operationName = "添加学生表", url = "student/addStudent")
//    public Result addStudent(Student entity) throws Exception {
//        return addStudentService.execute(new WebClient(request, entity));
//    }

    /**
     * @throws Exception
     * @Description 完善信息
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @RequestMapping(value = "/updateStudent")
    @Transactional
    @ApiOperation(value = "完善信息", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "修改", operationName = "完善信息", url = "student/login/updateStudent")
    public Result updateStudent(Student entity) throws Exception {
        return updateStudentService.execute(new WebClient(request, entity));
    }

}