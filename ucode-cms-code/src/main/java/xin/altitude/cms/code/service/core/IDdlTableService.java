package xin.altitude.cms.code.service.core;

import org.apache.ibatis.annotations.Options;

import java.util.List;

/**
 * @author explore
 * @since 2019/11/23 16:07
 **/
public interface IDdlTableService {
    void handleAllColumn(List<String> tableNames);
    
    void handleCreateTime(String tableName);
    
    void handleUpdateTime(String tableName);
    
    void handleLogicalDelete(String tableName);
    
    /**
     * 检测表是不是视图
     * 视图不需要编辑表
     *
     * @param tableNames 表名
     * @return 返回过滤后的实体表名
     */
    abstract List<String> viewTable(List<String> tableNames);
}
