package xin.altitude.cms.framework.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Entity基类
 *
 * @author ucode
 */
public class BaseProEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    /**
     * 搜索值
     */
    @TableField(exist = false)
    private String searchValue;
    
    /**
     * 请求参数
     */
    @TableField(exist = false)
    private Map<String, Object> params;
    
    public String getSearchValue() {
        return searchValue;
    }
    
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }
    
    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }
    
    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
