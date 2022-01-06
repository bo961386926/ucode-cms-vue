package xin.altitude.cms.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xin.altitude.cms.system.domain.SysUserRole;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/26 13:09
 **/
public interface ISysUserRoleService extends IService<SysUserRole> {
    void deleteUserRoleByUserId(Long userId);
    
    void deleteUserRoleByUserIds(List<Long> userIds);
}
