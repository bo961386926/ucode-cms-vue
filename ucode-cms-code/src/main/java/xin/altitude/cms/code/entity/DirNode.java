package xin.altitude.cms.code.entity;

import java.util.List;

/**
 * @author explore
 * @since 2019/07/16 15:56
 **/
public class DirNode {
    /**
     * 文件名
     */
    private String name;
    /**
     * 是否是文件
     */
    private Boolean isFile;
    /**
     * 孩子结点
     */
    private List<DirNode> childs;
    
    public DirNode() {
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getFile() {
        return isFile;
    }
    
    public void setFile(Boolean file) {
        isFile = file;
    }
    
    public List<DirNode> getChilds() {
        return childs;
    }
    
    public void setChilds(List<DirNode> childs) {
        this.childs = childs;
    }
}
