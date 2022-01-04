package xin.altitude.cms.code.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 数据库系统内置的表实体
 *
 * @author explore
 * @since 2021/06/19 13:02
 **/
@TableName(schema = "information_schema", value = "tables")
public class MetaTable extends Model<MetaTable> {
    /**
     * 所属数据库名
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 表类型
     */
    private String tableType;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /**
     * 表备注
     */
    private String tableComment;
    
    public MetaTable() {
    }
    
    public MetaTable(MetaTable metaTable) {
        // this.tableCatalog = metaTable.tableCatalog;
        this.tableSchema = metaTable.tableSchema;
        this.tableName = metaTable.tableName;
        this.createTime = metaTable.createTime;
        this.updateTime = metaTable.updateTime;
        this.tableComment = metaTable.tableComment;
        this.tableType = metaTable.tableType;
    }
    
    // public String getTableCatalog() {
    //     return tableCatalog;
    // }
    //
    // public void setTableCatalog(String tableCatalog) {
    //     this.tableCatalog = tableCatalog;
    // }
    
    public String getTableType() {
        return tableType;
    }
    
    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
    
    public String getTableSchema() {
        return tableSchema;
    }
    
    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public LocalDateTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getTableComment() {
        return tableComment;
    }
    
    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }
}
