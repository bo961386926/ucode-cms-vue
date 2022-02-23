

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

package xin.altitude.cms.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 简易分页实体
 *
 * @author explore
 */
public class PageEntity {
    private long current = 1;
    private long size = 10;

    public PageEntity() {
    }

    public PageEntity(long current, long size) {
        this.current = current;
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    /**
     * 转换为MybatisPlus分页对象
     *
     * @return 分页实体
     */
    public <T> Page<T> toPage() {
        return new Page<>(current, size);
    }

    /**
     * 转换为MybatisPlus分页对象
     *
     * @return 分页实体
     */
    public <T> Page<T> toPage(Class<T> clazz) {
        return new Page<>(current, size);
    }

    @Override
    public String toString() {
        return "PageEntity{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}
