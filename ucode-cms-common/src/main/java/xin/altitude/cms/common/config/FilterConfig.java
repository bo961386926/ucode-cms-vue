package xin.altitude.cms.common.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.altitude.cms.common.filter.RepeatableFilter;
import xin.altitude.cms.common.filter.XssFilter;
import xin.altitude.cms.common.util.StringUtils;
import xin.altitude.cms.common.util.spring.SpringUtils;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Filter配置
 *
 * @author ucode
 */
@Configuration
public class FilterConfig {
    private final CmsConfig.Xss xss = SpringUtils.getBean(CmsConfig.class).getXss();
    
    @Bean
    @ConditionalOnProperty(value = "ucode.xss.enabled", havingValue = "true")
    public FilterRegistrationBean<Filter> xssFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setDispatcherTypes(DispatcherType.REQUEST);
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns(StringUtils.split(xss.getUrlPatterns(), ","));
        registration.setName("xssFilter");
        registration.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", xss.getExcludes());
        registration.setInitParameters(initParameters);
        return registration;
    }
    
    @Bean
    public FilterRegistrationBean<Filter> someFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
    
}