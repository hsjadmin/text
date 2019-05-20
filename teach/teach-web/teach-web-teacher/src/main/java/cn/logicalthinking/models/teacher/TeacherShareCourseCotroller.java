package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.models.common.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/teacher/share")
@Api(tags = "【教师端】-我的课程")
public class TeacherShareCourseCotroller {

    @Resource(name = "teacherShareCoursesService")
    private AbstractService teacherShareCoursesService;

    /**
     * @note 查询老师的课程
     * @auhtor xhx
     * @date 2018/12/26,11:59
     * @version 1.0
     */
    @RequestMapping("/shareCourse")
    @ApiOperation(value = "分享课程", httpMethod = "POST", response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id", name = "courseId", paramType = "query"),
    })
    public Result getTeacherCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return teacherShareCoursesService.execute(new WebClient(request, response));
    }
}
