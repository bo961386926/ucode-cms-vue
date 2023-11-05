/*
 *
 * Copyright (c) 2020-2022, 赛泰先生 (http://www.altitude.xin).
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

package xin.altitude.cms.gen.code.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import xin.altitude.cms.gen.code.entity.bo.ControllerConfig;
import xin.altitude.cms.gen.code.entity.bo.DomainConfig;
import xin.altitude.cms.gen.code.entity.bo.MapperConfig;
import xin.altitude.cms.gen.code.enums.CodeModeEnum;
import xin.altitude.cms.gen.code.enums.DaoEnum;
import xin.altitude.cms.gen.code.enums.FileEnum;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 动态配置实体类
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2019/07/07 13:41
 **/
@Setter
@Getter
@ConfigurationProperties(prefix = "ucode.code")
public class CodeProperties {

    /**
     * 项目的相对路径，如果为空则使用默认值
     */
    private String projectDir;
    /**
     * 代码生成模式
     */
    private CodeModeEnum codeMode = CodeModeEnum.LOCAL;
    /**
     * 是否去除表前缀
     */
    private Boolean removeTablePrefix = false;
    /**
     * 表前缀
     */
    private String tablePrefix;
    /**
     * 基础包名
     */
    private String packageName = "xin.altitude.front";
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 作者
     */
    private String funAuthor = "explore";
    /**
     * DAO数据访问层
     */
    private DaoEnum dao = DaoEnum.mybatisPlus;

    /**
     * 是否使用Swagger
     */
    private Boolean useSwagger = false;
    /**
     * 是否过滤系统表
     */
    private Boolean filterSysTable = true;
    /**
     * 待生成文件列表
     */
    private Set<FileEnum> files = new LinkedHashSet<>(FileEnum.all());
    /**
     * 实体类配置
     */
    @NestedConfigurationProperty
    private DomainConfig domain = new DomainConfig();
    /**
     * 控制器配置
     */
    @NestedConfigurationProperty
    private ControllerConfig controller = new ControllerConfig();
    /**
     * Mapper文件
     */
    @NestedConfigurationProperty
    private MapperConfig mapper = new MapperConfig();

    public CodeProperties() {
    }

    public CodeProperties(CodeProperties codeProperties) {
        packageName = codeProperties.packageName;
        projectDir = codeProperties.projectDir;
        files = codeProperties.files;
        filterSysTable = codeProperties.filterSysTable;
        removeTablePrefix = codeProperties.removeTablePrefix;
        tablePrefix = codeProperties.tablePrefix;
        funAuthor = codeProperties.funAuthor;
        dao = codeProperties.dao;
        useSwagger = codeProperties.useSwagger;
        domain = codeProperties.domain;
        controller = codeProperties.controller;
        mapper = codeProperties.mapper;
        codeMode = codeProperties.codeMode;
        moduleName = codeProperties.moduleName;
    }
}
