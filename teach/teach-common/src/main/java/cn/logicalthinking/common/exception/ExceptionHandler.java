package cn.logicalthinking.common.exception;

import cn.logicalthinking.common.base.enums.CODE;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常统一处理拦截器,输出异常信息
 *
 * @author 黄世杰
 * @version 1.0
 */
public class ExceptionHandler implements HandlerExceptionResolver {

	private Logger log = Logger.getLogger(this.getClass());

	public ModelAndView resolveException(HttpServletRequest request,
										 HttpServletResponse response, Object target, Exception ex) {
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView();
		log.error(ex.getMessage(), ex);
		FastJsonJsonView jsonView = new FastJsonJsonView();
		jsonView.setAttributesMap(errMessage(ex));
		jsonView.setContentType("text/html;charset=UTF-8");
		mv.setView(jsonView);

		return mv;
	}

	/**
	 * 在这里判断异常，根据不同的异常返回错误。
	 *
	 * @param ex
	 * @return
	 * @Description
	 * @author 黄世杰
	 * @version 1.0
	 */
	private Map<String, String> errMessage(Exception ex) {
		Map<String, String> errorMap = new HashMap<String, String>();
		if (ex instanceof DataAccessException) {
			errorMap.put("code", CODE.CODE_509.getKey());
			errorMap.put("msg", CODE.CODE_509.getValue());
		}if (ex instanceof DataIntegrityViolationException) {
			errorMap.put("code", CODE.CODE_400.getKey());
			errorMap.put("msg","该数据已被引用，不能删除");
		} else if (ex instanceof NullPointerException) {
			errorMap.put("code", CODE.CODE_501.getKey());
			errorMap.put("msg", CODE.CODE_501.getValue());
		} else if (ex instanceof ClassNotFoundException) {
			errorMap.put("code", CODE.CODE_502.getKey());
			errorMap.put("msg", CODE.CODE_502.getValue());
		} else if (ex instanceof ArithmeticException) {
			errorMap.put("code", CODE.CODE_503.getKey());
			errorMap.put("msg", CODE.CODE_503.getValue());
		} else if (ex instanceof ArrayIndexOutOfBoundsException) {
			errorMap.put("code", CODE.CODE_504.getKey());
			errorMap.put("msg", CODE.CODE_504.getValue());
		} else if (ex instanceof IndexOutOfBoundsException) {
			errorMap.put("code", CODE.CODE_505.getKey());
			errorMap.put("msg", CODE.CODE_505.getValue());
		} else if (ex instanceof IllegalArgumentException) {
			errorMap.put("code", CODE.CODE_506.getKey());
			errorMap.put("msg", CODE.CODE_506.getValue());
		} else if (ex instanceof ClassCastException) {
			errorMap.put("code", CODE.CODE_507.getKey());
			errorMap.put("msg", CODE.CODE_507.getValue());
		} else if (ex instanceof SecurityException) {
			errorMap.put("code", CODE.CODE_508.getKey());
			errorMap.put("msg", CODE.CODE_508.getValue());
		} else if (ex instanceof SQLException) {
			errorMap.put("code", CODE.CODE_519.getKey());
			errorMap.put("msg", CODE.CODE_519.getValue());
		} else if (ex instanceof NoSuchMethodException) {
			errorMap.put("code", CODE.CODE_510.getKey());
			errorMap.put("msg", CODE.CODE_510.getValue());
		} else if (ex instanceof NumberFormatException) {
			errorMap.put("code", CODE.CODE_511.getKey());
			errorMap.put("msg", CODE.CODE_511.getValue());
		} else if (ex instanceof MaxUploadSizeExceededException) {
			errorMap.put("code", CODE.CODE_512.getKey());
			errorMap.put("msg", CODE.CODE_512.getValue());
		} else if (ex instanceof FileNotFoundException) {
			errorMap.put("code", CODE.CODE_513.getKey());
			errorMap.put("msg", CODE.CODE_513.getValue());
		} else if (ex instanceof SocketTimeoutException
				|| ex instanceof ConnectException) {
			errorMap.put("code", CODE.CODE_514.getKey());
			errorMap.put("msg", CODE.CODE_514.getValue());
		} else if (ex instanceof IOException) {
			errorMap.put("code", CODE.CODE_515.getKey());
			errorMap.put("msg", CODE.CODE_515.getValue());
		}else if (ex instanceof PermissionException) {
			errorMap.put("code", CODE.CODE_300.getKey());
			errorMap.put("msg", ex.getMessage());
		} else if (ex instanceof SysException) {
			errorMap.put("code", CODE.CODE_400.getKey());
			errorMap.put("msg", ex.getMessage());
		} else {
			errorMap.put("code", CODE.CODE_500.getKey());
			errorMap.put("msg","系统异常");
//			errorMap.put("msg",ex.getMessage());
		}
		return errorMap;
	}
}
