package cn.logicalthinking.common.exception;

/**
 * 业务异常
 */
public class BusinessException extends SysException {

    public BusinessException(Exception exception) {
        super(exception);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Exception exception, String message) {
        super(exception, message);
    }
}
