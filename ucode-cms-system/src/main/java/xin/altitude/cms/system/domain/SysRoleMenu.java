package xin.altitude.cms.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import xin.altitude.cms.common.entity.BaseEntity;

/**
 * 角色和菜单关联 sys_role_menu
 *
 * @author ucode
 */
@TableName("sys_role_menu")
public class SysRoleMenu extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 角色ID
     */
    private Long roleId;
    
    /**
     * 菜单ID
     */
    private Long menuId;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getRoleId() {
        return roleId;
    }
    
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    
    public Long getMenuId() {
        return menuId;
    }
    
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    
    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("roleId", getRoleId())
                .append("menuId", getMenuId())
                .toString();
    }
}
