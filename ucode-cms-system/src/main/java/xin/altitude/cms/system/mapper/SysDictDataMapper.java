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
import xin.altitude.cms.framework.core.domain.SysDictData;

/**
 * 字典表 数据层
 *
 * @author ucode
 */
// @Mapper
@CacheNamespace(eviction = ScheduledCache.class, blocking = true, flushInterval = 86400 * 1000)
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
    // /**
    //  * 根据条件分页查询字典数据
    //  *
    //  * @param dictData 字典数据信息
    //  * @return 字典数据集合信息
    //  */
    // public List<SysDictData> selectDictDataList(SysDictData dictData);
    //
    // /**
    //  * 根据字典类型查询字典数据
    //  *
    //  * @param dictType 字典类型
    //  * @return 字典数据集合信息
    //  */
    // public List<SysDictData> selectDictDataByType(String dictType);
    //
    // /**
    //  * 根据字典类型和字典键值查询字典数据信息
    //  *
    //  * @param dictType  字典类型
    //  * @param dictValue 字典键值
    //  * @return 字典标签
    //  */
    // public String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);
    //
    // /**
    //  * 根据字典数据ID查询信息
    //  *
    //  * @param dictCode 字典数据ID
    //  * @return 字典数据
    //  */
    // public SysDictData selectDictDataById(Long dictCode);
    //
    // /**
    //  * 查询字典数据
    //  *
    //  * @param dictType 字典类型
    //  * @return 字典数据
    //  */
    // public int countDictDataByType(String dictType);
    //
    // /**
    //  * 通过字典ID删除字典数据信息
    //  *
    //  * @param dictCode 字典数据ID
    //  * @return 结果
    //  */
    // public int deleteDictDataById(Long dictCode);
    //
    // /**
    //  * 批量删除字典数据信息
    //  *
    //  * @param dictCodes 需要删除的字典数据ID
    //  * @return 结果
    //  */
    // public int deleteDictDataByIds(Long[] dictCodes);
    //
    // /**
    //  * 新增字典数据信息
    //  *
    //  * @param dictData 字典数据信息
    //  * @return 结果
    //  */
    // public int insertDictData(SysDictData dictData);
    //
    // /**
    //  * 修改字典数据信息
    //  *
    //  * @param dictData 字典数据信息
    //  * @return 结果
    //  */
    // public int updateDictData(SysDictData dictData);
    //
    // /**
    //  * 同步修改字典类型
    //  *
    //  * @param oldDictType 旧字典类型
    //  * @param newDictType 新旧字典类型
    //  * @return 结果
    //  */
    // public int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType);
}
