package cn.logicalthinking.models.teacher;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.dao.CourseOutlineDao;
import cn.logicalthinking.common.dao.CourseTypeDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.entity.CourseOutline;
import cn.logicalthinking.common.entity.CourseType;
import cn.logicalthinking.common.util.CheckSumBuildr;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @note
 * @auhtor 卢祖飞
 * @date 2019/1/8,13:38
 */
@RestController
@RequestMapping("/route")
public class TeacherRouteController {

    @Resource
    private CourseOutlineDao courseOutlineDao;

    @Resource
    private CourseTypeDao courseTypeDao;

    @Resource
    private CourseDao courseDao;

    public static final Logger logger = LoggerFactory.getLogger(TeacherRouteController.class);
    // 需要改成自身应用的appSecret
    private final String appSecret = "26326c1782d0";
    @RequestMapping(value = {"/mockClient"}, method = {RequestMethod.POST})
    @ResponseBody
    public JSONObject mockClient(HttpServletRequest request)
            throws Exception {
        JSONObject result = new JSONObject();
        try {
            // 获取请求体
            byte[] body = readBody(request);
            if (body == null) {
                logger.warn("request wrong, empty body!");
                result.put("code", 414);
                return result;
            }
            // 获取部分request header，并打印
            String ContentType = request.getContentType();
            String AppKey = request.getHeader("AppKey");
            String CurTime = request.getHeader("CurTime");
            String MD5 = request.getHeader("MD5");
            String CheckSum = request.getHeader("CheckSum");
            logger.info("request headers: ContentType = {}, AppKey = {}, CurTime = {}, " +
                    "MD5 = {}, CheckSum = {}", ContentType, AppKey, CurTime, MD5, CheckSum);
            // 将请求体转成String格式，并打印
            String requestBody = new String(body, "utf-8");
            //解析返回内容
            JSONObject jsonObject = JSONObject.parseObject(requestBody);
            //返回状态为6
            updateUrl(jsonObject);
            logger.info("request body = {}", requestBody);
            // 获取计算过的md5及checkSum
            String verifyMD5 = CheckSumBuildr.getMD5(requestBody);
            String verifyChecksum = CheckSumBuildr.getCheckSum(appSecret, verifyMD5, CurTime);
            logger.debug("verifyMD5 = {}, verifyChecksum = {}", verifyMD5, verifyChecksum);
            // TODO: 比较md5、checkSum是否一致，以及后续业务处理
            result.put("code", 200);
            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            result.put("code", 414);
            return result;
        }
    }
    private byte[] readBody(HttpServletRequest request) throws IOException {
        if (request.getContentLength() > 0) {
            byte[] body = new byte[request.getContentLength()];
            IOUtils.readFully(request.getInputStream(), body);
            return body;
        } else
            return null;
    }
    //修改直播视频地址
    private void updateUrl(JSONObject jsonObject){
        if("6".equals(jsonObject.get("eventType"))){
            String fileInfo = jsonObject.get("fileinfo").toString();
            JSONArray jsonArray = JSONObject.parseArray(fileInfo);

            JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(0).toString());
            //频道id
            String channelid = jsonObject1.get("channelid").toString();
            //视频地址
            String url = jsonObject1.get("url").toString();

            Map<String, Object> map = new HashMap<>();
            map.put("channelNo",channelid);
            map.put("videoUrl",url);
            courseOutlineDao.updateUrl(map);
            //通过频道号查询大纲信息
            CourseOutline courseOutline = courseOutlineDao.selectCourseOutlineBychannelNo(channelid);
            //通过大纲查询课程类别
            CourseType courseType = courseTypeDao.selectCourseTypeById(courseOutline.getCourseTypeId());
//            //查询是否存在未直播的课程
//            Map<String, Object> map1 = new HashMap<>();
//            map1.put("courseTypeId",courseOutline.getCourseTypeId());
//            map1.put("status",0);
//            int num = courseOutlineDao.selectCourseOutlineCount(map1);

            //课程id
            Integer courseId = courseType.getCourseId();
            Course course = new Course();
            course.setId(courseId);
            course.setLiveStatus(0);
//            if(num <= 0){
//                course.setIsFinish(1);
//            }
            courseDao.updateCourse(course);
        }
    }
}
