package xin.altitude.cms.code.service.core;

import xin.altitude.cms.code.domain.KeyColumnUsage;

import java.util.List;

/**
 * @author explore
 * @since 2021/12/18 19:38
 **/
public interface IKeyColumnUsage {
    /**
     * 查询当前数据库所有列信息
     *
     * @return 列元数据集合
     */
    List<KeyColumnUsage> listKeyColumns();
    
    /**
     * 判断当前表是否存在主键
     *
     * @param tableName 表名
     * @return 有且仅有一列标记为主键返回true
     */
    boolean existPk(String tableName);
    
    boolean isPk(String tableName, String columnName);
    
    /**
     * 查询主键键索引信息
     *
     * @param tableName 当前表名
     * @return 主键集合
     */
    List<KeyColumnUsage> listPrimaryKeyColumns(String tableName);
    
    /**
     * 查询外键索引信息
     *
     * @param tableName 当前表名
     * @return 外键集合
     */
    List<KeyColumnUsage> listKeyColumns(String tableName);
}
