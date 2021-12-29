package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysConfig;

/**
 * 参数配置 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysConfigMapper extends BaseMapper<SysConfig> {
    // /**
    //  * 查询参数配置信息
    //  *
    //  * @param config 参数配置信息
    //  * @return 参数配置信息
    //  */
    // public SysConfig selectConfig(SysConfig config);
    //
    // /**
    //  * 查询参数配置列表
    //  *
    //  * @param config 参数配置信息
    //  * @return 参数配置集合
    //  */
    // public List<SysConfig> selectConfigList(SysConfig config);
    //
    // /**
    //  * 根据键名查询参数配置信息
    //  *
    //  * @param configKey 参数键名
    //  * @return 参数配置信息
    //  */
    // public SysConfig checkConfigKeyUnique(String configKey);
    //
    // /**
    //  * 新增参数配置
    //  *
    //  * @param config 参数配置信息
    //  * @return 结果
    //  */
    // public int insertConfig(SysConfig config);
    //
    // /**
    //  * 修改参数配置
    //  *
    //  * @param config 参数配置信息
    //  * @return 结果
    //  */
    // public int updateConfig(SysConfig config);
    //
    // /**
    //  * 删除参数配置
    //  *
    //  * @param configId 参数ID
    //  * @return 结果
    //  */
    // public int deleteConfigById(Long configId);
    //
    // /**
    //  * 批量删除参数信息
    //  *
    //  * @param configIds 需要删除的参数ID
    //  * @return 结果
    //  */
    // public int deleteConfigByIds(Long[] configIds);
}
