package xin.altitude.cms.framework.exception.user;

import xin.altitude.cms.framework.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author ucode
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;
    
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
