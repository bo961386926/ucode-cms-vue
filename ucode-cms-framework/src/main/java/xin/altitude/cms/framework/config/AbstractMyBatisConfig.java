package xin.altitude.cms.framework.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import xin.altitude.cms.common.util.ResourceUtils;
import xin.altitude.cms.common.util.StringUtil;

/**
 * @author explore
 * @since 2019/01/05 16:18
 **/
public abstract class AbstractMyBatisConfig {
    @Autowired
    protected Environment env;
    
    protected MybatisPlusInterceptor interceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页拦截器
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 阻止全表更新（此配置非常有用，不能不遵守规范的开发者）
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
    
    protected Resource[] getMapperLocations(String mapperLocations) {
        return ResourceUtils.resolveMapperLocations(StringUtil.split(mapperLocations, ","));
    }
    
    protected Resource getConfigLocation(String configLocation) {
        return ResourceUtils.resolveConfigLocation(configLocation);
    }
}
