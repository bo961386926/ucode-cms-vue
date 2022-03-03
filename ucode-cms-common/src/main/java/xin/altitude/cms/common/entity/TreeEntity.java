

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

package xin.altitude.cms.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * 树形统一结构
 *
 * @author explore
 * @since 2019/01/04 15:38
 **/
public abstract class TreeEntity<T> {
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<? extends TreeEntity<T>> childs;

    public List<? extends TreeEntity<T>> getChilds() {
        return childs;
    }

    public void setChilds(List<? extends TreeEntity<T>> childs) {
        this.childs = childs;
    }

    /**
     * 获取主键ID
     *
     * @return ID
     */
    public abstract T getId();

    /**
     * 获取名称
     *
     * @return 名称
     */
    public abstract String getName();

    /**
     * 获取父ID
     *
     * @return 父ID
     */
    public abstract T getParentId();
}
