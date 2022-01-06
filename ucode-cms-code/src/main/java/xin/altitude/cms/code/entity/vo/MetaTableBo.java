package xin.altitude.cms.code.entity.vo;

/**
 * @author explore
 * @since 2019/07/14 14:36
 **/
public class MetaTableBo {
    /**
     * 连接名称
     */
    private String tableName;
    /**
     * 是否选中(默认未选中)
     */
    private boolean selectOn;
    
    public MetaTableBo(String tableName) {
        this.tableName = tableName;
    }
    
    public MetaTableBo() {
    }
    
    public MetaTableBo(String tableName, boolean selectOn) {
        this.tableName = tableName;
        this.selectOn = selectOn;
    }
    
    public String getTableName() {
        return tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    
    public boolean isSelectOn() {
        return selectOn;
    }
    
    public void setSelectOn(boolean selectOn) {
        this.selectOn = selectOn;
    }
}
