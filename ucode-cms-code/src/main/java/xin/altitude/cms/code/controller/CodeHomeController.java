/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.code.config.MyBatisPlusConfig;
import xin.altitude.cms.code.config.property.CodeProperties;
import xin.altitude.cms.code.constant.RunEnv;
import xin.altitude.cms.code.domain.MetaTable;
import xin.altitude.cms.code.entity.vo.MetaTableBo;
import xin.altitude.cms.code.service.code.impl.CodeHomeServiceImpl;
import xin.altitude.cms.code.service.code.impl.ControllerServiceImpl;
import xin.altitude.cms.code.service.code.impl.DomainServiceImpl;
import xin.altitude.cms.code.service.code.impl.MapperServiceImpl;
import xin.altitude.cms.code.service.code.impl.ServiceImplServiceImpl;
import xin.altitude.cms.code.service.code.impl.ServiceServiceImpl;
import xin.altitude.cms.code.service.code.impl.XmlServiceImpl;
import xin.altitude.cms.code.service.core.ICodeHomeService;
import xin.altitude.cms.code.service.core.IDdlTableService;
import xin.altitude.cms.code.service.core.IMetaTableService;
import xin.altitude.cms.code.service.core.impl.DdlTableServiceImpl;
import xin.altitude.cms.code.service.core.impl.KeyColumnUsageImpl;
import xin.altitude.cms.code.service.core.impl.MetaColumnServiceImpl;
import xin.altitude.cms.code.service.core.impl.MetaTableServiceImpl;
import xin.altitude.cms.code.service.core.impl.ThirdSqlSessionServiceImpl;
import xin.altitude.cms.code.service.join.impl.DomainBoServiceImpl;
import xin.altitude.cms.code.service.join.impl.More2MoreServiceServiceImpl;
import xin.altitude.cms.code.service.join.impl.More2MoreVoServiceImpl;
import xin.altitude.cms.code.service.join.impl.One2MoreDomainVoServiceImpl;
import xin.altitude.cms.code.service.join.impl.One2OneServiceServiceImpl;
import xin.altitude.cms.code.service.join.impl.One2OneVoServiceImpl;
import xin.altitude.cms.code.util.CodeSpringUtils;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.framework.config.CmsConfig;

import java.util.ArrayList;
import java.util.List;


/**
 * 本地生成代码API访问入口
 * 本控制器仅在开发环境时启用，非开发环境不注入Spring容器中，并且其依赖的所有容器也不注入！！！
 *
 * @author explore
 * @since 2019/07/07 15:27
 **/
@ResponseBody
@Profile(value = RunEnv.ENV)
@RequestMapping(CmsConfig.UNIFORM_PREFIX + "/auto/code")
@Import({CodeHomeServiceImpl.class, MetaTableServiceImpl.class,
    DomainServiceImpl.class, One2OneVoServiceImpl.class, DomainBoServiceImpl.class, ControllerServiceImpl.class,
    MapperServiceImpl.class, ServiceServiceImpl.class, ServiceImplServiceImpl.class, XmlServiceImpl.class,
    CodeProperties.class, MyBatisPlusConfig.class, KeyColumnUsageImpl.class, CodeSpringUtils.class,
    More2MoreVoServiceImpl.class, MetaColumnServiceImpl.class, More2MoreServiceServiceImpl.class, One2OneServiceServiceImpl.class,
    One2MoreDomainVoServiceImpl.class, ThirdSqlSessionServiceImpl.class, DdlTableServiceImpl.class})
public class CodeHomeController {
    @Autowired
    private ICodeHomeService entranceService;
    @Autowired
    private IMetaTableService metaTableService;
    @Autowired
    private IDdlTableService ddlTableService;

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

    /**
     * 修改列信息
     *
     * @param tableNames 表名
     * @return AjaxResult
     */
    @GetMapping("/modify/column")
    public AjaxResult modifyColumn(@RequestParam List<String> tableNames) {
        if (tableNames == null) {
            ddlTableService.handleAllColumn(new ArrayList<>());
        } else {
            ddlTableService.handleAllColumn(tableNames);
        }

        return AjaxResult.success();
    }
}
