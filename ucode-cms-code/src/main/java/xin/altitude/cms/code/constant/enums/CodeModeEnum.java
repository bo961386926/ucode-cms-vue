/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.constant.enums;

/**
 * @author explore
 * @since 2019/12/03 11:50
 **/
public enum CodeModeEnum {
    /**
     * 文本标签
     */
    LOCAL(0, "本地模式"),
    /**
     * 在线模式（zip下载）
     */
    WEB(1, "在线模式");
    
    
    private final Integer code;
    private final String desc;
    
    CodeModeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public String getDesc() {
        return desc;
    }
}
