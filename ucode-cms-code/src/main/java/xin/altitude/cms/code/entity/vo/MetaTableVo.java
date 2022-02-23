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

package xin.altitude.cms.code.entity.vo;

import xin.altitude.cms.code.domain.MetaTable;

/**
 * 由业务层完成数据初始化
 *
 * @author explore
 * @since 2019/06/19 16:49
 **/
public class MetaTableVo extends MetaTable {
    /**
     * 是否展示系统表
     */
    private boolean showSysTable = false;

    public MetaTableVo(MetaTable metaTable) {
        super(metaTable);
    }

    public MetaTableVo() {
    }

    public boolean isShowSysTable() {
        return showSysTable;
    }

    public void setShowSysTable(boolean showSysTable) {
        this.showSysTable = showSysTable;
    }
}
