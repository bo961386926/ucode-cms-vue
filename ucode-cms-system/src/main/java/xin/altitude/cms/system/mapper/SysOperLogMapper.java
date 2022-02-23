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
import xin.altitude.cms.system.domain.SysOperLog;

/**
 * 操作日志 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysOperLogMapper extends BaseMapper<SysOperLog> {
    // /**
    //  * 新增操作日志
    //  *
    //  * @param operLog 操作日志对象
    //  */
    // public void insertOperlog(SysOperLog operLog);
    //
    // /**
    //  * 查询系统操作日志集合
    //  *
    //  * @param operLog 操作日志对象
    //  * @return 操作日志集合
    //  */
    // public List<SysOperLog> selectOperLogList(SysOperLog operLog);
    //
    // /**
    //  * 批量删除系统操作日志
    //  *
    //  * @param operIds 需要删除的操作日志ID
    //  * @return 结果
    //  */
    // public int deleteOperLogByIds(Long[] operIds);
    //
    // /**
    //  * 查询操作日志详细
    //  *
    //  * @param operId 操作ID
    //  * @return 操作日志对象
    //  */
    // public SysOperLog selectOperLogById(Long operId);
    //
    // /**
    //  * 清空操作日志
    //  */
    // public void cleanOperLog();
}
