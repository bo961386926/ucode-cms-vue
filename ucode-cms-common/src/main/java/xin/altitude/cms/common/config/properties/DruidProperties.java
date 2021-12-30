package xin.altitude.cms.common.config.properties;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import xin.altitude.cms.common.util.bean.BeanCopyUtils;

/**
 * druid 配置属性
 *
 * @author ucode
 */
// @Configuration
@ConfigurationProperties("spring.datasource.druid")
public class DruidProperties {
    // @Value("${spring.datasource.druid.initialSize}")
    private Integer initialSize;
    
    // @Value("${spring.datasource.druid.minIdle}")
    private Integer minIdle;
    
    // @Value("${spring.datasource.druid.maxActive}")
    private Integer maxActive;
    
    // @Value("${spring.datasource.druid.maxWait}")
    private Integer maxWait;
    
    // @Value("${spring.datasource.druid.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;
    
    // @Value("${spring.datasource.druid.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;
    
    // @Value("${spring.datasource.druid.maxEvictableIdleTimeMillis}")
    private Integer maxEvictableIdleTimeMillis;
    
    // @Value("${spring.datasource.druid.validationQuery}")
    private String validationQuery;
    
    // @Value("${spring.datasource.druid.testWhileIdle}")
    private Boolean testWhileIdle;
    
    // @Value("${spring.datasource.druid.testOnBorrow}")
    private Boolean testOnBorrow;
    
    // @Value("${spring.datasource.druid.testOnReturn}")
    private Boolean testOnReturn;
    
    public DruidDataSource createDataSource(DruidDataSource datasource) {
        BeanCopyUtils.copyProperties(this, datasource);
        // /* 配置初始化大小、最小、最大 */
        // datasource.setInitialSize(initialSize);
        // datasource.setMaxActive(maxActive);
        // datasource.setMinIdle(minIdle);
        //
        // /* 配置获取连接等待超时的时间 */
        // datasource.setMaxWait(maxWait);
        //
        // /* 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
        // datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        //
        // /* 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
        // datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        // datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        //
        // /* 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。 */
        // datasource.setValidationQuery(validationQuery);
        // /* 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。 */
        // datasource.setTestWhileIdle(testWhileIdle);
        // /* 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        // datasource.setTestOnBorrow(testOnBorrow);
        // /* 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
        // datasource.setTestOnReturn(testOnReturn);
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
