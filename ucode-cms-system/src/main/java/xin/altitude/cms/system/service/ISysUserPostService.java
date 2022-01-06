package xin.altitude.cms.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xin.altitude.cms.system.domain.SysUserPost;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/24 13:18
 **/
public interface ISysUserPostService extends IService<SysUserPost> {
    void deleteUserPostByUserId(Long userId);
    
    void deleteUserPostByUserIds(List<Long> userIds);
}
