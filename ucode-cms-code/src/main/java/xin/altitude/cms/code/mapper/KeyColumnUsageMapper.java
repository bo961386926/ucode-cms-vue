/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.code.domain.KeyColumnUsage;

/**
 * @author explore
 * @since 2019/12/18 19:36
 **/
@Mapper
@CacheNamespace(flushInterval = 10000, blocking = true, eviction = ScheduledCache.class)
public interface KeyColumnUsageMapper extends BaseMapper<KeyColumnUsage> {
}
