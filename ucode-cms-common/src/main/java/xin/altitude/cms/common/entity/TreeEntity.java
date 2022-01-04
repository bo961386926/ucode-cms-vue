package xin.altitude.cms.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.util.List;

/**
 * 树形统一结构
 *
 * @author explore
 * @since 2022/01/04 15:38
 **/
public abstract class TreeEntity<T> {
    private static final long serialVersionUID = 1L;
    
    @TableField(exist = false)
    private List<? extends TreeEntity<T>> childs;
    
    public List<? extends TreeEntity<T>> getChilds() {
        return childs;
    }
    
    public void setChilds(List<? extends TreeEntity<T>> childs) {
        this.childs = childs;
    }
    
    /**
     * 获取主键ID
     *
     * @return ID
     */
    public abstract T getId();
    
    /**
     * 获取名称
     *
     * @return 名称
     */
    public abstract String getName();
    
    /**
     * 获取父ID
     *
     * @return 父ID
     */
    public abstract T getParentId();
}
