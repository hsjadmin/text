package cn.logicalthinking.common.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author xhx
 * @version 1.0
 * @Description 过滤器  过滤字符串
 * @2018-6-4
 */
@WebFilter(urlPatterns = "/*")
public class CharFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest hreq = (HttpServletRequest) servletRequest;

        CharFilterRequest charFilterRequest = new CharFilterRequest(hreq);

        // Filter 只是链式处理，请求依然转发到目的地址。  
        filterChain.doFilter(charFilterRequest, servletResponse);
    }

    public void destroy() {

    }
}  