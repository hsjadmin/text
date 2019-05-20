package cn.logicalthinking.common.base.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 基础工具控制器
 * 
 * @author 黄世杰
 * @version 1.0
 * @date 2018-12-19
 */
public class BaseController {
	protected HttpSession session;
	protected HttpServletRequest request;
	
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
	}
	
	@Resource
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Resource
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * 获取分页参数
	 */
	protected Map<String, Object> initMap(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String pageIndex = request.getParameter("pageIndex");
		if (StringUtils.isNotBlank(pageIndex)) {
			map.put("pageIndex", Integer.parseInt(pageIndex));
		}

		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isNotBlank(pageSize)) {
			map.put("pageSize", Integer.parseInt(pageSize));
		}

		String sortname = request.getParameter("sortname");
		if (StringUtils.isNotBlank(sortname)) {
			map.put("sortname", sortname);
		}

		String sortorder = request.getParameter("sortorder");
		if (StringUtils.isNotBlank(sortorder)) {
			map.put("sortorder", sortorder);
		}
		return map;
	}

}