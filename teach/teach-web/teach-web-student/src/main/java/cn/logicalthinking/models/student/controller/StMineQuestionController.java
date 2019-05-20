package cn.logicalthinking.models.student.controller;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
import cn.logicalthinking.common.base.service.WebClient;
import cn.logicalthinking.common.entity.QuestionComment;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author xhx
 * @version 1.0
 * @Description 【学生】登录
 * @date 2018-12-19
 */
@RestController
@RequestMapping("/student/mine/question")
@Api(tags = "【学生】我的-我的答疑")
public class StMineQuestionController extends BaseController {

    @Resource(name = "stGetMineQuestionListService")
    private AbstractService stGetMineQuestionListService;

    @Resource(name = "stAddQuestionCommentService")
    private AbstractService stAddQuestionCommentService;

    /**
     * @throws Exception
     * @Description 查询我的答疑
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/getMineQuestionList")
    @ApiOperation(value = "查询我的答疑", response = Result.class, httpMethod = "POST")
    public Result getMineQuestionList() throws Exception {
        return stGetMineQuestionListService.execute(new WebClient(request));
    }


    /**
     * swagger 测试参数需要加上   @RequestBody
     *
     * @return Result
     * @throws Exception
     * @Description 解疑评论表添加
     * @author xhx
     * @version 1.0
     * @date 2018-12-19
     */
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单id", name = "orderId", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "星数", name = "star", paramType = "query", defaultValue = "10"),
            @ApiImplicitParam(value = "留言", name = "comment", paramType = "query", defaultValue = "10")
    })
    @RequestMapping(value = "/addQuestionComment")
    @Transactional
    @ApiOperation(value = "解疑评论表添加", httpMethod = "POST", response = Result.class)
    @ServiceLogAnn(OPType = "添加", operationName = "添加解疑评论表", url = "student/mine/question/addQuestionComment")
    public Result addQuestionComment(@ApiIgnore QuestionComment entity) throws Exception {
        return stAddQuestionCommentService.execute(new WebClient(request, entity));
    }

}