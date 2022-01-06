package xin.ucode.front.domain;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
/** 参数配置表 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_config")
public class SysConfig implements Serializable{
    private static final long serialVersionUID = 1L;
    /** 参数主键 */
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "参数主键", position = 1)
    private Integer configId;
    /** 参数名称 */
    @ApiModelProperty(value = "参数名称", position = 2)
    private String configName;
    /** 参数键名 */
    @ApiModelProperty(value = "参数键名", position = 3)
    private String configKey;
    /** 参数键值 */
    @ApiModelProperty(value = "参数键值", position = 4)
    private String configValue;
    /** 系统内置（Y是 N否） */
    @ApiModelProperty(value = "系统内置（Y是 N否）", position = 5)
    private String configType;
    /** 创建者 */
    @ApiModelProperty(value = "创建者", position = 6)
    private String createBy;
    /** 创建时间 */
    @ApiModelProperty(value = "创建时间", position = 7)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /** 更新者 */
    @ApiModelProperty(value = "更新者", position = 8)
    private String updateBy;
    /** 更新时间 */
    @ApiModelProperty(value = "更新时间", position = 9)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    /** 备注 */
    @ApiModelProperty(value = "备注", position = 10)
    private String remark;
    
    public SysConfig(SysConfig sysConfig) {
        if (Objects.nonNull(sysConfig)) {
            this.configId=sysConfig.configId;
            this.configName=sysConfig.configName;
            this.configKey=sysConfig.configKey;
            this.configValue=sysConfig.configValue;
            this.configType=sysConfig.configType;
            this.createBy=sysConfig.createBy;
            this.createTime=sysConfig.createTime;
            this.updateBy=sysConfig.updateBy;
            this.updateTime=sysConfig.updateTime;
            this.remark=sysConfig.remark;
        }
    }
}
