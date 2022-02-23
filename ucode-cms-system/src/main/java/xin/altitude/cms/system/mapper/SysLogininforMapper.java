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
import xin.altitude.cms.system.domain.SysLogininfor;

/**
 * 系统访问日志情况信息 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {
    // /**
    //  * 新增系统登录日志
    //  *
    //  * @param logininfor 访问日志对象
    //  */
    // public void insertLogininfor(SysLogininfor logininfor);
    //
    // /**
    //  * 查询系统登录日志集合
    //  *
    //  * @param logininfor 访问日志对象
    //  * @return 登录记录集合
    //  */
    // public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);
    //
    // /**
    //  * 批量删除系统登录日志
    //  *
    //  * @param infoIds 需要删除的登录日志ID
    //  * @return 结果
    //  */
    // public int deleteLogininforByIds(Long[] infoIds);
    //
    // /**
    //  * 清空系统登录日志
    //  *
    //  * @return 结果
    //  */
    // public int cleanLogininfor();
}
