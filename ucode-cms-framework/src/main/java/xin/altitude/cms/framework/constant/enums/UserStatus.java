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

package xin.altitude.cms.framework.constant.enums;

/**
 * 用户状态
 *
 * @author ucode
 */
public enum UserStatus {
    /**
     * 正常
     */
    OK("0", "正常"),

    /**
     * 停用
     */
    DISABLE("1", "停用"),

    /**
     * 删除
     */
    DELETED("2", "删除");

    private final String code;
    private final String info;

    UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
