package xin.altitude.cms.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.core.interceptor.RepeatSubmitInterceptor;
import xin.altitude.cms.common.util.spring.SpringUtils;

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
        registry.addInterceptor(repeatSubmitInterceptor).addPathPatterns("/**");
    }
    
    // /**
    //  * 跨域配置
    //  */
    // @Bean
    // public CorsFilter corsFilter() {
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowCredentials(true);
    //     // 设置访问源地址
    //     config.addAllowedOriginPattern("*");
    //     // 设置访问源请求头
    //     config.addAllowedHeader("*");
    //     // 设置访问源请求方法
    //     config.addAllowedMethod("*");
    //     // 有效期 1800秒
    //     config.setMaxAge(1800L);
    //     // 添加映射路径，拦截一切请求
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     source.registerCorsConfiguration("/**", config);
    //     // 返回新的CorsFilter
    //     return new CorsFilter(source);
    // }
}