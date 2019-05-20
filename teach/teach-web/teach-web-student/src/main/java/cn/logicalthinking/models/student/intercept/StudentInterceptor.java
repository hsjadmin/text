package cn.logicalthinking.models.student.intercept;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.constant.RedisConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.cache.RedisManager;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.StudentDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.Student;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * 学生管理 拦截器
 *
 * @author 黄世杰
 * @version 1.0
 * @Description
 * @2018-8-24
 */

public class StudentInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(StudentInterceptor.class);

    @Resource
    private StudentDao studentDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    @Resource
    private RedisManager redisManager;

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,

                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {


        /*分享连接*/
        String key = request.getParameter("key");
        if (StringUtils.isNotBlank(key)) {

            String cId = request.getParameter("cId");
            String cacheKey = redisManager.get(RedisConstant.COURSE_SHARE + cId);
            if (Objects.equals(cacheKey, key)) {
                return true;
            }
        }

        logger.info("《《《《《《《《《《token拦截》》》》》》》》》》" + request.getRequestURI());
        if (excludeUrl(request))
            return true;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            errorMsg(response, "登录超时，请重新登录");
            return false;
        }
        Student user = studentDao.selectStudentByOpenId(token);
        if (user == null) {
            errorMsg(response, "用户不存在");
            return false;
        }
        if (0 != user.getStatus()) {
            logger.info("《《《《《《《《《《token拦截结束》》》》》》》》》》" + request.getRequestURI());
            ApplicationParameter parameter = applicationParameterDao.selectApplicationParameterByName(AppParamConstant.CUSTOMER_SERVICE_PHONE);
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8"); //避免IE出现下载JSON文件的情况
            Result result = Result.jsonMsg(CODE.CODE_305.getKey(), "用户已被禁用", "phone", parameter.getValue());
            out.print(result.toJSONString());
            out.flush();
            out.close();
            return false;
        }
        logger.info("《《《《《《《《《《token拦截结束》》》》》》》》》》" + request.getRequestURI());
        return true;
    }

    public void errorMsg(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8"); //避免IE出现下载JSON文件的情况
        out.print("{\"code\": 300, \"msg\":\"" + msg + "！\"}");
        out.flush();
        out.close();
    }


    /**
     * 设置不拦截的url
     */
    private boolean excludeUrl(HttpServletRequest request) {
        String[] exclude = {
                "/student/wx/code2Session",
                "/student/login/getOrAddStudentByOpenId",
                "/student/home/getMarketingBannerList",
                "/student/home/getMarketingVideoList",
                "/student/base/lgs",
                "/student/course/getMarketingCourseList",
                "/student/course/getTeacherList",
                "/student/question/getTeacherList",
                "/student/question/getMarketingQuestionList",
                "/student/common/upLoadFile",
                "/student/course/stGetCourseById",
        };
        String uri = request.getRequestURI();
        boolean doExclude = false;
        for (String s : exclude) {
            if (uri.indexOf(s) != -1) {
                doExclude = true;
                break;
            }
        }
        // 支付回调放行
        if (uri.toLowerCase().contains("payCallback".toLowerCase())) {
            return true;
        }
        return doExclude;
    }
}