package xin.altitude.cms.framework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.core.interceptor.RepeatSubmitInterceptor;

/**
 * 通用配置
 *
 * @author ucode
 */
// @Configuration
public class ResourcesConfig implements WebMvcConfigurer {
    private final CmsConfig cmsConfig = SpringUtils.getBean(CmsConfig.class);
    @Autowired
    private RepeatSubmitInterceptor repeatSubmitInterceptor;
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /* 本地文件上传路径 */
        registry.addResourceHandler(Constants.RESOURCE_PREFIX + "/**")
                .addResourceLocations("file:" + cmsConfig.getCms().getProfile() + "/");
    
        /* swagger配置 */
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    
        /* cms-ui配置 */
        registry.addResourceHandler("/cms-web/**").addResourceLocations("classpath:/META-INF/resources/");
    }
    
    /**
     * 自定义拦截规则
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 添加全局防重复提交拦截器 */
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
}