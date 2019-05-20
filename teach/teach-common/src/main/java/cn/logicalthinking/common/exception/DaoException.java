package cn.logicalthinking.common.exception;
/**
 *  dao层自定义异常
 * @author 黄世杰
 * @date   2018-12-19
 */
public class DaoException extends SysException {

	private static final long serialVersionUID = 1L;

	public DaoException(Exception exception, String code) {
		super(exception, code);
	}
	
	public DaoException(String code){
		super(code);
	}
}
