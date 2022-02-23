/*
 *
 *  *
 *  *  Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *  *
 *
 */

package xin.altitude.cms.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.cache.decorators.ScheduledCache;
import xin.altitude.cms.framework.core.domain.SysDept;

/**
 * 部门管理 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysDeptMapper extends BaseMapper<SysDept> {
    // /**
    //  * 查询部门管理数据
    //  *
    //  * @param dept 部门信息
    //  * @return 部门信息集合
    //  */
    // List<SysDept> selectDeptList(SysDept dept);
    //
    // /**
    //  * 根据角色ID查询部门树信息
    //  *
    //  * @param roleId            角色ID
    //  * @param deptCheckStrictly 部门树选择项是否关联显示
    //  * @return 选中部门列表
    //  */
    // List<Integer> selectDeptListByRoleId(@Param("roleId") Long roleId, @Param("deptCheckStrictly") boolean deptCheckStrictly);
    //
    // /**
    //  * 根据部门ID查询信息
    //  *
    //  * @param deptId 部门ID
    //  * @return 部门信息
    //  */
    // SysDept selectDeptById(Long deptId);
    //
    // /**
    //  * 根据ID查询所有子部门
    //  *
    //  * @param deptId 部门ID
    //  * @return 部门列表
    //  */
    // List<SysDept> selectChildrenDeptById(Long deptId);
    //
    // /**
    //  * 根据ID查询所有子部门（正常状态）
    //  *
    //  * @param deptId 部门ID
    //  * @return 子部门数
    //  */
    // int selectNormalChildrenDeptById(Long deptId);
    //
    // /**
    //  * 是否存在子节点
    //  *
    //  * @param deptId 部门ID
    //  * @return 结果
    //  */
    // int hasChildByDeptId(Long deptId);
    //
    // /**
    //  * 查询部门是否存在用户
    //  *
    //  * @param deptId 部门ID
    //  * @return 结果
    //  */
    // int checkDeptExistUser(Long deptId);
    //
    // /**
    //  * 校验部门名称是否唯一
    //  *
    //  * @param deptName 部门名称
    //  * @param parentId 父部门ID
    //  * @return 结果
    //  */
    // SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);
    //
    // /**
    //  * 新增部门信息
    //  *
    //  * @param dept 部门信息
    //  * @return 结果
    //  */
    // int insertDept(SysDept dept);
    //
    // /**
    //  * 修改部门信息
    //  *
    //  * @param dept 部门信息
    //  * @return 结果
    //  */
    // int updateDept(SysDept dept);
    //
    // /**
    //  * 修改所在部门正常状态
    //  *
    //  * @param deptIds 部门ID组
    //  */
    // void updateDeptStatusNormal(Long[] deptIds);
    //
    // /**
    //  * 修改子元素关系
    //  *
    //  * @param depts 子元素
    //  * @return 结果
    //  */
    // int updateDeptChildren(@Param("depts") List<SysDept> depts);
    //
    // /**
    //  * 删除部门管理信息
    //  *
    //  * @param deptId 部门ID
    //  * @return 结果
    //  */
    // int deleteDeptById(Long deptId);
}
