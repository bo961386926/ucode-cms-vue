/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.entity.vo;


import xin.altitude.cms.code.domain.KeyColumnUsage;

/**
 * @author explore
 * @since 2019/12/19 14:40
 **/
public class KeyColumnUsageVo extends KeyColumnUsage {
    /**
     * 当前表类名
     */
    private String className;
    /**
     * 字段类型
     */
    private String fieldType;
    
    /**
     * 字段名称
     */
    private String fieldName;
    
    /**
     * 引用表类名
     */
    private String referencedClassName;
    
    /**
     * 引用列类型
     */
    private String referencedFieldType;
    /**
     * 引用字段名称
     */
    private String referencedFieldName;
    
    public KeyColumnUsageVo() {
    }
    
    public KeyColumnUsageVo(KeyColumnUsage keyColumnUsage) {
        super(keyColumnUsage);
    }
    
    public String getFieldType() {
        return fieldType;
    }
    
    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }
    
    public String getReferencedFieldType() {
        return referencedFieldType;
    }
    
    public void setReferencedFieldType(String referencedFieldType) {
        this.referencedFieldType = referencedFieldType;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    // public String getInstanceName() {
    //     return instanceName;
    // }
    //
    // public void setInstanceName(String instanceName) {
    //     this.instanceName = instanceName;
    // }
    
    public String getReferencedClassName() {
        return referencedClassName;
    }
    
    public void setReferencedClassName(String referencedClassName) {
        this.referencedClassName = referencedClassName;
    }
    
    // public String getReferencedInstanceName() {
    //     return referencedInstanceName;
    // }
    //
    // public void setReferencedInstanceName(String referencedInstanceName) {
    //     this.referencedInstanceName = referencedInstanceName;
    // }
    
    public String getFieldName() {
        return fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getReferencedFieldName() {
        return referencedFieldName;
    }
    
    public void setReferencedFieldName(String referencedFieldName) {
        this.referencedFieldName = referencedFieldName;
    }
    
    // public String getUpperFieldName() {
    //     return upperFieldName;
    // }
    //
    // public void setUpperFieldName(String upperFieldName) {
    //     this.upperFieldName = upperFieldName;
    // }
    //
    // public String getReferencedUpperFieldName() {
    //     return referencedUpperFieldName;
    // }
    //
    // public void setReferencedUpperFieldName(String referencedUpperFieldName) {
    //     this.referencedUpperFieldName = referencedUpperFieldName;
    // }
}
