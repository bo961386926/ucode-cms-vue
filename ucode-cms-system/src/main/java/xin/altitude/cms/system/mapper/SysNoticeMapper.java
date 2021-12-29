package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysNotice;

/**
 * 通知公告表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysNoticeMapper extends BaseMapper<SysNotice> {
    // /**
    //  * 查询公告信息
    //  *
    //  * @param noticeId 公告ID
    //  * @return 公告信息
    //  */
    // public SysNotice selectNoticeById(Long noticeId);
    //
    // /**
    //  * 查询公告列表
    //  *
    //  * @param notice 公告信息
    //  * @return 公告集合
    //  */
    // public List<SysNotice> selectNoticeList(SysNotice notice);
    //
    // /**
    //  * 新增公告
    //  *
    //  * @param notice 公告信息
    //  * @return 结果
    //  */
    // public int insertNotice(SysNotice notice);
    //
    // /**
    //  * 修改公告
    //  *
    //  * @param notice 公告信息
    //  * @return 结果
    //  */
    // public int updateNotice(SysNotice notice);
    //
    // /**
    //  * 批量删除公告
    //  *
    //  * @param noticeId 公告ID
    //  * @return 结果
    //  */
    // public int deleteNoticeById(Long noticeId);
    //
    // /**
    //  * 批量删除公告信息
    //  *
    //  * @param noticeIds 需要删除的公告ID
    //  * @return 结果
    //  */
    // public int deleteNoticeByIds(Long[] noticeIds);
}
