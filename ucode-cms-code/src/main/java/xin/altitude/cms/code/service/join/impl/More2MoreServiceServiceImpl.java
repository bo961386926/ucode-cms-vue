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

package xin.altitude.cms.code.service.join.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.config.property.CodeProperties;
import xin.altitude.cms.code.domain.KeyColumnUsage;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;
import xin.altitude.cms.code.service.code.impl.CommonServiceImpl;
import xin.altitude.cms.code.util.CodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;
import xin.altitude.cms.code.util.format.JavaFormat4Domain;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.SpringUtils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2019/07/07 14:11
 **/
public class More2MoreServiceServiceImpl extends CommonServiceImpl {
    private final static String TEMPLATE = "vm10/java/more2more/service.java.vm";

    public void writeToLocalFile(String tableName, String className, List<KeyColumnUsage> keyColumnUsageList) {
        List<KeyColumnUsageVo> keyColumnUsageVos = EntityUtils.toList(keyColumnUsageList, this::toKeyColumnUsageVo);
        String fileName = String.format("I%sService.java", className);
        VelocityContext context = createContext(tableName, keyColumnUsageVos);
        String value = JavaFormat4Domain.formJava(renderTemplate(context, TEMPLATE).toString());
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath("service");
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }

    /**
     * 代码实时预览
     */
    public String realtimePreview(VelocityContext context) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }

    public String realtimePreview(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName, keyColumnUsageVo);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }

    public VelocityContext createContext(String tableName, List<KeyColumnUsageVo> keyColumnUsageVos) {
        VelocityContext context = createContext();
        context.put("tableName", tableName);
        context.put("ClassName", CodeUtils.getClassName(tableName));
        if (keyColumnUsageVos.size() == 2 && !keyColumnUsageVos.get(0).getReferencedTableName().equalsIgnoreCase(keyColumnUsageVos.get(1).getReferencedTableName())) {
            context.put("LKeyColumn", keyColumnUsageVos.get(0));
            context.put("RKeyColumn", keyColumnUsageVos.get(1));
        }
        context.put("joinQuery", SpringUtils.getBean(CodeProperties.class).getJoinQuery());
        context.put("importList", new HashSet<>(getImportList(tableName, keyColumnUsageVos)));
        return context;
    }


    /**
     * 构建VelocityContext
     */
    public VelocityContext createContext(String tableName) {
        VelocityContext context = new VelocityContext();
        context.put("configEntity", config);
        context.put("packageName", config.getPackageName());
        context.put("tableName", tableName);
        context.put("ClassName", CodeUtils.getClassName(tableName));
        context.put("className", CodeUtils.getInstanceName(tableName));
        // 添加导包列表
        context.put("importList", getImportList(tableName));
        // 添加表备注
        // context.put("tableComment", getTableInfo(tableName, configEntity.getDbConnId()).getTableComment());
        return context;
    }

    public VelocityContext createContext(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        VelocityContext context = createContext(tableName);
        context.put("keyColumn", keyColumnUsageVo);
        context.put("joinQuery", SpringUtils.getBean(CodeProperties.class).getJoinQuery());
        // 添加导包列表
        context.put("importList", getImportList(tableName, keyColumnUsageVo));
        return context;
    }

    /**
     * 获取导包列表
     */
    public List<String> getImportList(String tableName) {
        ArrayList<String> rs = new ArrayList<>();
        if (config.getUseMybatisPlus()) {
            rs.add(String.format("import %s;", IService.class.getName()));
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), CodeUtils.getClassName(tableName)));
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }

    public List<String> getImportList(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        List<String> rs = getImportList(tableName);
        Boolean joinQuery = SpringUtils.getBean(CodeProperties.class).getJoinQuery();
        if (joinQuery) {
            rs.add("import com.baomidou.mybatisplus.core.metadata.IPage;");
            rs.add("import com.baomidou.mybatisplus.core.toolkit.Wrappers;");
            rs.add("import xin.altitude.cms.common.util.BeanCopyUtils;");
            rs.add("import xin.altitude.cms.common.util.EntityUtils;");
            rs.add("import xin.altitude.cms.common.util.SpringUtils;");
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), keyColumnUsageVo.getReferencedClassName()));
            rs.add(String.format("import %s.entity.vo.%sVo;", config.getPackageName(), keyColumnUsageVo.getClassName()));
            rs.add("import java.util.List;");
            rs.add("import java.util.Map;");
            rs.add("import java.util.Set;");
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }

    public List<String> getImportList(String tableName, List<KeyColumnUsageVo> keyColumnUsageVos) {
        List<String> rs = getImportList(tableName);
        Boolean joinQuery = SpringUtils.getBean(CodeProperties.class).getJoinQuery();
        if (joinQuery) {
            rs.add("import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;");
            rs.add("import com.baomidou.mybatisplus.core.metadata.IPage;");
            rs.add("import com.baomidou.mybatisplus.core.toolkit.Wrappers;");
            rs.add("import com.baomidou.mybatisplus.extension.service.IService;");
            rs.add("import com.google.common.collect.Table;");
            rs.add("import xin.altitude.cms.common.util.BeanCopyUtils;");
            rs.add("import xin.altitude.cms.common.util.EntityUtils;");
            rs.add("import xin.altitude.cms.common.util.SpringUtils;");
            rs.add("import xin.altitude.cms.common.util.TableUtils;");
            rs.add("import java.util.List;");
            rs.add("import java.util.Map;");
            rs.add("import java.util.Set;");
            rs.add("import java.util.stream.Collectors;");
            for (KeyColumnUsageVo keyColumnUsageVo : keyColumnUsageVos) {
                rs.add(String.format("import %s.domain.%s;", config.getPackageName(), keyColumnUsageVo.getReferencedClassName()));
                rs.add(String.format("import %s.entity.vo.%s.%sVo;", config.getPackageName(), keyColumnUsageVo.getClassName(), keyColumnUsageVo.getReferencedClassName()));
                rs.add(String.format("import %s.entity.bo.%s.%sBo;", config.getPackageName(), keyColumnUsageVo.getClassName(), keyColumnUsageVo.getReferencedClassName()));
            }
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}


