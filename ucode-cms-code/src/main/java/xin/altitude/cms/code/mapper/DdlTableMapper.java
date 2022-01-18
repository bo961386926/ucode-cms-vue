/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author explore
 * @since 2019/11/23 14:55
 **/
@Mapper
public interface DdlTableMapper {
    /**
     * 添加创建时间
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} add column ${columnName} datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';")
    void addCreateTime(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
    /**
     * 修改创建时间
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} modify column ${columnName} datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';")
    void updateCreateTime(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
    /**
     * 添加更新时间
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} add column ${columnName} datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';")
    void addUpdateTime(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
    /**
     * 修改更新时间
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} modify column ${columnName} datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';")
    void updateUpdateTime(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
    /**
     * 添加逻辑删除
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} add column ${columnName} int(11) DEFAULT 0 COMMENT '逻辑删除';")
    void addLogicalDelete(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
    /**
     * 修改逻辑删除
     *
     * @param tableName 表名
     */
    @Update("alter table ${tableName} modify column ${columnName} int(11) DEFAULT 0 COMMENT '逻辑删除';")
    void updateLogicalDelete(@Param("tableName") String tableName, @Param("columnName") String columnName);
    
}
