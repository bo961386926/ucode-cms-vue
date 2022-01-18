/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import xin.altitude.cms.code.domain.MetaTable;
import xin.altitude.cms.code.entity.vo.MetaTableBo;

import java.util.List;

/**
 * 信息Service接口
 *
 * @author explore
 */
public interface IMetaTableService {
    
    /**
     * 查询当前数据库所有列信息
     *
     * @return 列元数据集合
     */
    List<MetaTable> listTables();
    
    List<MetaTableBo> selectTableList(MetaTable metaTable);
    
    List<MetaTableBo> selectTableList(String tableName);
    
    List<MetaTable> listTables(MetaTable metaTable);
    
    IPage<MetaTable> pageMetaTable(Page<MetaTable> page, MetaTable metaTable);
    
    MetaTable getMetaTable(String tableName);
}
