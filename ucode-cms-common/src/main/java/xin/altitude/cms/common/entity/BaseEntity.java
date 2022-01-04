package xin.altitude.cms.common.entity;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Entity基类
 *
 * @author ucode
 */
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建时间
     */
    private LocalTime createTime;
    /**
     * 更新时间
     */
    private LocalTime updateTime;
    /**
     * 逻辑删除
     */
    private Boolean deleted;
    
    public BaseEntity(BaseEntity baseEntity) {
        this.createTime = baseEntity.createTime;
        this.updateTime = baseEntity.updateTime;
        this.deleted = baseEntity.deleted;
    }
    
    public LocalTime getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(LocalTime createTime) {
        this.createTime = createTime;
    }
    
    public LocalTime getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(LocalTime updateTime) {
        this.updateTime = updateTime;
    }
    
    public Boolean getDeleted() {
        return deleted;
    }
    
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
