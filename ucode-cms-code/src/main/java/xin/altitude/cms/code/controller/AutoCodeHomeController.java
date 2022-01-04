package xin.altitude.cms.code.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.code.constant.RunEnv;
import xin.altitude.cms.code.service.core.ILocalAutoCodeHomeService;
import xin.altitude.cms.code.domain.MetaTable;
import xin.altitude.cms.code.entity.vo.MetaTableBo;
import xin.altitude.cms.code.service.core.IMetaTableService;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.entity.PageEntity;
import xin.altitude.cms.common.util.EntityUtils;

import java.util.List;


/**
 * 本地生成代码API访问入口
 *
 * @author explore
 * @since 2021/07/07 15:27
 **/
@ResponseBody
@Profile(value = RunEnv.ENV)
@RequestMapping("auto/code")
public class AutoCodeHomeController {
    @Autowired
    private ILocalAutoCodeHomeService entranceService;
    @Autowired
    private IMetaTableService metaTableService;
    
    @GetMapping("/table/gen")
    public AjaxResult multiTableGen(String[] tableName) {
        MetaTable metaTable = new MetaTable();
        List<MetaTableBo> tableList = metaTableService.selectTableList(metaTable);
        if (tableName == null) {
            tableName = EntityUtils.toList(tableList, MetaTableBo::getTableName).toArray(new String[0]);
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
}
