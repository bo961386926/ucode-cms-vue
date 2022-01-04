package xin.altitude.cms.code.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import xin.altitude.cms.code.entity.DirNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author explore
 * @since 2021/07/16 17:53
 **/
public class DirUtils {
    /**
     * 创建目录文件
     *
     * @param baseFile 基础文件
     * @param path     文件路径
     */
    public static void createFile(File baseFile, String path) {
        File file = new File(baseFile, path);
        try {
            FileUtils.createParentDirectories(file);
            boolean bool = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    /**
     * 将目录转化为树形集合
     *
     * @param root 根目录文件
     * @return 树形集合
     */
    public static DirNode getRootDirNode(File root) {
        String fileName = FilenameUtils.getName(root.getName());
        DirNode dirNode = new DirNode();
        dirNode.setName(fileName);
        dirNode.setFile(root.isFile());
        dirNode.setChilds(getChildDirNode(root));
        return dirNode;
    }
    
    /**
     * 获取孩子结点
     *
     * @param root 相对根目录
     * @return 树形集合
     */
    public static List<DirNode> getChildDirNode(File root) {
        List<DirNode> children = new ArrayList<>();
        File[] childFiles = Optional.ofNullable(root).map(File::listFiles).orElse(null);
        if (root != null && childFiles != null && childFiles.length > 0) {
            File[] files = Arrays.stream(childFiles).sorted(NameFileComparator.NAME_COMPARATOR).toArray(File[]::new);
            for (File file : files) {
                String fileName = FilenameUtils.getName(file.getName());
                DirNode dirNode = new DirNode();
                dirNode.setName(fileName);
                dirNode.setFile(file.isFile());
                dirNode.setChilds(getChildDirNode(file));
                children.add(dirNode);
            }
        }
        
        return children;
    }
}
