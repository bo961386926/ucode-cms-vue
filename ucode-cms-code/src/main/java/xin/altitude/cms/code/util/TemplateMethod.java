/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.util;

import java.util.Optional;

/**
 * beetl模版针对word拓展函数
 *
 * @author explore
 */
public class TemplateMethod {
    /**
     * 首字母大小写切换
     *
     * @param s 输入英文字符
     * @return 转化后的英文字符
     */
    public String sc(String s) {
        return Optional.ofNullable(s).map(e -> {
            char[] chars = e.toCharArray();
            chars[0] ^= 32;
            return String.valueOf(chars);
        }).orElse(null);
        
    }
    
    /**
     * 根据表名创建类名
     *
     * @param tableName 原始表名（与数据库一致）
     * @return 类名（首字母大写）
     */
    public String getClassName(String tableName) {
        return CodeUtils.getClassName(tableName);
    }
    
    /**
     * 将下划线连接符转化为首字母小写驼峰
     *
     * @param columnName 列名
     * @return 字段名（首字母小写）
     */
    public String getFieldName(String columnName) {
        return CodeUtils.getFieldName(columnName);
    }
    
}