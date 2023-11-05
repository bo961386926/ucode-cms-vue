/*
 *
 * Copyright (c) 2020-2022, 赛泰先生 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.tenant.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.tenant.core.DynamicTenant;
import xin.altitude.cms.tenant.domain.DbSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * druid 配置多数据源
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 */
public class TenantHikariConfig {
    public final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 默认（缺省）数据源
     */
    @Bean
    public DataSource defaultDataSource(DataSourceProperties properties) {
        HikariConfig hikariConfig = new HikariConfig();
        BeanUtils.copyProperties(properties, hikariConfig);
        hikariConfig.setJdbcUrl(properties.getUrl());
        return new HikariDataSource(hikariConfig);
    }


    @Bean
    @Primary
    public DataSource tenantDataSource() {
        return tenantDataSource(SpringUtils.getBean("defaultDataSource"));
    }

    /**
     * 刷新Bean使用
     *
     * @param defaultDataSource 默认数据源
     */
    public DataSource tenantDataSource(DataSource defaultDataSource) {
        List<DbSource> dbSources = listConfigs(defaultDataSource);

        Map<Object, Object> targetDataSources = new HashMap<>();
        if (dbSources.size() > 0) {
            for (DbSource dbSource : dbSources) {
                HikariConfig hikariConfig = new HikariConfig();
                BeanUtils.copyProperties(dbSource, hikariConfig);
                hikariConfig.setJdbcUrl(dbSource.getUrl());
                targetDataSources.put(dbSource.getTenantId(), new HikariDataSource(hikariConfig));
                log.debug("租户[{}}数据库配置初始化完毕", dbSource.getTenantId());
            }
        }
        return new DynamicTenant(defaultDataSource, targetDataSources);
    }


    /**
     * 通过默认数据源访问数据库
     */
    private List<DbSource> listConfigs(DataSource dataSource) {
        try {
            List<DbSource> result = new ArrayList<>();
            String sql = "select * from tb_db_source";
            Connection conn = dataSource.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DbSource dbSource = new DbSource();
                dbSource.setTenantId(rs.getString("user_id"));
                dbSource.setDriverClassName(rs.getString("driver_class_name"));
                dbSource.setUrl(rs.getString("url"));
                dbSource.setUsername(rs.getString("username"));
                dbSource.setPassword(rs.getString("password"));
                result.add(dbSource);
            }
            rs.close();
            ps.close();
            conn.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
