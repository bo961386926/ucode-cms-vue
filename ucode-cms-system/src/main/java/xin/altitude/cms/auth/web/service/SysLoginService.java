package xin.altitude.cms.auth.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import xin.altitude.cms.auth.manager.AsyncManager;
import xin.altitude.cms.auth.manager.factory.AsyncFactory;
import xin.altitude.cms.auth.model.LoginUser;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.ServletUtils;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.config.CmsConfig;
import xin.altitude.cms.framework.core.domain.SysUser;
import xin.altitude.cms.framework.core.redis.RedisCache;
import xin.altitude.cms.framework.exception.ServiceException;
import xin.altitude.cms.framework.exception.user.CaptchaException;
import xin.altitude.cms.framework.exception.user.CaptchaExpireException;
import xin.altitude.cms.framework.exception.user.UserPasswordNotMatchException;
import xin.altitude.cms.framework.util.DateUtils;
import xin.altitude.cms.framework.util.MessageUtils;
import xin.altitude.cms.framework.util.ip.IpUtils;
import xin.altitude.cms.system.service.ISysConfigService;
import xin.altitude.cms.system.service.ISysUserService;

import javax.annotation.Resource;

/**
 * 登录校验方法
 *
 * @author ucode
 */
// @Component
public class SysLoginService {
    @Autowired
    private CmsTokenService cmsTokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        /* 验证码开关 */
        boolean captchaOnOff = SpringUtils.getBean(CmsConfig.class).getCms().isCaptchaEnabled();
        if (captchaOnOff) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return cmsTokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
