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
import xin.altitude.cms.system.domain.SysUserRole;

/**
 * 用户与角色关联表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    // /**
    //  * 通过用户ID删除用户和角色关联
    //  *
    //  * @param userId 用户ID
    //  * @return 结果
    //  */
    // public int deleteUserRoleByUserId(Long userId);
    //
    // /**
    //  * 批量删除用户和角色关联
    //  *
    //  * @param ids 需要删除的数据ID
    //  * @return 结果
    //  */
    // public int deleteUserRole(Long[] ids);
    //
    // /**
    //  * 通过角色ID查询角色使用数量
    //  *
    //  * @param roleId 角色ID
    //  * @return 结果
    //  */
    // public int countUserRoleByRoleId(Long roleId);
    //
    // /**
    //  * 批量新增用户角色信息
    //  *
    //  * @param userRoleList 用户角色列表
    //  * @return 结果
    //  */
    // public int batchUserRole(List<SysUserRole> userRoleList);
    //
    // /**
    //  * 删除用户和角色关联信息
    //  *
    //  * @param userRole 用户和角色关联信息
    //  * @return 结果
    //  */
    // public int deleteUserRoleInfo(SysUserRole userRole);
    //
    // /**
    //  * 批量取消授权用户角色
    //  *
    //  * @param roleId  角色ID
    //  * @param userIds 需要删除的用户数据ID
    //  * @return 结果
    //  */
    // public int deleteUserRoleInfos(@Param("roleId") Long roleId, @Param("userIds") Long[] userIds);
}
