package xin.altitude.cms.framework.config;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import xin.altitude.cms.common.constant.CNTC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author explore
 * @since 2019/07/05 14:18
 **/
// @Configuration
@ConditionalOnClass({Cache.class})
public class SpringCaffeineConfig extends AbstractCaffeineConfig {
    
    @Bean
    public CacheManager caffeineCacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>();
        caches.add(new CaffeineCache(CNTC.CACHE_05SECS, expire(5, TimeUnit.SECONDS)));
        caches.add(new CaffeineCache(CNTC.CACHE_10SECS, expire(10, TimeUnit.SECONDS)));
        caches.add(new CaffeineCache(CNTC.CACHE_30SECS, expire(30, TimeUnit.SECONDS)));
        caches.add(new CaffeineCache(CNTC.CACHE_01MINS, expire(1, TimeUnit.MINUTES)));
        caches.add(new CaffeineCache(CNTC.CACHE_03MINS, expire(3, TimeUnit.MINUTES)));
        caches.add(new CaffeineCache(CNTC.CACHE_05MINS, expire(5, TimeUnit.MINUTES)));
        caches.add(new CaffeineCache(CNTC.CACHE_1HOURS, expire(1, TimeUnit.HOURS)));
        caches.add(new CaffeineCache(CNTC.CACHE_1DAYS, expire(1, TimeUnit.DAYS)));
        
        cacheManager.setCaches(caches);
        return cacheManager;
    }
    
}
