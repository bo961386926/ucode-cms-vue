package xin.altitude.cms.tenant.core;

import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.tenant.config.TenantHikariConfig;

import javax.sql.DataSource;

/**
 * 定义UserId的实现
 * <p>
 * 刷新租户配置
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public abstract class TenantSupport {
    /**
     * 获取租户ID
     *
     * @return 租户ID
     */
    public abstract String getTenantId();


    /**
     * 当租户ID数量发生变化时，及时刷新配置信息
     * <p>
     * 增加租户时，需要调用此方法刷新配置，否则容器中没有新增租户的数据库信息
     * <p>
     * 减少租户时，理论上可以不刷新配置，但是从节约内存的角度来讲，建议也刷新配置
     */
    public final void refreshTenant() {

        // 取出默认数据源
        DataSource defaultDataSource = SpringUtils.getBean("defaultDataSource");
        // 构造新bean
        DataSource dynamicDataSource = new TenantHikariConfig().tenantDataSource(defaultDataSource);
        SpringUtils.refreshBean("tenantDataSource", dynamicDataSource);
    }
}
