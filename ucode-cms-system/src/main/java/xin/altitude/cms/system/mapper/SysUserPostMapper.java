package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysUserPost;

/**
 * 用户与岗位关联表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {
    // /**
    //  * 通过用户ID删除用户和岗位关联
    //  *
    //  * @param userId 用户ID
    //  * @return 结果
    //  */
    // public int deleteUserPostByUserId(Long userId);
    //
    // /**
    //  * 通过岗位ID查询岗位使用数量
    //  *
    //  * @param postId 岗位ID
    //  * @return 结果
    //  */
    // public int countUserPostById(Long postId);
    //
    // /**
    //  * 批量删除用户和岗位关联
    //  *
    //  * @param ids 需要删除的数据ID
    //  * @return 结果
    //  */
    // public int deleteUserPost(Long[] ids);
    //
    // /**
    //  * 批量新增用户岗位信息
    //  *
    //  * @param userPostList 用户角色列表
    //  * @return 结果
    //  */
    // public int batchUserPost(List<SysUserPost> userPostList);
}
