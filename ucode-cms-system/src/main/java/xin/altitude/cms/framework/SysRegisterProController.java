package xin.altitude.cms.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.auth.controller.BaseProController;
import xin.altitude.cms.auth.web.service.SysRegisterService;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.StringUtil;
import xin.altitude.cms.framework.config.CmsConfig;
import xin.altitude.cms.framework.core.model.RegisterBody;
import xin.altitude.cms.system.service.ISysConfigService;

/**
 * 注册验证
 *
 * @author ucode
 */
@ResponseBody
@RequestMapping(CmsConfig.UNIFORM_PREFIX)
public class SysRegisterProController extends BaseProController {
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
        return StringUtil.isEmpty(msg) ? success() : error(msg);
    }
}