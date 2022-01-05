package xin.altitude.cms.framework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.util.Utils;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.config.properties.DruidProperties;
import xin.altitude.cms.framework.constant.DataSourceName;
import xin.altitude.cms.framework.constant.enums.DataSourceType;
import xin.altitude.cms.framework.core.datasource.DynamicDataSource;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 *
 * @author ucode
 */
// @Configuration
// @EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class DruidConfig {
    
    /**
     * 主数据源
     *
     * @param druidProperties
     * @return
     */
    @Bean(DataSourceName.MASTER_DATA_SOURCE)
    // @ConfigurationProperties("spring.datasource.druid.master")
    @ConfigurationProperties("spring.datasource")
    public DataSource masterDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.createDataSource(dataSource);
    }
    
    /**
     * 副数据源
     *
     * @param druidProperties
     * @return
     */
    @Bean(DataSourceName.SLAVE_DATA_SOURCE)
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(DruidProperties druidProperties) {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        return druidProperties.createDataSource(dataSource);
    }
    
    
    @Primary
    @Bean(DataSourceName.DYNAMIC_DATA_SOURCE)
    public DynamicDataSource dataSource(DataSource masterDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        if (SpringUtils.containsBean(DataSourceName.SLAVE_DATA_SOURCE)) {
            setDataSource(targetDataSources, DataSourceType.SLAVE.name(), DataSourceName.SLAVE_DATA_SOURCE);
        }
        return new DynamicDataSource(masterDataSource, targetDataSources);
    }
    
    /**
     * 设置数据源
     *
     * @param targetDataSources 备选数据源集合
     * @param sourceName        数据源名称
     * @param beanName          bean名称
     */
    public void setDataSource(Map<Object, Object> targetDataSources, String sourceName, String beanName) {
        try {
            DataSource dataSource = SpringUtils.getBean(beanName);
            targetDataSources.put(sourceName, dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 去除监控页面底部的广告
     */
    @Bean
    @ConditionalOnProperty(name = "spring.datasource.druid.statViewServlet.enabled", havingValue = "true")
    public FilterRegistrationBean<Filter> removeDruidFilterRegistrationBean(DruidStatProperties properties) {
        // 获取web监控页面的参数
        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
        // 提取common.js的配置路径
        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
        final String filePath = "support/http/resources/js/common.js";
        // 创建filter进行过滤
        Filter filter = (request, response, chain) -> {
            chain.doFilter(request, response);
            // 重置缓冲区，响应头不会被重置
            response.resetBuffer();
            // 获取common.js
            String text = Utils.readFromResource(filePath);
            // 正则替换banner, 除去底部的广告信息
            response.getWriter().write(text.replaceAll("<a.*?banner\"></a><br/>", "").replaceAll("powered.*?shrek.wang</a>", ""));
        };
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns(commonJsPattern);
        return registrationBean;
    }
}
