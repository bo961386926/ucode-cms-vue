package xin.ucode.admin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.plugins.Docket;
import xin.altitude.cms.framework.config.AbstractSwaggerConfig;

/**
 * Swagger2的接口配置
 *
 * @author ucode
 */
@Configuration
@ConditionalOnProperty(value = "ucode.swagger.enabled", havingValue = "true")
public class SwaggerConfig extends AbstractSwaggerConfig {
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        Docket docket = createBaseDocket();
        return docket;
    }
    
}
