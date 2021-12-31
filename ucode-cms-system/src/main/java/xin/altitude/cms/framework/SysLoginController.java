package xin.altitude.cms.framework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.auth.util.SecurityUtils;
import xin.altitude.cms.auth.web.service.SysLoginService;
import xin.altitude.cms.auth.web.service.SysPermissionService;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.framework.config.CmsConfig;
import xin.altitude.cms.framework.core.domain.SysMenu;
import xin.altitude.cms.framework.core.domain.SysUser;
import xin.altitude.cms.framework.core.model.LoginBody;
import xin.altitude.cms.system.service.ISysMenuService;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author ucode
 */
@ResponseBody
@RequestMapping(CmsConfig.UNIFORM_PREFIX)
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;
    
    @Autowired
    private ISysMenuService menuService;
    
    @Autowired
    private SysPermissionService permissionService;
    
    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
    
    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }
    
    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
