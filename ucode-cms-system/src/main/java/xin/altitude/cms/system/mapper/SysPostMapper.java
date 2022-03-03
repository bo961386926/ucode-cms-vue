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
import xin.altitude.cms.system.domain.SysPost;

/**
 * 岗位信息 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysPostMapper extends BaseMapper<SysPost> {
    // /**
    //  * 查询岗位数据集合
    //  *
    //  * @param post 岗位信息
    //  * @return 岗位数据集合
    //  */
    // public List<SysPost> selectPostList(SysPost post);
    //
    // /**
    //  * 查询所有岗位
    //  *
    //  * @return 岗位列表
    //  */
    // public List<SysPost> selectPostAll();
    //
    // /**
    //  * 通过岗位ID查询岗位信息
    //  *
    //  * @param postId 岗位ID
    //  * @return 角色对象信息
    //  */
    // public SysPost selectPostById(Long postId);
    //
    // /**
    //  * 根据用户ID获取岗位选择框列表
    //  *
    //  * @param userId 用户ID
    //  * @return 选中岗位ID列表
    //  */
    // public List<Integer> selectPostListByUserId(Long userId);
    //
    // /**
    //  * 查询用户所属岗位组
    //  *
    //  * @param userName 用户名
    //  * @return 结果
    //  */
    // public List<SysPost> selectPostsByUserName(String userName);
    //
    // /**
    //  * 删除岗位信息
    //  *
    //  * @param postId 岗位ID
    //  * @return 结果
    //  */
    // public int deletePostById(Long postId);
    //
    // /**
    //  * 批量删除岗位信息
    //  *
    //  * @param postIds 需要删除的岗位ID
    //  * @return 结果
    //  */
    // public int deletePostByIds(Long[] postIds);
    //
    // /**
    //  * 修改岗位信息
    //  *
    //  * @param post 岗位信息
    //  * @return 结果
    //  */
    // public int updatePost(SysPost post);
    //
    // /**
    //  * 新增岗位信息
    //  *
    //  * @param post 岗位信息
    //  * @return 结果
    //  */
    // public int insertPost(SysPost post);
    //
    // /**
    //  * 校验岗位名称
    //  *
    //  * @param postName 岗位名称
    //  * @return 结果
    //  */
    // public SysPost checkPostNameUnique(String postName);
    //
    // /**
    //  * 校验岗位编码
    //  *
    //  * @param postCode 岗位编码
    //  * @return 结果
    //  */
    // public SysPost checkPostCodeUnique(String postCode);
}
