package xin.altitude.cms.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.code.domain.MetaTable;

/**
 * 信息Mapper接口
 *
 * @author explore
 */
@Mapper
@CacheNamespace(flushInterval = 10000, blocking = true, eviction = ScheduledCache.class)
public interface MetaTableMapper extends BaseMapper<MetaTable> {

}
