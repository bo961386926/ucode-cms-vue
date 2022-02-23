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

package xin.altitude.cms.code.constant;

/**
 * 数据标准化字段枚举
 *
 * @author explore
 * @since 2019/11/23 15:38
 **/
public enum DdlEnum {
    /**
     * 创建时间
     */
    create_time,
    /**
     * 更新时间
     */
    update_time,
    /**
     * 逻辑删除
     */
    deleted;


    @Override
    public String toString() {
        return name();
    }
}
