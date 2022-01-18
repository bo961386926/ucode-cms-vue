/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.entity.bo;

/**
 * @author explore
 * @since 2019/07/08 19:24
 **/
// @Data
public class XmlConfig {
    /**
     * 是否需要初始化Xml文件
     */
    private Boolean addXml = false;
    
    public Boolean getAddXml() {
        return addXml;
    }
    
    public void setAddXml(Boolean addXml) {
        this.addXml = addXml;
    }
}
