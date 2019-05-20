package cn.logicalthinking.common.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
/**
 * @Description  过滤器  过滤请求  解决跨域问题
 * @author 黄世杰
 * @2018-6-4
 * @version  1.0
 */
public class CORSFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) servletResponse;
		// 解决跨越问题
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods","*");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,token");
		// 允许跨域请求中携带cookie
		//response.setHeader("Access-Control-Allow-Credentials", "true");
		 // 配置options的请求返回

		HttpServletRequest hreq = (HttpServletRequest) servletRequest;

        if (hreq.getMethod().equals("OPTIONS")) {

        	response.setStatus(HttpStatus.SC_OK);
            // hresp.setContentLength(0);  
        	response.getWriter().write("OPTIONS returns OK");

            return;

        }

        // Filter 只是链式处理，请求依然转发到目的地址。  
		filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}  