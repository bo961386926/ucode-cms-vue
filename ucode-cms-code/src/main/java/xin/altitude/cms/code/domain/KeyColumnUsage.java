package xin.altitude.cms.code.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @author explore
 * @since 2021/12/18 19:28
 **/
@TableName(schema = "information_schema", value = "KEY_COLUMN_USAGE")
public class KeyColumnUsage extends Model<KeyColumnUsage> {
    /**
     * 约束名称
     */
    private String constraintName;
    /**
     * 数据库名称
     */
    private String tableSchema;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 字段名
     */
    private String columnName;
    /**
     * 引用数据库名称
     */
    private String referencedTableSchema;
    /**
     * 引用表名称
     */
    private String referencedTableName;
    /**
     * 引用列名称
     */
    private String referencedColumnName;
    
    public KeyColumnUsage() {
    }
    
    public KeyColumnUsage(KeyColumnUsage keyColumnUsage) {
        if (keyColumnUsage != null) {
            this.constraintName = keyColumnUsage.constraintName;
            this.tableSchema = keyColumnUsage.tableSchema;
            this.tableName = keyColumnUsage.tableName;
            this.columnName = keyColumnUsage.columnName;
            this.referencedTableSchema = keyColumnUsage.referencedTableSchema;
            this.referencedTableName = keyColumnUsage.referencedTableName;
            this.referencedColumnName = keyColumnUsage.referencedColumnName;
        }
    }
    
    public String getConstraintName() {
        return constraintName;
    }
    
    public void setConstraintName(String constraintName) {
        this.constraintName = constraintName;
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
    
    public String getColumnName() {
        return columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    public String getReferencedTableSchema() {
        return referencedTableSchema;
    }
    
    public void setReferencedTableSchema(String referencedTableSchema) {
        this.referencedTableSchema = referencedTableSchema;
    }
    
    public String getReferencedTableName() {
        return referencedTableName;
    }
    
    public void setReferencedTableName(String referencedTableName) {
        this.referencedTableName = referencedTableName;
    }
    
    public String getReferencedColumnName() {
        return referencedColumnName;
    }
    
    public void setReferencedColumnName(String referencedColumnName) {
        this.referencedColumnName = referencedColumnName;
    }
    
}
