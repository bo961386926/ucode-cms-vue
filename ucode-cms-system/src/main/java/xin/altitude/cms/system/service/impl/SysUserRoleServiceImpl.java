package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.system.domain.SysUserRole;
import xin.altitude.cms.system.mapper.SysUserRoleMapper;
import xin.altitude.cms.system.service.ISysUserRoleService;

import java.util.List;

/**
 * @author explore
 * @since 2021/12/26 13:08
 **/
// @Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {
    @Override
    public void deleteUserRoleByUserId(Long userId) {
        remove(Wrappers.lambdaQuery(SysUserRole.class).eq(SysUserRole::getUserId, userId));
    }
    
    @Override
    public void deleteUserRoleByUserIds(List<Long> userIds) {
        if (userIds.size() > 0) {
            remove(Wrappers.lambdaQuery(SysUserRole.class).in(SysUserRole::getUserId, userIds));
        }
    }
}
