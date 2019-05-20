package cn.logicalthinking.common.exception;
/**
 * @Description 数据校验自定义异常
 * @author 黄世杰 
 * @date   2018-12-19
 * @version  1.0
 */
public class ValidataException extends SysException {

	private static final long serialVersionUID = 1L;

	public ValidataException(String message) {
		super(message);
	}
}
