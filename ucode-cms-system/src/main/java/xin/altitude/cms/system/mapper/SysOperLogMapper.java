package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysOperLog;

/**
 * 操作日志 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    // /**
    //  * 新增操作日志
    //  *
    //  * @param operLog 操作日志对象
    //  */
    // public void insertOperlog(SysOperLog operLog);
    //
    // /**
    //  * 查询系统操作日志集合
    //  *
    //  * @param operLog 操作日志对象
    //  * @return 操作日志集合
    //  */
    // public List<SysOperLog> selectOperLogList(SysOperLog operLog);
    //
    // /**
    //  * 批量删除系统操作日志
    //  *
    //  * @param operIds 需要删除的操作日志ID
    //  * @return 结果
    //  */
    // public int deleteOperLogByIds(Long[] operIds);
    //
    // /**
    //  * 查询操作日志详细
    //  *
    //  * @param operId 操作ID
    //  * @return 操作日志对象
    //  */
    // public SysOperLog selectOperLogById(Long operId);
    //
    // /**
    //  * 清空操作日志
    //  */
    // public void cleanOperLog();
}
