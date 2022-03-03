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

package xin.altitude.cms.system.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.system.domain.SysUserPost;
import xin.altitude.cms.system.mapper.SysUserPostMapper;
import xin.altitude.cms.system.service.ISysUserPostService;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/24 13:17
 **/
// @Service
public class SysUserPostServiceServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {
    @Override
    public void deleteUserPostByUserId(Long userId) {
        remove(Wrappers.lambdaQuery(SysUserPost.class).eq(SysUserPost::getUserId, userId));
    }

    @Override
    public void deleteUserPostByUserIds(List<Long> userIds) {
        if (userIds.size() > 0) {
            remove(Wrappers.lambdaQuery(SysUserPost.class).in(SysUserPost::getUserId, userIds));
        }
    }
}
