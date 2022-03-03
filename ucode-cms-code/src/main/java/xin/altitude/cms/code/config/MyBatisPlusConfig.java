
/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
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

package xin.altitude.cms.code.config;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import xin.altitude.cms.code.constant.CodeConstant;
import xin.altitude.cms.code.mapper.MetaTableMapper;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.config.AbstractMyBatisConfig;
import xin.altitude.cms.framework.constant.DataSourceName;


/**
 * Mybatis支持*匹配扫描包
 *
 * @author ucode
 */
public class MyBatisPlusConfig extends AbstractMyBatisConfig {
    /**
     * 注入分页拦截器，使二级缓存生效
     *
     * @return SqlSessionFactory
     */
    @Bean(name = CodeConstant.CODE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() {
        VFS.addImplClass(SpringBootVFS.class);
        String mapperLocations = "classpath*:mapper/metadata/*Mapper.xml";
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setCacheEnabled(true);
        configuration.setLogImpl(StdOutImpl.class);
        /* 读取动态数据源 */
        sessionFactory.setDataSource(SpringUtils.getBean(DataSourceName.DYNAMIC_DATA_SOURCE));
        sessionFactory.setPlugins(interceptor());
        sessionFactory.setConfiguration(configuration);
        configuration.getMapperRegistry().addMappers(MetaTableMapper.class.getPackage().getName());
        // sessionFactory.setMapperLocations(ResourceUtils.resolveMapperLocations(mapperLocations));
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
