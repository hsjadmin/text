package cn.logicalthinking.models.student.intercept;

import cn.logicalthinking.common.cache.RedisManager;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FrequentlyAccessInterceptor implements HandlerInterceptor {

    public static Logger logger = Logger.getLogger(FrequentlyAccessInterceptor.class);

    @Resource
    private RedisManager redisManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("《《《《《《《《《《防止频繁请求开始拦截》》》》》》》》》》" + request.getRequestURI());
        System.out.println("《《《《《《《《《《防止频繁请求开始拦截》》》》》》》》》》" + request.getRequestURI());
        String token = request.getHeader("token");
        String requestURI = request.getRequestURI();
        String cachedRequestURI = redisManager.hget(token, "requestURI");
        if (StringUtils.isNotBlank(cachedRequestURI) && cachedRequestURI.equals(requestURI)) {
            logger.info("《《《《《《《《《《请求过于频繁》》》》》》》》》》");
            errorMsg(response, "请求过于频繁，请" + 5 + "秒后才操作");
            return false;
        }
        redisManager.hset(token, "requestURI", requestURI, 5);
        logger.info("《《《《《《《《《《防止频繁请求结束拦截》》》》》》》》》》");
        return true;
    }

    public void errorMsg(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8"); //避免IE出现下载JSON文件的情况
        out.print("{\"code\": 400, \"msg\":\"" + msg + "！\"}");
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
