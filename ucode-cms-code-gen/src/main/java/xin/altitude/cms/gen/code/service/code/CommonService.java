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

package xin.altitude.cms.gen.code.service.code;

import org.apache.commons.collections.ListUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.gen.code.config.property.CodeProperties;
import xin.altitude.cms.gen.code.entity.bo.ConfigBo;
import xin.altitude.cms.gen.code.entity.vo.MetaColumnVo;
import xin.altitude.cms.gen.code.mysql.domain.MetaColumn;
import xin.altitude.cms.gen.code.mysql.domain.MetaTable;
import xin.altitude.cms.gen.code.mysql.service.MetaColumnService;
import xin.altitude.cms.gen.code.mysql.service.MetaTableService;
import xin.altitude.cms.gen.code.util.CodeUtils;
import xin.altitude.cms.gen.code.util.TemplateMethod;
import xin.altitude.cms.gen.code.util.VelocityInitializer;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.List;


/**
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 */
// @Import({KeyColumnUsageImpl.class})
public abstract class CommonService {
    // @Autowired

    /**
     * 配置
     */
    protected static ConfigBo config = new ConfigBo(SpringUtils.getBean(CodeProperties.class));
    @Autowired
    private MetaTableService metaTableService;
    @Autowired
    private MetaColumnService metaColumnService;

    public static ConfigBo getConfig() {
        return config;
    }

    public static void setConfig(ConfigBo config) {
        CommonService.config = config;
    }

    /**
     * 查询表信息
     */
    public MetaTable getTableInfo(String tableName) {
        return metaTableService.getMetaTable(tableName);
    }

    /**
     * 查询列信息
     */
    public List<MetaColumnVo> getColumnVos(String tableName) {
        return metaColumnService.getColumnVos(tableName);
    }

    /**
     * 获取两表列名信息的差集
     *
     * @param tableNameA 表名A
     * @param tableNameB 表名B
     * @return 差集合
     */
    public List<MetaColumn> listColumns(String tableNameA, String tableNameB) {
        List<MetaColumn> a = metaColumnService.listColumns(tableNameA);
        List<MetaColumn> b = metaColumnService.listColumns(tableNameB);
        return ListUtils.subtract(a, b);
    }

    /**
     * 通过连接ID，判断所有权，获取连接，查询列信息
     *
     * @return 列实体VO
     */
    public MetaColumnVo getPkColumn(String tableName) {
        List<MetaColumnVo> columnsVoList = getColumnVos(tableName);
        columnsVoList.forEach(CodeUtils::handleColumnField);
        return columnsVoList.stream().filter(MetaColumnVo::getPkColumn).findFirst().orElse(null);
    }


    public List<MetaColumnVo> getMetaColumnVoList(String tableNameA, String tableNameB) {
        List<MetaColumn> columns = listColumns(tableNameA, tableNameB);
        List<MetaColumnVo> columnsVoList = EntityUtils.toList(columns, MetaColumnVo::new);
        columnsVoList.forEach(CodeUtils::handleColumnField);
        return columnsVoList;
    }

    /**
     * 创建全局Context
     *
     * @return Context
     */
    public VelocityContext createContext() {
        VelocityContext context = new VelocityContext();
        context.put("t", new TemplateMethod());
        context.put("configEntity", config);
        context.put("packageName", config.getPackageName());
        return context;
    }

    /**
     * 将模板渲染
     *
     * @param context  数据
     * @param template 模版
     * @return StringWriter
     */
    public StringWriter renderTemplate(VelocityContext context, String template) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        Template tpl = Velocity.getTemplate(template, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return sw;
    }


}
