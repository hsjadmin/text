package cn.logicalthinking.common.interceptors;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class RRInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(RRInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取所有的请求参数
        JSONObject jsonObject = new JSONObject();
        Enumeration<String> paraNames = request.getParameterNames();
        for (Enumeration<String> e = paraNames; e.hasMoreElements(); ) {
            String thisName = e.nextElement();
            String thisValue = request.getParameter(thisName);
            jsonObject.put(thisName, thisValue);
        }

        // 获取请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        JSONObject jsonObjectH = new JSONObject();
        for (Enumeration<String> e = headerNames; e.hasMoreElements(); ) {
            String thisName = e.nextElement();
            String thisValue = request.getHeader(thisName);
            jsonObjectH.put(thisName, thisValue);
        }

        logger.info("");
        logger.info("==========================请求信息==========================");
        logger.info("ip：" + request.getRemoteAddr());
        logger.info("uri：" + request.getRequestURI());
        logger.info("headers：" + jsonObjectH);
        logger.info("params：" + jsonObject);
        logger.info("==========================请求信息===========================");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        // 获取请求头
//        JSONObject jsonObjectH = new JSONObject();
//        Collection<String> headerNames = response.getHeaderNames();
//        for (String headerName : headerNames) {
//            jsonObjectH.put(headerName, response.getHeader(headerName));
//        }
//        // 响应内容
//        JSONObject jsonObject = new JSONObject();
//
//        logger.info("==========================响应信息==========================");
//        logger.info("headers：" + jsonObjectH);
//        logger.info("==========================响应信息==========================");
//        logger.info("");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("==========================结束===========================");
//        logger.info("");
    }

}
