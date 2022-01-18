/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.entity.vo;

import xin.altitude.cms.code.constant.enums.ColumnDataEnum;
import xin.altitude.cms.code.domain.MetaColumn;

/**
 * @author explore
 * @since 2019/06/19 18:27
 **/
public class MetaColumnVo extends MetaColumn {
    /**
     * 字段类型
     */
    private String fieldType;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 是否是主键
     */
    private Boolean pkColumn;
    /**
     * 列数据类型
     */
    private ColumnDataEnum columnDataType;
    
    public MetaColumnVo(MetaColumn metaColumn) {
        super(metaColumn);
    }
    
    public Boolean getPkColumn() {
        // return "PRI".equals(this.getColumnKey());
        return pkColumn;
    }
    
    public void setPkColumn(Boolean pkColumn) {
        this.pkColumn = pkColumn;
    }
    
    public String getFieldType() {
        return fieldType;
    }
    
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public ColumnDataEnum getColumnDataType() {
        return columnDataType;
    }
    
    public void setColumnDataType(ColumnDataEnum columnDataType) {
        this.columnDataType = columnDataType;
    }
}
