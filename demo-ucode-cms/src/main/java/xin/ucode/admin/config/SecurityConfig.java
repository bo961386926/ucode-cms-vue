package xin.ucode.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import xin.altitude.cms.auth.config.AbstractSecurityConfig;


/**
 * spring security配置
 *
 * @author ucode
 */
// @Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends AbstractSecurityConfig {
    /**
     * 跨域过滤器
     */
    // @Autowired
    // protected CorsFilter corsFilter;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        super.configure(httpSecurity);
        // 添加CORS filter
        // httpSecurity.addFilterBefore(corsFilter, JwtAuthenticationTokenFilter.class);
        // httpSecurity.addFilterBefore(corsFilter, LogoutFilter.class);
    }
}
