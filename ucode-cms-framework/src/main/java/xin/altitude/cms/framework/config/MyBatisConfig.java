package xin.altitude.cms.framework.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import xin.altitude.cms.common.util.SpringUtils;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Mybatis支持*匹配扫描包
 *
 * @author ucode
 */
public class MyBatisConfig extends AbstractMyBatisConfig {
    
    /**
     * 构造SqlSession会话工厂
     *
     * @param dataSource 数据源
     * @return SqlSessionFactory
     * @throws Exception Exception
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        /* 注入MybatisPlus会话工厂 */
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        /* 注入MyBatis全局配置 */
        Optional.ofNullable(env.getProperty("mybatis.mapperLocations")).ifPresent(e -> sessionFactory.setMapperLocations(getMapperLocations(e)));
        // /* 注入mapper扫描地址 */
        // Optional.ofNullable(env.getProperty("mybatis.configLocation")).ifPresent(e -> sessionFactory.setConfigLocation(getConfigLocation(e)));
        VFS.addImplClass(SpringBootVFS.class);
        sessionFactory.setConfiguration(SpringUtils.getBean(MybatisPlusProperties.class).getConfiguration());
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(interceptor());
        return sessionFactory.getObject();
    }
    
}