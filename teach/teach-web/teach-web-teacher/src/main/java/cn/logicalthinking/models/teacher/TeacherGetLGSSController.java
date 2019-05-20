package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.models.common.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/3,14:46
 */
@RestController
@RequestMapping("/teacher/teacherGetLGSS")
@Api(tags = "【教师端】-年级课程")
public class TeacherGetLGSSController extends BaseController {

    @Resource(name = "getLGSService")
    private AbstractService getLGSService;


    /**
    *@note 查询年级科目
    *@auhtor 卢祖飞
    *@date 2019/1/3,14:49
    *@version 1.0
    */
    @RequestMapping("getLGSS")
    @ApiOperation(value = "查询年级科目",httpMethod = "POST",response = Result.class)
    public Result getLGSS() throws Exception{
        return getLGSService.execute(new WebClient(request));
    }

}
