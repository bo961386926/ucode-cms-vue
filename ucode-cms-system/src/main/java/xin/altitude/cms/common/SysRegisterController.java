package xin.altitude.cms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.auth.controller.BaseController;
import xin.altitude.cms.auth.web.service.SysRegisterService;
import xin.altitude.cms.common.core.domain.AjaxResult;
import xin.altitude.cms.common.model.RegisterBody;
import xin.altitude.cms.common.utils.StringUtils;
import xin.altitude.cms.system.service.ISysConfigService;

/**
 * 注册验证
 *
 * @author ucode
 */
@RestController
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;
    
    @Autowired
    private ISysConfigService configService;
    
    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }
}
