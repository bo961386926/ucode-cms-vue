package xin.altitude.cms.monitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.auth.web.domain.Server;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.core.domain.AjaxResult;

/**
 * 服务器监控
 *
 * @author ucode
 */
@ResponseBody
@RequestMapping(CmsConfig.UNIFORM_PREFIX + "/monitor/server")
public class ServerController {
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
