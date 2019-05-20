package cn.logicalthinking.models.teacher.service;

import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.service.AbstractService;
import cn.logicalthinking.common.base.service.IClientData;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.dao.CourseDao;
import cn.logicalthinking.common.entity.Course;
import cn.logicalthinking.common.exception.BusinessException;
import cn.logicalthinking.common.pay.WxProp4Student;
import cn.logicalthinking.common.util.DocumentPathParsing;
import cn.logicalthinking.common.util.HttpsRequest;
import cn.logicalthinking.common.util.ParamValidation;
import cn.logicalthinking.common.util.Tools;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

/**
 * @note 查看老师课程
 * @auhtor xhx
 * @date 2018/12/26,11:49
 */
@Service
public class TeacherShareCoursesService extends AbstractService {

    @Resource
    private CourseDao courseDao;

    @Resource
    private RedisManager redisManager;

    private IClientData data;

    @Override
    protected Result doWork(IClientData data) throws Exception {

        this.data = data;

        String courseId = data.getParameter("courseId");

        ParamValidation.isNotNull(courseId, "课程id不能为空");

//        Course course = courseDao.selectCourseById(Integer.valueOf(courseId));
//        if (course == null) {
//            throw new BusinessException("没有该课程");
//        }
        // 校验是否为该老师的课程
//        Integer teacherId = course.getTeacherId();
//        String teacherUserId = data.getTeacherUserId();
//        if (!Objects.equals(teacherId, teacherUserId)) {
//            throw new BusinessException("没有权限分享");
//        }

        String key = redisManager.get(RedisConstant.COURSE_SHARE + courseId);
        if (StringUtils.isBlank(key)) {
            key = Tools.UUID().substring(0, 10);
            redisManager.set(RedisConstant.COURSE_SHARE + courseId, key);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("courseId", courseId);
        jsonObject.put("key", key);

        return Result.jsonData("200", jsonObject);
    }

//    public String AA(String courseId) throws BusinessException {
//        OutputStream os = null;
//        try {
//
//            String url1 = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + WxProp4Student.MP_ID + "&secret=" + WxProp4Student.MP_SECRET;
//            JSONObject jsonObject = HttpsRequest.httpsRequest(url1, "GET", "");
//            System.out.println(jsonObject.toJSONString());
//            String access_token = jsonObject.getString("access_token");
//            URL url = new URL("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + access_token);
//            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
//            httpURLConnection.setRequestMethod("POST");// 提交模式
//            // conn.setConnectTimeout(10000);//连接超时 单位毫秒
//            // conn.setReadTimeout(2000);//读取超时 单位毫秒
//            // 发送POST请求必须设置如下两行
//            httpURLConnection.setDoOutput(true);
//            httpURLConnection.setDoInput(true);
//            // 获取URLConnection对象对应的输出流
//            PrintWriter printWriter = new PrintWriter(httpURLConnection.getOutputStream());
//            // 发送请求参数
//            JSONObject paramJson = new JSONObject();
//
//            String key = redisManager.get(RedisConstant.COURSE_SHARE + courseId);
//            if (StringUtils.isBlank(key)) {
//                key = Tools.UUID().substring(0, 10);
//                redisManager.set(RedisConstant.COURSE_SHARE + courseId, key);
//            }
//            paramJson.put("scene", courseId + "_" + key);
//            paramJson.put("page", "pages/index/index");
//            paramJson.put("width", 430);
//            paramJson.put("auto_color", true);
//            /**
//             * line_color生效
//             * paramJson.put("auto_color", false);
//             * JSONObject lineColor = new JSONObject();
//             * lineColor.put("r", 0);
//             * lineColor.put("g", 0);
//             * lineColor.put("b", 0);
//             * paramJson.put("line_color", lineColor);
//             * */
//
//            printWriter.write(paramJson.toString());
//            // flush输出流的缓冲
//            printWriter.flush();
//            //开始获取数据
//            BufferedInputStream bis = new BufferedInputStream(httpURLConnection.getInputStream());
//            String fullPath = DocumentPathParsing.getFullPath();
//            String filename = Tools.UUID() + ".png";
//            os = new FileOutputStream(new File(fullPath, filename));
//            int len;
//            byte[] arr = new byte[1024];
//            while ((len = bis.read(arr)) != -1) {
//                os.write(arr, 0, len);
//                os.flush();
//            }
//            os.close();
//            return filename;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new BusinessException("分享异常");
//        } finally {
//            IOUtils.close(os);
//        }
//    }

    public static void main(String[] args) throws Exception {
//        AA("aaa");
//
//        try {
//            throw new Exception("aaa");
//        } catch (Exception e) {
//            throw new Exception("bbb");
//        } finally {
//            System.out.println("finally");
//            throw new Exception("finally");
////            return;
//        }

//		String url1="https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+jsonObject.getString("access_token");
//		Map<String,Object> map=new HashMap<String, Object>();
//		map.put("path", "pages/index/index?id=1213414413121");
//		 map.put("width", "430");//图片大小
//		JSONObject json=JSONObject.fromObject(map);
//		System.out.println(json.toString());
//		JSONObject result=HttpsRequest.httpsRequest(url1, "POST",json.toString());
//		System.out.println(result);
    }
}
