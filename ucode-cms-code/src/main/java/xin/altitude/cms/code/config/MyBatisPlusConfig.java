
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
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import xin.altitude.cms.code.constant.CodeConstant;
import xin.altitude.cms.code.mapper.MetaTableMapper;
import xin.altitude.cms.framework.config.AbstractMyBatisConfig;


/**
 * Mybatis支持*匹配扫描包
 *
 * @author ucode
 */
@Import({CodeDataSourceFactoryBean.class})
public class MyBatisPlusConfig extends AbstractMyBatisConfig {

    @Autowired
    private CodeDataSourceFactoryBean factoryBean;
    
    /**
     * 注入分页拦截器，使二级缓存生效
     *
     * @return SqlSessionFactory
     */
    @Bean(name = CodeConstant.CODE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() {
        VFS.addImplClass(SpringBootVFS.class);
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setCacheEnabled(true);
        configuration.setLogImpl(Slf4jImpl.class);
        sessionFactory.setDataSource(factoryBean.getObject());
        sessionFactory.setPlugins(interceptor());
        sessionFactory.setConfiguration(configuration);
        configuration.getMapperRegistry().addMappers(MetaTableMapper.class.getPackage().getName());
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
