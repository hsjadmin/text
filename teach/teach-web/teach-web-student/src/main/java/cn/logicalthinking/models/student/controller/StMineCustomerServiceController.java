package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】客服
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/cs")
@Api(tags = "【学生】客服")
public class StMineCustomerServiceController extends BaseController {

    @Resource(name = "stGetAppParamService")
    private AbstractService stGetAppParamService;

    /**
     * @throws Exception
     * @Description 查询客服电话
     * @author xhx
     * @version 1.0
     * @date 2018-12-25
     */
    @RequestMapping(value = "/getCSPhone")
    @ApiOperation(value = "查询客服电话", response = Result.class, httpMethod = "POST")
    public Result stGetMineInfoService() throws Exception {
        return stGetAppParamService.execute(new WebClient(request));
    }
}