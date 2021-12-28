package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysRoleMenu;

/**
 * 角色与菜单关联表 数据层
 *
 * @author ucode
 */
@Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    // /**
    //  * 查询菜单使用数量
    //  *
    //  * @param menuId 菜单ID
    //  * @return 结果
    //  */
    // public int checkMenuExistRole(Long menuId);
    //
    // /**
    //  * 通过角色ID删除角色和菜单关联
    //  *
    //  * @param roleId 角色ID
    //  * @return 结果
    //  */
    // public int deleteRoleMenuByRoleId(Long roleId);
    //
    // /**
    //  * 批量删除角色菜单关联信息
    //  *
    //  * @param ids 需要删除的数据ID
    //  * @return 结果
    //  */
    // public int deleteRoleMenu(Long[] ids);
    //
    // /**
    //  * 批量新增角色菜单信息
    //  *
    //  * @param roleMenuList 角色菜单列表
    //  * @return 结果
    //  */
    // public int batchRoleMenu(List<SysRoleMenu> roleMenuList);
}
