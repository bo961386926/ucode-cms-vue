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

package xin.altitude.cms.gen.code.service.code.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.StreamUtils;
import xin.altitude.cms.gen.code.entity.CodeFile;
import xin.altitude.cms.gen.code.enums.DaoEnum;
import xin.altitude.cms.gen.code.service.code.CommonService;
import xin.altitude.cms.gen.code.util.CodeUtils;
import xin.altitude.cms.gen.code.util.VelocityInitializer;
import xin.altitude.cms.gen.code.util.format.JavaFormat4Domain;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2019/07/07 14:11
 **/
// @Service
public class ServiceServiceImpl extends CommonService {
    private final static String TEMPLATE = "vm10/java/service.java.vm";

    /**
     * 默认走此方法
     *
     * @param tableName
     * @param className
     */
    public void writeToLocalFile(String tableName, String className) {
        String fileName = String.format("I%sService.java", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath("service");
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }


    public void writeToLocalFile200(String tableName, String className, ZipOutputStream zip) {
        String fileName = String.format("I%sService.java", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath("service");
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        try {
            zip.putNextEntry(new ZipEntry(filePath));
            StreamUtils.copy(value, StandardCharsets.UTF_8, zip);
            zip.flush();
            zip.closeEntry();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CodeFile writeToLocalFile2(String tableName, String className) {
        String fileName = String.format("I%sService.java", className);
        String value = realtimePreview(tableName);
        return new CodeFile(fileName, value);
    }


    // public void writeToLocalFile(String tableName, String className, KeyColumnUsageVo keyColumnUsageVo) {
    //     String fileName = String.format("I%sService.java", className);
    //     String value = realtimePreview(tableName, keyColumnUsageVo);
    //     String parentDirPath = CodeUtils.createRelativJavaDirFilePath("service");
    //     String filePath = FilenameUtils.concat(parentDirPath, fileName);
    //     CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    // }

    /**
     * 代码实时预览
     */
    public String realtimePreview(String tableName) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }


    // public String realtimePreview(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
    //     StringWriter sw = new StringWriter();
    //     VelocityInitializer.initVelocity();
    //     VelocityContext context = createContext(tableName, keyColumnUsageVo);
    //     Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
    //     tpl.merge(context, sw);
    //     return JavaFormat4Domain.formJava(sw.toString());
    // }


    /**
     * 构建VelocityContext
     */

    public VelocityContext createContext(String tableName) {
        VelocityContext context = createContext();
        context.put("tableName", tableName);
        context.put("ClassName", CodeUtils.getClassName(tableName));
        context.put("className", CodeUtils.getInstanceName(tableName));
        // 添加导包列表
        context.put("importList", getImportList(tableName));
        // 添加表备注
        // context.put("tableComment", getTableInfo(tableName, configEntity.getDbConnId()).getTableComment());
        return context;
    }

    /**
     * 获取导包列表
     */

    public List<String> getImportList(String tableName) {
        ArrayList<String> rs = new ArrayList<>();
        if (DaoEnum.mybatisPlus.equals(config.getDao())) {
            rs.add(String.format("import %s;", IService.class.getName()));
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), CodeUtils.getClassName(tableName)));
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }


    // public List<String> getImportList(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
    //     List<String> rs = getImportList(tableName);
    //     Boolean joinQuery = SpringUtils.getBean(CodeProperties.class).getJoinQuery();
    //     if (joinQuery) {
    //         rs.add("import com.baomidou.mybatisplus.core.metadata.IPage;");
    //         rs.add("import com.baomidou.mybatisplus.core.toolkit.Wrappers;");
    //         rs.add("import xin.altitude.cms.common.util.BeanCopyUtils;");
    //         rs.add("import xin.altitude.cms.common.util.EntityUtils;");
    //         rs.add("import xin.altitude.cms.common.util.SpringUtils;");
    //         rs.add(String.format("import %s.domain.%s;", config.getPackageName(), keyColumnUsageVo.getReferencedClassName()));
    //         rs.add(String.format("import %s.entity.vo.%sVo;", config.getPackageName(), keyColumnUsageVo.getClassName()));
    //         rs.add("import java.util.List;");
    //         rs.add("import java.util.Map;");
    //         rs.add("import java.util.Set;");
    //     }
    //     rs.sort(Comparator.naturalOrder());
    //     return rs;
    // }
}


