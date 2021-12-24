package xin.altitude.cms.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import xin.altitude.cms.common.annotation.Excel;
import xin.altitude.cms.common.annotation.Excel.ColumnType;
import xin.altitude.cms.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 系统访问记录表 sys_logininfor
 *
 * @author ucode
 */
@TableName("sys_logininfor")
public class SysLogininfor extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * ID
     */
    @Excel(name = "序号", cellType = ColumnType.NUMERIC)
    @TableId(type = IdType.AUTO)
    private Long infoId;
    
    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    private String userName;
    
    /**
     * 登录状态 0成功 1失败
     */
    @Excel(name = "登录状态", readConverterExp = "0=成功,1=失败")
    private String status;
    
    /**
     * 登录IP地址
     */
    @Excel(name = "登录地址")
    private String ipaddr;
    
    /**
     * 登录地点
     */
    @Excel(name = "登录地点")
    private String loginLocation;
    
    /**
     * 浏览器类型
     */
    @Excel(name = "浏览器")
    private String browser;
    
    /**
     * 操作系统
     */
    @Excel(name = "操作系统")
    private String os;
    
    /**
     * 提示消息
     */
    @Excel(name = "提示消息")
    private String msg;
    
    /**
     * 访问时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date loginTime;
    /**
     * 创建者
     */
    @TableField(exist = false)
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date createTime;
    /**
     * 更新者
     */
    @TableField(exist = false)
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private Date updateTime;
    /**
     * 备注
     */
    @TableField(exist = false)
    private String remark;
    
    public Long getInfoId() {
        return infoId;
    }
    
    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getIpaddr() {
        return ipaddr;
    }
    
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }
    
    public String getLoginLocation() {
        return loginLocation;
    }
    
    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }
    
    public String getBrowser() {
        return browser;
    }
    
    public void setBrowser(String browser) {
        this.browser = browser;
    }
    
    public String getOs() {
        return os;
    }
    
    public void setOs(String os) {
        this.os = os;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Date getLoginTime() {
        return loginTime;
    }
    
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    
    public String getCreateBy() {
        return createBy;
    }
    
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateBy() {
        return updateBy;
    }
    
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
    
    public Date getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
