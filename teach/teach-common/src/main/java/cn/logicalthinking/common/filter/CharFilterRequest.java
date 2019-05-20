package cn.logicalthinking.common.filter;

import cn.logicalthinking.common.util.CharUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CharFilterRequest extends HttpServletRequestWrapper {
    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public CharFilterRequest(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getParameter(String name) {
        String str = super.getParameter(name);
        return CharUtil.filterEmoji(str);
//        return CharUtil.filterEmoji(str, "[表情]");
    }
}
