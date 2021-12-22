package xin.altitude.cms.framework.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.altitude.cms.common.config.CmsConfig;
import xin.altitude.cms.common.filter.RepeatableFilter;
import xin.altitude.cms.common.filter.XssFilter;
import xin.altitude.cms.common.utils.StringUtils;
import xin.altitude.cms.common.utils.spring.SpringUtils;

import javax.servlet.DispatcherType;
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
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnProperty(value = "ucode.xss.enabled", havingValue = "true")
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
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
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
    
}
