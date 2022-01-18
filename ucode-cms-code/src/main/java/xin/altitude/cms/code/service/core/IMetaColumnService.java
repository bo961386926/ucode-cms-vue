/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.core;

import xin.altitude.cms.code.domain.MetaColumn;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;

import java.util.List;

/**
 * 字段信息Service接口
 *
 * @author explore
 */
public interface IMetaColumnService {
    
    List<MetaColumn> listColumns();
    
    /**
     * 查询表的列信息集合
     *
     * @param tableName 表名
     * @return 列信息集合
     */
    List<MetaColumn> listColumns(String tableName);
    
    /**
     * 根据表名、列名查询详细数据
     *
     * @param tableName  表名
     * @param columnName 列名
     * @return 列详细数据
     */
    MetaColumn getOneColumn(String tableName, String columnName);
    
    List<MetaColumnVo> getColumnVos(String tableName);
}
