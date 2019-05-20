package cn.logicalthinking.common.exception;


/**
 * 用户权限自定义异常
 * @Description
 * @author 黄世杰 
 * @2018-8-24
 * @version  1.0
 */
public class PermissionException extends SysException {

	private static final long serialVersionUID = 1L;
	
	public PermissionException(String message) {
		super(message);
	}
}
