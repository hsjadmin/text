package cn.logicalthinking.models.manage.intercept;

import cn.logicalthinking.common.dao.SysUserDao;
import cn.logicalthinking.common.entity.SysUser;
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
 * 后台管理 拦截器
 *
 * @author 黄世杰
 * @version 1.0
 * @Description
 * @2018-8-24
 */
public class ManagerInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(ManagerInterceptor.class);

    @Resource
    private SysUserDao sysUserDao;

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
        SysUser user = sysUserDao.selectSysUserById(claims.getId());
        if (user == null) {
            errorMsg(response, "用户不存在");
            return false;
        }
        if (!"0".equals(user.getState())) {
            errorMsg(response, "用户已被禁用");
            return false;
        }
        if ("1".equals(user.getState())) {
            errorMsg(response, "该用户已被永久禁用");
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
                "/manage/adminLogin",
                "/manage/order/course/export",
                "/manage/sysLog/except",
                "/manage/order/course/export",
                "/manage/doubts/exceptDoubts",
        };
        String uri = request.getRequestURI();
        boolean doExclude = false;
        for (String s : exclude) {
            if (uri.indexOf(s) != -1) {
                doExclude = true;
                break;
            }
        }
        return doExclude;
    }
}