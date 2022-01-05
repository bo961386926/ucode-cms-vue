package xin.ucode.admin.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xin.altitude.cms.framework.config.AbstractMyBatisConfig;

import javax.sql.DataSource;

/**
 * Mybatis支持*匹配扫描包
 *
 * @author ucode
 */
@Configuration
public class MyBatisConfig extends AbstractMyBatisConfig {
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String mapperLocations = env.getProperty("mybatis.mapperLocations");
        String configLocation = env.getProperty("mybatis.configLocation");
        VFS.addImplClass(SpringBootVFS.class);
        /* 注入MybatisPlus会话工厂 */
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(getMapperLocations(mapperLocations));
        sessionFactory.setConfigLocation(getConfigLocation(configLocation));
        sessionFactory.setPlugins(interceptor());
        return sessionFactory.getObject();
    }
    
}