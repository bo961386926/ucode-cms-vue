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
import xin.altitude.cms.framework.core.domain.SysRole;

/**
 * 角色表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysRoleMapper extends BaseMapper<SysRole> {
    // /**
    //  * 根据条件分页查询角色数据
    //  *
    //  * @param role 角色信息
    //  * @return 角色数据集合信息
    //  */
    // public List<SysRole> selectRoleList(SysRole role);
    //
    // /**
    //  * 根据用户ID查询角色
    //  *
    //  * @param userId 用户ID
    //  * @return 角色列表
    //  */
    // public List<SysRole> selectRolePermissionByUserId(Long userId);
    //
    // /**
    //  * 查询所有角色
    //  *
    //  * @return 角色列表
    //  */
    // public List<SysRole> selectRoleAll();
    //
    // /**
    //  * 根据用户ID获取角色选择框列表
    //  *
    //  * @param userId 用户ID
    //  * @return 选中角色ID列表
    //  */
    // public List<Long> selectRoleListByUserId(Long userId);
    //
    // /**
    //  * 通过角色ID查询角色
    //  *
    //  * @param roleId 角色ID
    //  * @return 角色对象信息
    //  */
    // public SysRole selectRoleById(Long roleId);
    //
    // /**
    //  * 根据用户ID查询角色
    //  *
    //  * @param userName 用户名
    //  * @return 角色列表
    //  */
    // public List<SysRole> selectRolesByUserName(String userName);
    //
    // /**
    //  * 校验角色名称是否唯一
    //  *
    //  * @param roleName 角色名称
    //  * @return 角色信息
    //  */
    // public SysRole checkRoleNameUnique(String roleName);
    //
    // /**
    //  * 校验角色权限是否唯一
    //  *
    //  * @param roleKey 角色权限
    //  * @return 角色信息
    //  */
    // public SysRole checkRoleKeyUnique(String roleKey);
    //
    // /**
    //  * 修改角色信息
    //  *
    //  * @param role 角色信息
    //  * @return 结果
    //  */
    // public int updateRole(SysRole role);
    //
    // /**
    //  * 新增角色信息
    //  *
    //  * @param role 角色信息
    //  * @return 结果
    //  */
    // public int insertRole(SysRole role);
    //
    // /**
    //  * 通过角色ID删除角色
    //  *
    //  * @param roleId 角色ID
    //  * @return 结果
    //  */
    // public int deleteRoleById(Long roleId);
    //
    // /**
    //  * 批量删除角色信息
    //  *
    //  * @param roleIds 需要删除的角色ID
    //  * @return 结果
    //  */
    // public int deleteRoleByIds(Long[] roleIds);
}
