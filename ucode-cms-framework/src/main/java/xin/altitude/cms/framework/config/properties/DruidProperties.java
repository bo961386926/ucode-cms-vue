package xin.altitude.cms.framework.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import xin.altitude.cms.common.util.BeanCopyUtils;

/**
 * druid 配置属性
 *
 * @author ucode
 */
// @Configuration
@ConfigurationProperties("spring.datasource.druid")
public class DruidProperties {
    private Integer initialSize;
    
    private Integer minIdle;
    
    private Integer maxActive;
    
    private Integer maxWait;
    
    private Integer timeBetweenEvictionRunsMillis;
    
    private Integer minEvictableIdleTimeMillis;
    
    private Integer maxEvictableIdleTimeMillis;
    
    private String validationQuery;
    
    private Boolean testWhileIdle;
    
    private Boolean testOnBorrow;
    
    private Boolean testOnReturn;
    
    public DruidDataSource createDataSource(DruidDataSource datasource) {
        BeanCopyUtils.copyProperties(this, datasource);
        return datasource;
    }
    
    public Integer getInitialSize() {
        return initialSize;
    }
    
    public void setInitialSize(Integer initialSize) {
        this.initialSize = initialSize;
    }
    
    public Integer getMinIdle() {
        return minIdle;
    }
    
    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }
    
    public Integer getMaxActive() {
        return maxActive;
    }
    
    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }
    
    public Integer getMaxWait() {
        return maxWait;
    }
    
    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }
    
    public Integer getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }
    
    public void setTimeBetweenEvictionRunsMillis(Integer timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }
    
    public Integer getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }
    
    public void setMinEvictableIdleTimeMillis(Integer minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }
    
    public Integer getMaxEvictableIdleTimeMillis() {
        return maxEvictableIdleTimeMillis;
    }
    
    public void setMaxEvictableIdleTimeMillis(Integer maxEvictableIdleTimeMillis) {
        this.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
    }
    
    public String getValidationQuery() {
        return validationQuery;
    }
    
    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }
    
    public Boolean isTestWhileIdle() {
        return testWhileIdle;
    }
    
    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }
    
    public Boolean isTestOnBorrow() {
        return testOnBorrow;
    }
    
    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
    
    public Boolean isTestOnReturn() {
        return testOnReturn;
    }
    
    public void setTestOnReturn(Boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }
}
