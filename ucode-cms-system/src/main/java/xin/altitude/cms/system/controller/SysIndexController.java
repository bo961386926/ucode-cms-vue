package xin.altitude.cms.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import xin.altitude.cms.common.util.StringUtil;
import xin.altitude.cms.framework.config.CmsConfig;

/**
 * 首页
 *
 * @author ucode
 */
// @ResponseBody
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private CmsConfig cmsConfig;
    
    /**
     * 访问首页，提示语
     */
    // @RequestMapping("/")
    @GetMapping("/")
    public String index() {
        return StringUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cmsConfig.getCms().getName(), cmsConfig.getCms().getVersion());
    }
}
