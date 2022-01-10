package xin.altitude.cms.code.entity.vo;

import xin.altitude.cms.code.domain.MetaTable;

/**
 * 由业务层完成数据初始化
 *
 * @author explore
 * @since 2019/06/19 16:49
 **/
public class MetaTableVo extends MetaTable {
    /**
     * 是否展示系统表
     */
    private boolean showSysTable = false;
    
    public MetaTableVo(MetaTable metaTable) {
        super(metaTable);
    }
    
    public MetaTableVo() {
    }
    
    public boolean isShowSysTable() {
        return showSysTable;
    }
    
    public void setShowSysTable(boolean showSysTable) {
        this.showSysTable = showSysTable;
    }
}
