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

package xin.altitude.cms.code.service.code;

import xin.altitude.cms.code.domain.KeyColumnUsage;
import xin.altitude.cms.code.domain.MetaColumn;
import xin.altitude.cms.code.domain.MetaTable;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/03 16:55
 **/
public interface ICommonService {
    MetaTable getTableInfo(String tableName);

    List<MetaColumnVo> getColumnVos(String tableName);

    /**
     * 获取两表列名信息的差集
     *
     * @param tableNameA 表名A
     * @param tableNameB 表名B
     * @return 差集合
     */
    List<MetaColumn> listColumns(String tableNameA, String tableNameB);

    MetaColumnVo getPkColumn(String tableName);

    /**
     * 查询外键索引信息
     *
     * @param tableName 当前表名
     * @return 外键集合
     */
    List<KeyColumnUsage> listKeyColumns(String tableName);

    KeyColumnUsageVo toKeyColumnUsageVo(KeyColumnUsage keyColumnUsage);
}
