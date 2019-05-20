package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.entity.Course;
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
 * @date 2018/12/27,9:28
 */
@RestController
@RequestMapping("/teacher/teacherReleaseCourse")
@Api(tags = "【教师端】-发布课程")
public class TeacherReleaseCourseController extends BaseController {

    @Resource(name = "releaseCourseService")
    private AbstractService releaseCourseService;


    @RequestMapping("/ReleaseCourse")
    @ApiOperation(value = "添加课程",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程大纲信息",name = "outlineInfo",paramType = "query"),
            @ApiImplicitParam(value = "课程类别信息",name = "typeInfo",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "添加",operationName = "添加课程",url = "/teacher/teacherReleaseCourse/ReleaseCourse")
    public Result ReleaseCourse(Course course)throws Exception{
        return releaseCourseService.execute(new WebClient(request,course));
    }

}
