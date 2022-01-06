package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.system.domain.SysUserPost;
import xin.altitude.cms.system.mapper.SysUserPostMapper;
import xin.altitude.cms.system.service.ISysUserPostService;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/24 13:17
 **/
// @Service
public class SysUserPostServiceServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {
    @Override
    public void deleteUserPostByUserId(Long userId) {
        remove(Wrappers.lambdaQuery(SysUserPost.class).eq(SysUserPost::getUserId, userId));
    }
    
    @Override
    public void deleteUserPostByUserIds(List<Long> userIds) {
        if (userIds.size() > 0) {
            remove(Wrappers.lambdaQuery(SysUserPost.class).in(SysUserPost::getUserId, userIds));
        }
    }
}
