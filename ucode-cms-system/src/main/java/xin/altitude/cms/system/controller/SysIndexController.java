package xin.altitude.cms.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.util.StringUtils;

/**
 * 首页
 *
 * @author ucode
 */
@ResponseBody
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private CmsConfig cmsConfig;
    
    /**
     * 访问首页，提示语
     */
    @RequestMapping("/")
    public String index() {
        return StringUtils.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cmsConfig.getCms().getName(), cmsConfig.getCms().getVersion());
    }
}
