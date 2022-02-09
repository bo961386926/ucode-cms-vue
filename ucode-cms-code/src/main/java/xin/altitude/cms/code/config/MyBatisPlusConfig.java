/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.config;

import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import xin.altitude.cms.code.constant.CodeConstant;
import xin.altitude.cms.code.util.CodeSpringUtils;
import xin.altitude.cms.common.util.ResourceUtils;
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
    // @Primary
    @Bean(name = CodeConstant.CODE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() {
        VFS.addImplClass(SpringBootVFS.class);
        String mapperLocations = "classpath*:mapper/metadata/*Mapper.xml";
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setCacheEnabled(true);
        configuration.setLogImpl(StdOutImpl.class);
        /* 读取动态数据源 */
        sessionFactory.setDataSource(CodeSpringUtils.getBean(DataSourceName.DYNAMIC_DATA_SOURCE));
        sessionFactory.setPlugins(interceptor());
        sessionFactory.setConfiguration(configuration);
        sessionFactory.setMapperLocations(ResourceUtils.resolveMapperLocations(mapperLocations));
        try {
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}