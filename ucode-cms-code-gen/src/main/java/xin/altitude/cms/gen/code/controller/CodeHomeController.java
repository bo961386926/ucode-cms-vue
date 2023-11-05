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

package xin.altitude.cms.gen.code.controller;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.ServletUtils;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.gen.code.config.MyBatisPlusConfig;
import xin.altitude.cms.gen.code.config.property.CodeProperties;
import xin.altitude.cms.gen.code.entity.bo.ConfigBo;
import xin.altitude.cms.gen.code.entity.vo.MetaTableBo;
import xin.altitude.cms.gen.code.enums.FileEnum;
import xin.altitude.cms.gen.code.mysql.domain.MetaTable;
import xin.altitude.cms.gen.code.mysql.service.MetaColumnService;
import xin.altitude.cms.gen.code.mysql.service.MetaTableService;
import xin.altitude.cms.gen.code.service.code.CommonService;
import xin.altitude.cms.gen.code.service.code.DomainService;
import xin.altitude.cms.gen.code.service.code.MapStructService;
import xin.altitude.cms.gen.code.service.code.impl.ControllerServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.MapperServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.ServiceImplServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.ServiceServiceImpl;
import xin.altitude.cms.gen.code.service.code.impl.XmlServiceImpl;
import xin.altitude.cms.gen.code.service.core.ICodeHomeService;
import xin.altitude.cms.gen.code.service.core.impl.CodeHomeServiceImpl;
import xin.altitude.cms.gen.code.service.core.impl.ThirdSqlSessionServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 本地生成代码API访问入口
 * 本控制器仅在开发环境时启用，非开发环境不注入Spring容器中，并且其依赖的所有容器也不注入！！！
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2019/07/07 15:27
 **/
@ResponseBody
@ConditionalOnProperty(prefix = "ucode.code", name = "enabled", havingValue = "true", matchIfMissing = true)
@RequestMapping(Constants.UNIFORM_PREFIX + "/auto/code")
@Import({CodeHomeServiceImpl.class, MetaTableService.class,
    DomainService.class, ControllerServiceImpl.class,
    MapperServiceImpl.class, ServiceServiceImpl.class,
    ServiceImplServiceImpl.class, XmlServiceImpl.class,
    CodeProperties.class, MyBatisPlusConfig.class, MetaColumnService.class,
    ThirdSqlSessionServiceImpl.class, MapStructService.class})
public class CodeHomeController {
    @Autowired
    private ICodeHomeService entranceService;
    @Autowired
    private MetaTableService metaTableService;

    @GetMapping("/table/gen")
    public AjaxResult multiTableGen(String[] tableName) {
        MetaTable metaTable = new MetaTable();
        List<MetaTableBo> tableList = metaTableService.selectTableList(metaTable);
        if (tableName == null) {
            // tableName = EntityUtils.toList(tableList, MetaTableBo::getTableName).toArray(new String[0]);
            return AjaxResult.success("请选择表名");
        }
        entranceService.multiTableGen(tableName);
        return AjaxResult.success();
    }

    @SneakyThrows
    @GetMapping("/table/download")
    public void multiTableDownload(String[] tableName) {
        byte[] data = entranceService.multiTableDownload(tableName);
        // FileOutputStream fileOutputStream = new FileOutputStream("/tmp/123.zip");
        // StreamUtils.copy(data, fileOutputStream);
        genCode(data);
    }

    @SneakyThrows
    private void genCode(byte[] data) {
        HttpServletResponse response = ServletUtils.getResponse();
        // response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        StreamUtils.copy(data, response.getOutputStream());
    }

    /**
     * 查询信息列表
     *
     * @param pageEntity 分页
     * @param metaTable  表对象
     * @return AjaxResult
     */
    @GetMapping("/table/page")
    public AjaxResult page(PageEntity pageEntity, MetaTable metaTable) {
        return AjaxResult.success(metaTableService.pageMetaTable(pageEntity.toPage(), metaTable));
    }

    /**
     * 查询信息列表
     *
     * @param metaTable 表名
     * @return AjaxResult
     */
    @GetMapping("/table/list")
    public AjaxResult list(MetaTable metaTable) {
        return AjaxResult.success(metaTableService.listTables(metaTable));
    }


    @GetMapping("/files")
    public Object files() {
        return FileEnum.hint();
    }

    @GetMapping("/default/config")
    public Object defaultConfig() {
        return SpringUtils.getBean(CodeProperties.class);
    }

    /**
     * 预览代码（默认表｜自定义表）
     */
    @PostMapping("/preview/{tableName}")
    public AjaxResult preview(@PathVariable("tableName") String tableName, @RequestBody ConfigBo configBo) {
        CommonService.setConfig(configBo);
        return AjaxResult.success(entranceService.multiTableGen2(tableName));

    }
}
