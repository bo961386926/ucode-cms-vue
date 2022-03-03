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
import xin.altitude.cms.framework.core.domain.SysDictType;

/**
 * 字典表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
    // /**
    //  * 根据条件分页查询字典类型
    //  *
    //  * @param dictType 字典类型信息
    //  * @return 字典类型集合信息
    //  */
    // public List<SysDictType> selectDictTypeList(SysDictType dictType);
    //
    // /**
    //  * 根据所有字典类型
    //  *
    //  * @return 字典类型集合信息
    //  */
    // public List<SysDictType> selectDictTypeAll();
    //
    // /**
    //  * 根据字典类型ID查询信息
    //  *
    //  * @param dictId 字典类型ID
    //  * @return 字典类型
    //  */
    // public SysDictType selectDictTypeById(Long dictId);
    //
    // /**
    //  * 根据字典类型查询信息
    //  *
    //  * @param dictType 字典类型
    //  * @return 字典类型
    //  */
    // public SysDictType selectDictTypeByType(String dictType);
    //
    // /**
    //  * 通过字典ID删除字典信息
    //  *
    //  * @param dictId 字典ID
    //  * @return 结果
    //  */
    // public int deleteDictTypeById(Long dictId);
    //
    // /**
    //  * 批量删除字典类型信息
    //  *
    //  * @param dictIds 需要删除的字典ID
    //  * @return 结果
    //  */
    // public int deleteDictTypeByIds(Long[] dictIds);
    //
    // /**
    //  * 新增字典类型信息
    //  *
    //  * @param dictType 字典类型信息
    //  * @return 结果
    //  */
    // public int insertDictType(SysDictType dictType);
    //
    // /**
    //  * 修改字典类型信息
    //  *
    //  * @param dictType 字典类型信息
    //  * @return 结果
    //  */
    // public int updateDictType(SysDictType dictType);
    //
    // /**
    //  * 校验字典类型称是否唯一
    //  *
    //  * @param dictType 字典类型
    //  * @return 结果
    //  */
    // public SysDictType checkDictTypeUnique(String dictType);
}
