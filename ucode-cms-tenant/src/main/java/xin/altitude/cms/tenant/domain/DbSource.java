package xin.altitude.cms.tenant.domain;

import com.zaxxer.hikari.HikariConfig;
import org.springframework.beans.BeanUtils;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class DbSource {
    /**
     * 租户ID
     */
    private String tenantId;
    /**
     * 驱动名称（限定名）
     */
    private String driverClassName;
    /**
     * 数据库连接URL
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 转换成属性对象
     */
    public HikariConfig toHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        BeanUtils.copyProperties(this, hikariConfig);
        hikariConfig.setJdbcUrl(this.getUrl());
        return hikariConfig;
    }
}
