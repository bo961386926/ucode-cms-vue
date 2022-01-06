package xin.ucode.front.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import org.springframework.transaction.annotation.Transactional;
import xin.ucode.front.domain.SysConfig;
@Mapper
@Transactional(rollbackFor = Exception.class)
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 60 * 1000)
public interface SysConfigMapper extends BaseMapper<SysConfig>{
}
