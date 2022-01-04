package xin.ucode.admin.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import xin.altitude.cms.common.util.ResourceUtils;
import xin.altitude.cms.common.util.StringUtil;

import javax.sql.DataSource;

/**
 * Mybatis支持*匹配扫描包
 *
 * @author ucode
 */
@Configuration
public class MyBatisConfig {
    @Autowired
    private Environment env;
    
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        String mapperLocations = env.getProperty("mybatis.mapperLocations");
        String configLocation = env.getProperty("mybatis.configLocation");
        VFS.addImplClass(SpringBootVFS.class);
        
        final MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(ResourceUtils.resolveMapperLocations(StringUtil.split(mapperLocations, ",")));
        sessionFactory.setConfigLocation(ResourceUtils.resolveConfigLocation(configLocation));
        sessionFactory.setPlugins(interceptor());
        return sessionFactory.getObject();
    }
    
    public MybatisPlusInterceptor interceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 阻止全表更新（此配置非常有用，不能不遵守规范的开发者）
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}