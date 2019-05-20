package cn.logicalthinking.models.teacher;

import cn.logicalthinking.common.base.annotation.ServiceLogAnn;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.BaseController;
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
 * @date 2018/12/26,11:47
 */
@RestController
@RequestMapping("/teacher/teacherCourses")
@Api(tags = "【教师端】-我的课程")
public class TeacherCoursesController extends BaseController {

    @Resource(name = "getTeacherCoursesService")
    private AbstractService getTeacherCoursesService;

    @Resource(name = "getCourseInfoService")
    private AbstractService getCourseInfoService;

    @Resource(name = "updateCourseShowTypeService")
    private AbstractService updateCourseShowTypeService;

    @Resource(name = "getCourseCommentService")
    private AbstractService getCourseCommoentService;

    @Resource(name = "getCourseListService")
    private AbstractService getCourseListService;

    @Resource(name = "getBuyCourseStudentService")
    private AbstractService getBuyCourseStudentService;

    @Resource(name = "isExistRoomService")
    private AbstractService isExistRoomService;

    @Resource(name = "sendMessagePushService")
    private AbstractService sendMessagePushService;

    @Resource(name = "keepVideoUrlService")
    private AbstractService keepVideoUrlService;

    @Resource(name = "getCourseCommentCountService")
    private AbstractService getCourseCommentCountService;

    @Resource(name = "setUpchannelNoService")
    private AbstractService setUpchannelNoService;

    @Resource(name = "updateCourseStatusService")
    private AbstractService updateCourseStatusService;

    /**
    *@note 查询老师的课程
    *@auhtor 卢祖飞
    *@date 2018/12/26,11:59
    *@version 1.0
    */
    @RequestMapping("/getTeacherCourse")
    @ApiOperation(value = "查询老师的课程",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "pageIndex", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(value = "总条数", name = "pageSize", paramType = "query", defaultValue = "10")
    })
    public Result getTeacherCourse() throws Exception{
        return getTeacherCoursesService.execute(new WebClient(request));
    }

    /**
     *@note 查询课程详情
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getCourseInfo")
    @ApiOperation(value = "查询课程详情",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "课程id",name = "cId",paramType = "query")
    public Result getCourseInfo() throws Exception{
        return getCourseInfoService.execute(new WebClient(request));
    }


    /**
     *@note 修改是否显示状态
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/updateIsShow")
    @ApiOperation(value = "修改是否显示状态",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "cId",paramType = "query"),
            @ApiImplicitParam(value = "是否显示状态",name = "isShow",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改显示状态",url = "/teacher/teacherCourses/updateIsShow")
    public Result updateIsShow()throws Exception{
        return updateCourseShowTypeService.execute(new WebClient(request));
    }
    /**
     *@note 修改异常的上课状态
     *@auhtor xhx
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/updateCourseStatus")
    @ApiOperation(value = "修改异常的上课状态",httpMethod = "POST",response = Result.class)
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改异常的上课状态",url = "/teacher/teacherCourses/updateCourseStatus")
    public Result updateCourseStatusService()throws Exception{
        return updateCourseStatusService.execute(new WebClient(request));
    }

    /**
     *@note 查看课程评论
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getCourseComment")
    @ApiOperation(value = "查看课程评论",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "cId",paramType = "query"),
            @ApiImplicitParam(value = "评论等级",name = "type",paramType = "query"),
            @ApiImplicitParam(value = "起始页",name = "pageIndex",paramType = "query"),
            @ApiImplicitParam(value = "每页显示条数",name = "pageSize",paramType = "query")
    })
    public Result getCourseComment() throws Exception{
        return getCourseCommoentService.execute(new WebClient(request));
    }

    /**
     *@note 查询评论条数
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getCourseCommentCount")
    @ApiOperation(value = "查询评论条数",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "cId",paramType = "query"),
    })
    public Result getCourseCommentCount()throws Exception{
        return getCourseCommentCountService.execute(new WebClient(request));
    }

    /**
     *@note 查看课程大纲
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getCourseOutLine")
    @ApiOperation(value = "查看课程大纲",httpMethod = "POST",response = Result.class)
    @ApiImplicitParam(value = "课程类别id",name = "courseTypeId",paramType = "query")
    public Result getCourseOutLine() throws Exception{
        return getCourseListService.execute(new WebClient(request));
    }


    /**
     *@note 查询购买课程的学生
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/getBuyCourseStudent")
    @ApiOperation(value = "查询购买课程的学生",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "课程id",name = "cId",paramType = "query"),
            @ApiImplicitParam(value = "大纲id",name = "coId",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "添加",operationName = "添加消息",url = "/teacher/teacherCourses/getBuyCourseStudent")
    public Result getBuyCourseStudent() throws Exception{
        return getBuyCourseStudentService.execute(new WebClient(request));
    }

    /**
     *@note 创建房间号
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/isExistRoom")
    @ApiOperation(value = "创建房间号",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "大纲id",name = "id",paramType = "query"),
            @ApiImplicitParam(value = "课程类型id",name = "courseTypeId",paramType = "query")
    })
    public Result isExistRoom() throws Exception{
        return isExistRoomService.execute(new WebClient(request));
    }

    /**
     *@note 发送消息
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/sendMessagePush")
    @ApiOperation(value = "发送消息",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "发送类型",name = "sendType",paramType = "query"),
            //发送普通消息
            @ApiImplicitParam(value = "发送者accid",name = "from",paramType = "query"),
            @ApiImplicitParam(value = "消息类型(0：点对点个人消息，1：群消息)",name = "ope",paramType = "query"),
            @ApiImplicitParam(value = "接收人accid",name = "to",paramType = "query"),
            //批量发送点对点消息
            @ApiImplicitParam(value = "发送者accid",name = "fromAccid",paramType = "query"),
            @ApiImplicitParam(value = "接受者的accid(格式为JSONArray,例:[\"aaa\",\"bbb\"])",name = "toAccids",paramType = "query"),
            @ApiImplicitParam(value = "是否需要返回消息ID",name = "returnMsgid",paramType = "query"),
            //公共消息内容
            @ApiImplicitParam(value = "0 表示文本消息,1 表示图片，2 表示语音，3 表示视频，4 表示地理位置信息，6 表示文件，100 自定义消息类型",name = "type",paramType = "query"),
            @ApiImplicitParam(value = "消息内容",name = "body",paramType = "query")
    })
    public Result sendMessagePush() throws Exception{
        return sendMessagePushService.execute(new WebClient(request));
    }

    /**
     *@note 保存录制视频路径
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/keepVideoUrl")
    @ApiOperation(value = "保存录制视频路径(下课)",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "大纲id",name = "id",paramType = "query"),
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "修改视频路径",url = "/teacher/teacherCourses/keepVideoUrl")
    public Result keepVideoUrl() throws Exception{
        return keepVideoUrlService.execute(new WebClient(request));
    }

    /**
     *@note 保存返回的频道号
     *@auhtor 卢祖飞
     *@date 2018/12/26,11:59
     *@version 1.0
     */
    @RequestMapping("/setUpchannelNo")
    @ApiOperation(value = "保存返回的频道号",httpMethod = "POST",response = Result.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "大纲id",name = "id",paramType = "query"),
            @ApiImplicitParam(value = "频道号",name = "channelNo",paramType = "query")
    })
    @Transactional
    @ServiceLogAnn(OPType = "修改",operationName = "保存返回的频道号",url = "/teacher/teacherCourses/setUpchannelNo")
    public Result setUpchannelNo() throws Exception{
        return setUpchannelNoService.execute(new WebClient(request));
    }

}
