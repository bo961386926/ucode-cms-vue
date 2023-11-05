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

package xin.altitude.cms.gen.code.service.core.impl;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import xin.altitude.cms.gen.code.config.property.CodeProperties;
import xin.altitude.cms.gen.code.entity.CodeFile;
import xin.altitude.cms.gen.code.enums.FileEnum;
import xin.altitude.cms.gen.code.service.code.CommonService;
import xin.altitude.cms.gen.code.service.code.DomainService;
import xin.altitude.cms.gen.code.service.code.MapStructService;
import xin.altitude.cms.gen.code.service.code.impl.ControllerServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.MapperServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.ServiceImplServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.ServiceServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.XmlServiceImpl;
import xin.altitude.cms.gen.code.service.core.ICodeHomeService;
import xin.altitude.cms.gen.code.util.CodeUtils;
import xin.altitude.cms.gen.code.util.ConfigUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipOutputStream;


/**
 * 入口代码
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2019/07/08 19:28
 **/
// @Service
public class CodeHomeServiceImpl extends CommonService implements ICodeHomeService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DomainService domainService;
    @Autowired
    private ControllerServiceImpl controllerService;
    @Autowired
    private MapperServiceImpl mapperService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private ServiceImplServiceImpl serviceImplService;
    @Autowired
    private XmlServiceImpl xmlService;

    @Autowired
    private MapStructService mapStructService;

    /**
     * 生成单张表的代码
     *
     * @param tableName 表名
     */
    public void multiTableDownload(String tableName, ZipOutputStream zip) {
        String className = CodeUtils.getClassName(tableName);
        Set<FileEnum> fileList = config.getFiles();
        // 如果不配置 默认是所有的
        if (fileList.isEmpty()) {
            fileList.addAll(FileEnum.all());
        }
        for (FileEnum fileEnum : fileList) {
            if (FileEnum.domain.equals(fileEnum)) {
                domainService.writeToLocalFile3(tableName, className, zip);
            } else if (FileEnum.mapper.equals(fileEnum)) {
                mapperService.writeToLocalFile3(tableName, className, zip);
            } else if (FileEnum.serviceImpl.equals(fileEnum)) {
                // 判断是否有父类接口层
                if (config.isHasService()) {
                    serviceImplService.writeToLocalFile100(tableName, className, zip);
                    serviceService.writeToLocalFile200(tableName, className, zip);
                } else {
                    // 执行其它逻辑
                    serviceImplService.writeToLocalFile300(tableName, className, zip);
                }
            } else if (FileEnum.controller.equals(fileEnum)) {
                controllerService.writeToLocalFile3(tableName, className, zip);
            } else if (FileEnum.mapStruct.equals(fileEnum)) {
                mapStructService.writeToLocalFile3(tableName, className, zip);
            } else if (FileEnum.xml.equals(fileEnum)) {
                xmlService.writeToLocalFile3(tableName, className, zip);
            }
        }
    }

    public void multiTableGen(String tableName) {
        // CodeProperties config = SpringUtils.getBean(CodeProperties.class);
        String className = CodeUtils.getClassName(tableName);
        Set<FileEnum> fileList = config.getFiles();
        // 如果不配置 默认是所有的
        if (fileList.isEmpty()) {
            fileList.addAll(FileEnum.all());
        }
        for (FileEnum fileEnum : fileList) {
            if (FileEnum.domain.equals(fileEnum)) {
                domainService.writeToLocalFile(tableName, className);
            } else if (FileEnum.mapper.equals(fileEnum)) {
                mapperService.writeToLocalFile(tableName, className);
            } else if (FileEnum.serviceImpl.equals(fileEnum)) {
                // 判断是否有父类接口层
                if (config.isHasService()) {
                    serviceImplService.writeToLocalFile(tableName, className);
                    serviceService.writeToLocalFile(tableName, className);
                } else {
                    // 执行其它逻辑
                    serviceImplService.writeToLocalFile2(tableName, className);
                }
            } else if (FileEnum.controller.equals(fileEnum)) {
                controllerService.writeToLocalFile(tableName, className);
            } else if (FileEnum.mapStruct.equals(fileEnum)) {
                mapStructService.writeToLocalFile(tableName, className);
            } else if (FileEnum.xml.equals(fileEnum)) {
                xmlService.writeToLocalFile(tableName, className);
            }
        }
    }

    @Override
    public List<CodeFile> multiTableGen2(String tableName) {
        String className = CodeUtils.getClassName(tableName);
        Set<FileEnum> fileList = config.getFiles();
        // 如果不配置 默认是所有的
        if (fileList.isEmpty()) {
            fileList.addAll(FileEnum.all());
        }
        List<FileEnum> fileEnumList = fileList.stream().sorted(Comparator.comparingInt(FileEnum::getSort)).collect(Collectors.toList());
        logger.debug(Arrays.toString(fileEnumList.toArray()));
        List<CodeFile> rs = new ArrayList<>();
        for (FileEnum fileEnum : fileEnumList) {
            if (FileEnum.domain.equals(fileEnum)) {
                CodeFile codeFile = domainService.writeToLocalFile2(tableName, className);
                codeFile.setFileEnum(FileEnum.domain.getValue());
                rs.add(codeFile);
            } else if (FileEnum.mapper.equals(fileEnum)) {
                CodeFile codeFile = mapperService.writeToLocalFile2(tableName, className);
                codeFile.setFileEnum(FileEnum.mapper.getValue());
                rs.add(codeFile);
            } else if (FileEnum.serviceImpl.equals(fileEnum)) {
                // 判断是否有父类接口层
                if (config.isHasService()) {
                    CodeFile codeFile1 = serviceImplService.writeToLocalFile22(tableName, className);
                    codeFile1.setFileEnum(FileEnum.serviceImpl.getValue());
                    rs.add(codeFile1);
                    CodeFile codeFile2 = serviceService.writeToLocalFile2(tableName, className);
                    codeFile2.setFileEnum(FileEnum.service.getValue());
                    rs.add(codeFile2);
                } else {
                    // 执行其它逻辑
                    CodeFile codeFile = serviceImplService.writeToLocalFile33(tableName, className);
                    codeFile.setFileEnum(FileEnum.serviceImpl.getValue());
                    rs.add(codeFile);
                }
            } else if (FileEnum.controller.equals(fileEnum)) {
                CodeFile codeFile = controllerService.writeToLocalFile2(tableName, className);
                codeFile.setFileEnum(FileEnum.controller.getValue());
                rs.add(codeFile);
            } else if (FileEnum.mapStruct.equals(fileEnum)) {
                CodeFile codeFile = mapStructService.writeToLocalFile2(tableName, className);
                codeFile.setFileEnum(FileEnum.mapStruct.getValue());
                rs.add(codeFile);
            } else if (FileEnum.xml.equals(fileEnum)) {
                CodeFile codeFile = xmlService.writeToLocalFile2(tableName, className);
                codeFile.setFileEnum(FileEnum.xml.getValue());
                rs.add(codeFile);
            }
        }
        addConfigCode(rs);
        return rs;
    }


    /**
     * 添加动态配置
     *
     * @param list
     */
    public void addConfigCode(List<CodeFile> list) {
        CodeProperties codeProperties = new CodeProperties(config);
        String toYaml = ConfigUtils.toYaml(codeProperties);
        if (toYaml != null) {
            String[] splits = toYaml.split("\n");
            StringBuilder sb = new StringBuilder("ucode:").append('\n');
            sb.append(" code:").append('\n');
            for (String s : splits) {
                if (!"".equals(s.trim())) {
                    sb.append("  ").append(s).append('\n');
                }
            }
            list.add(new CodeFile("application.yml", sb.toString()));
        }
    }

    /**
     * 循环生成本地代码
     *
     * @param tableNames 表名数组
     */
    @Override
    public void multiTableGen(String[] tableNames) {
        for (String tableName : tableNames) {
            multiTableGen(tableName);
        }
    }

    @Override
    public byte[] multiTableDownload(String[] tableNames) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            multiTableDownload(tableName, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
