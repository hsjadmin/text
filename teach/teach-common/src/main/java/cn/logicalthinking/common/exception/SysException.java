package cn.logicalthinking.common.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * @Description 系统自定义异常
 * @author 黄世杰
 * @date   2018-12-19
 * @version  1.0
 */
public class SysException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	protected Exception exception;
	protected String message;
	
	public SysException(Exception exception) {
		this.exception = exception;
	}

	public SysException(String message) {
		this.message = message;
	}

	public SysException(Exception exception,String message) {

		this.exception = exception;
		this.message = message;
	}

	public String getSimpleMessage() {
		if (this.exception != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(out);
			this.exception.printStackTrace(ps); //打印到控制台错误信息
		}
		if(message==null)
			return exception.getMessage();
		else
			return message;
	}

	@Override
	public String getMessage() {
		String exMsg = "";
		if (this.exception != null) {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(out);
			this.exception.printStackTrace(ps); //打印到控制台错误信息
			byte[] bytes = out.toByteArray();

			try {
				exMsg = new String(bytes,"\r\t");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				exMsg = new String(bytes);
			}
		}
		if (this.message != null && this.message != "") {
			if(exMsg==null||exMsg=="")
				exMsg = this.message;
			else
				exMsg = this.message +":"+ exMsg;
		}
		return exMsg;
	}
	
	@Override
	public String getLocalizedMessage() {
		if (exception != null)
			return exception.getLocalizedMessage();
		return super.getLocalizedMessage();
	}

	@Override
	public synchronized Throwable getCause() {
		if (exception != null)
			return exception.getCause();
		return super.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		if (exception != null)
			return exception.initCause(cause);
		return super.initCause(cause);
	}

	@Override
	public void printStackTrace() {
		if (exception != null)
			exception.printStackTrace();
		super.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		if (exception != null)
			exception.printStackTrace(s);
		super.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		if (exception != null)
			exception.printStackTrace(s);
		super.printStackTrace(s);
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		if (exception != null)
			return exception.getStackTrace();
		return super.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		if (exception != null)
			exception.setStackTrace(stackTrace);
		super.setStackTrace(stackTrace);
	}

}
