package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.system.domain.SysRoleDept;

/**
 * 角色与部门关联表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysRoleDeptMapper extends BaseMapper<SysRoleDept> {
    // /**
    //  * 通过角色ID删除角色和部门关联
    //  *
    //  * @param roleId 角色ID
    //  * @return 结果
    //  */
    // public int deleteRoleDeptByRoleId(Long roleId);
    //
    // /**
    //  * 批量删除角色部门关联信息
    //  *
    //  * @param ids 需要删除的数据ID
    //  * @return 结果
    //  */
    // public int deleteRoleDept(Long[] ids);
    //
    // /**
    //  * 查询部门使用数量
    //  *
    //  * @param deptId 部门ID
    //  * @return 结果
    //  */
    // public int selectCountRoleDeptByDeptId(Long deptId);
    //
    // /**
    //  * 批量新增角色部门信息
    //  *
    //  * @param roleDeptList 角色部门列表
    //  * @return 结果
    //  */
    // public int batchRoleDept(List<SysRoleDept> roleDeptList);
}
