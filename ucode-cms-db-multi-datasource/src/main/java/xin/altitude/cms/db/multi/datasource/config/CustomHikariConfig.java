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

package xin.altitude.cms.db.multi.datasource.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import xin.altitude.cms.db.multi.datasource.core.DynamicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * druid 配置多数据源
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 */
@Import({ConfigMapBean.class})
public class CustomHikariConfig {

    @Autowired
    private ConfigMapBean configMapBean;


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
    public DataSource dynamicDataSource(DataSource defaultDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        Map<String, DataSourceProperties> map = configMapBean.getMore();
        if (map != null) {
            for (Map.Entry<String, DataSourceProperties> entry : map.entrySet()) {
                HikariConfig hikariConfig = new HikariConfig();
                BeanUtils.copyProperties(entry.getValue(), hikariConfig);
                hikariConfig.setJdbcUrl(entry.getValue().getUrl());
                targetDataSources.put(entry.getKey(), new HikariDataSource(hikariConfig));
            }
        }
        return new DynamicDataSource(defaultDataSource, targetDataSources);
    }
}
