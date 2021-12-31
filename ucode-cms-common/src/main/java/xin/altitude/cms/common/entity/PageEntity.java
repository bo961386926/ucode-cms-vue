package xin.altitude.cms.common.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 简易分页实体
 *
 * @author explore
 */
public class PageEntity {
    private long current = 1;
    private long size = 10;
    
    public PageEntity() {
    }
    
    public PageEntity(long current, long size) {
        this.current = current;
        this.size = size;
    }
    
    public long getCurrent() {
        return current;
    }
    
    public void setCurrent(long current) {
        this.current = current;
    }
    
    public long getSize() {
        return size;
    }
    
    public void setSize(long size) {
        this.size = size;
    }
    
    /**
     * 转换为MybatisPlus分页对象
     *
     * @return 分页实体
     */
    public <T> Page<T> toPage() {
        return new Page<>(current, size);
    }
    
    /**
     * 转换为MybatisPlus分页对象
     *
     * @return 分页实体
     */
    public <T> Page<T> toPage(Class<T> clazz) {
        return new Page<>(current, size);
    }
    
    @Override
    public String toString() {
        return "PageEntity{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}