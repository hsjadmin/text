package cn.logicalthinking.models.teacher.intercept;

import cn.logicalthinking.common.base.constant.AppParamConstant;
import cn.logicalthinking.common.base.entity.Result;
import cn.logicalthinking.common.base.enums.CODE;
import cn.logicalthinking.common.dao.ApplicationParameterDao;
import cn.logicalthinking.common.dao.TeacherDao;
import cn.logicalthinking.common.entity.ApplicationParameter;
import cn.logicalthinking.common.entity.Teacher;
import cn.logicalthinking.common.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 老师管理 拦截器
 *
 * @author 黄世杰
 * @version 1.0
 * @Description
 * @2018-8-24
 */
public class TeacherInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(TeacherInterceptor.class);

    @Resource
    private TeacherDao teacherDao;

    @Resource
    private ApplicationParameterDao applicationParameterDao;

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exception)
            throws Exception {

    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,

                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        logger.info("《《《《《《《《《《token拦截》》》》》》》》》》" + request.getRequestURI());
        if (excludeUrl(request))
            return true;
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            errorMsg(response, "登录超时，请重新登录");
            return false;
        }
        Claims claims = JwtUtils.parseJWT(token);
        if (claims == null) {
            errorMsg(response, "登录超时，请重新登录");
            return false;
        }
        Date date = new Date();
        if (date.getTime() > claims.getExpiration().getTime()) {
            errorMsg(response, "登录超时，请重新登录");
            return false;
        }
        Teacher user = teacherDao.selectTeacherById(Integer.parseInt(claims.getId()));
        if (user == null) {
            errorMsg(response, "用户不存在");
            return false;
        }
        if (0 != user.getStatus()) {
            logger.info("《《《《《《《《《《token拦截结束》》》》》》》》》》");
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
        logger.info("《《《《《《《《《《token拦截结束》》》》》》》》》》");
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
                "/teacher/teacherLogin/sendCode",
                "/teacher/teacherLogin/login",
                "/teacher/teacherLogin/perfectInfo",
                "/teacher/teacherLogin/getTeacherInfos",
                "/teacher/teacherGetLGSS/getLGSS",
                "/teacher/share/shareCourse",
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
        if (uri.toLowerCase().contains("payCallBack".toLowerCase())) {
            return true;
        }
        return doExclude;
    }
}