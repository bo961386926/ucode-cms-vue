/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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
