package xin.altitude.cms.framework.config;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author explore
 * @since 2022/01/05 16:10
 **/
public abstract class AbstractSwaggerConfig {
    /**
     * 系统基础配置
     */
    @Autowired
    protected CmsConfig cmsConfig;
    
    protected Docket createBaseDocket() {
        Docket docket = new Docket(DocumentationType.OAS_30)
                // 是否启用Swagger
                .enable(cmsConfig.getSwagger().getEnabled())
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）
                .apiInfo(apiInfo())
                /* 设置安全模式，swagger可以设置访问token */
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .pathMapping(cmsConfig.getSwagger().getPathMapping());
        return docket;
    }
    
    /**
     * 安全模式
     * 指定token通过Authorization头请求头传递
     */
    protected List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        apiKeyList.add(new ApiKey("Authorization", "Authorization", In.HEADER.toValue()));
        return apiKeyList;
    }
    
    /**
     * 安全上下文
     */
    protected List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .operationSelector(o -> o.requestMappingPattern().matches("/.*"))
                        .build());
        return securityContexts;
    }
    
    /**
     * 默认的安全上引用
     */
    protected List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
        return securityReferences;
    }
    
    /**
     * 添加摘要信息
     */
    protected ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title(null)
                // 描述
                .description(null)
                // 作者信息
                .contact(new Contact(cmsConfig.getCms().getName(), null, null))
                // 版本
                .version("版本号:" + cmsConfig.getCms().getVersion())
                .build();
    }
}
