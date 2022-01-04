package xin.altitude.cms.code.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.SpringBootVFS;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import xin.altitude.cms.code.config.property.HikariProperties;
import xin.altitude.cms.code.constant.CodeConstant;
import xin.altitude.cms.code.util.CodeSpringUtils;
import xin.altitude.cms.common.util.ResourceUtils;


/**
 * Mybatis支持*匹配扫描包
 *
 * @author ruoyi
 */
public class MyBatisPlusConfig {
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 阻止全表更新（此配置非常有用，不能不遵守规范的开发者）
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
    
    /**
     * 注入分页拦截器，使二级缓存生效
     *
     * @return SqlSessionFactory
     */
    @Primary
    @Bean(name = CodeConstant.CODE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory() {
        VFS.addImplClass(SpringBootVFS.class);
        String mapperLocations = "classpath*:mapper/metadata/*Mapper.xml";
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        HikariProperties properties = CodeSpringUtils.getBean(HikariProperties.class);
        BeanUtils.copyProperties(properties, dataSource);
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setCacheEnabled(true);
        configuration.setLogImpl(StdOutImpl.class);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(mybatisPlusInterceptor());
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