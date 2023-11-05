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

package xin.altitude.cms.tenant.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 */
public class DynamicTenant extends AbstractRoutingDataSource {
    public final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 构造器
     */
    public DynamicTenant(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        // 设置默认数据源（主数据源必须存在）
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        // 设置备用数据源（备用数据源允许没有）
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }


    @Override
    protected String determineCurrentLookupKey() {
        String tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            log.info("当前请求使用主（默认）数据源访问数据库");
        } else {
            log.info("当前请求使用租户:[{}]访问数据库", tenantId);
        }
        return tenantId;
    }


}
