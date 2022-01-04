package xin.altitude.cms.code.entity.bo;

/**
 * @author explore
 * @since 2021/07/08 19:24
 **/
// @Data
public class MapperConfig {
    /**
     * 是否需要使用二级缓存
     */
    private Boolean useCache = true;
    
    public Boolean getUseCache() {
        return useCache;
    }
    
    public void setUseCache(Boolean useCache) {
        this.useCache = useCache;
    }
}
