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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】登录凭证校验
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/wx")
@Api(tags = "【学生】登录凭证校验")
public class StWXController extends BaseController {

    @Resource(name = "stCode2SessionService")
    private AbstractService stCode2SessionService;

    /**
     * @throws Exception
     * @Description 登录凭证校验
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "登录时获取的 code", name = "code", paramType = "query"),
    })
    @RequestMapping(value = "/code2Session")
    @ApiOperation(value = "登录凭证校验", response = Result.class, httpMethod = "POST")
    @ServiceLogAnn(OPType = "登录凭证校验", operationName = "登录凭证校验", url = "student/course/code2Session")
    public Result readCourseBanner() throws Exception {
        return stCode2SessionService.execute(new WebClient(request));
    }

}
