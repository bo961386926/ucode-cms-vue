package xin.altitude.cms.code.entity.bo;

/**
 * 控制器部分代码配置类
 *
 * @author explore
 * @since 2019/07/08 15:17
 **/
// @Data
public class ControllerConfig {
    /**
     * 业务名(如果不指定业务名，则由表名去除下划线替换而来)
     */
    private String businessName;
    /**
     * 是否需要import列表
     */
    private Boolean addImportList = true;
    /**
     * 是否需要方法注释
     */
    private Boolean addNoteInfo = false;
    
    public String getBusinessName() {
        return businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    
    public Boolean getAddImportList() {
        return addImportList;
    }
    
    public void setAddImportList(Boolean addImportList) {
        this.addImportList = addImportList;
    }
    
    public Boolean getAddNoteInfo() {
        return addNoteInfo;
    }
    
    public void setAddNoteInfo(Boolean addNoteInfo) {
        this.addNoteInfo = addNoteInfo;
    }
}
