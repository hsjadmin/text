package cn.logicalthinking.models.manage.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @note
 * @auhtor 肖宏鑫
 * @date 2018-11-22
 */
@RestController
@RequestMapping("/manage/sysLog")
@Api(tags = "【后台】--系统日志")
public class MaSysLogController extends BaseController {

    @Resource(name = "maGetSysLogService")
    private AbstractService maGetSysLogService;

    @Resource(name = "exceptLogService")
    private AbstractService exceptLogService;

    /**
    *@note 查询系统日志
     *
    *@auhtor 肖宏鑫
    *@date 2018-11-22
    *@version 1.0
    */
    @RequestMapping("/list")
    @ApiOperation(value = "查询系统日志",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    public Result list() throws Exception{
        return maGetSysLogService.execute(new WebClient(request));
    }

    /**
    *@note 导出系统日志
    *@auhtor 卢祖飞
    *@date 2019/1/4,14:15
    *@version 1.0
    */
    @RequestMapping("/except")
    @ApiOperation(value = "导出系统日志",httpMethod = "POST",response = Result.class)
    @Transactional
    @ServiceLogAnn(OPType = "导出",operationName = "导出系统日志",url = "manage/sysLog/except")
    public Result except(HttpServletResponse response) throws Exception{
        return exceptLogService.execute(new WebClient(request,response));
    }


}
