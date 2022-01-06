package xin.altitude.cms.framework.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author explore
 * @since 2022/01/06 16:11
 **/
public abstract class AbstractCaffeineConfig {
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            String name = target.getClass().getName();
            String methodName = method.getName();
            return String.format("%s:%s(%s)", name, methodName, CollectionUtils.arrayToList(params));
        };
    }
    
    protected Cache<Object, Object> expire(long duration, TimeUnit unit) {
        return Caffeine.newBuilder().expireAfterWrite(duration, unit).build();
    }
}
