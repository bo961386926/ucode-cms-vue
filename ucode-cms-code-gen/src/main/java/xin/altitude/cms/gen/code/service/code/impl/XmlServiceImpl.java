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

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.StreamUtils;
import xin.altitude.cms.gen.code.entity.CodeFile;
import xin.altitude.cms.gen.code.service.code.CommonService;
import xin.altitude.cms.gen.code.service.code.IXmlService;
import xin.altitude.cms.gen.code.util.CodeUtils;
import xin.altitude.cms.gen.code.util.VelocityInitializer;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
public class XmlServiceImpl extends CommonService implements IXmlService {
    private final static String TEMPLATE = "vm10/xml/mapper.xml.vm";

    @Override
    public void writeToLocalFile(String tableName, String className) {
        String fileName = String.format("%sMapper.xml", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativXmlDirFilePath(getConfig().getModuleName());
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }

    public void writeToLocalFile3(String tableName, String className, ZipOutputStream zip) {
        String fileName = String.format("%sMapper.xml", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativXmlDirFilePath(getConfig().getModuleName());
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
        String fileName = String.format("%sMapper.xml", className);
        String value = realtimePreview(tableName);
        return new CodeFile(fileName, value);
    }

    /**
     * 代码实时预览
     */
    @Override
    public String realtimePreview(String tableName) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return sw.toString();
    }

    /**
     * 构建VelocityContext
     */
    @Override
    public VelocityContext createContext(String tableName) {
        VelocityContext context = new VelocityContext();
        context.put("packageName", config.getPackageName());
        context.put("ClassName", CodeUtils.getClassName(tableName));
        return context;
    }
}


