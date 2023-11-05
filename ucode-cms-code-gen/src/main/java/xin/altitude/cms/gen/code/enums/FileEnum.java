package xin.altitude.cms.gen.code.enums;

import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.gen.code.entity.FileModel;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 代码生成文件枚举
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public enum FileEnum {
    /**
     * DO实体类
     */
    domain("domain", "DO实体类", 1),
    /**
     * DAO访问层
     */
    mapper("mapper", "DAO访问层", 2),

    /**
     * 服务层
     */
    serviceImpl("serviceImpl", "服务层", 3),
    /**
     * 服务接口层
     */
    service("service", "服务接口层", 4),
    /**
     * 控制器层
     */
    controller("controller", "控制器层", 5),
    /**
     * XML文件
     */
    xml("xml", "XML文件", 6),
    /**
     * 实体类转换层
     */
    mapStruct("mapStruct", "实体类转换层", 7);

    private final String value;
    private final String desc;
    private final Integer sort;

    // FileEnum(String value, String desc) {
    //     this.value = value;
    //     this.desc = desc;
    // }

    FileEnum(String value, String desc, int sort) {
        this.value = value;
        this.desc = desc;
        this.sort = sort;
    }

    public static Set<FileEnum> all() {
        List<FileEnum> enumList = Arrays.stream((FileEnum.values())).sorted(Comparator.comparing(o -> o.sort)).collect(Collectors.toList());
        return new LinkedHashSet<>(enumList);
    }

    /**
     * 构造hint提示文件
     */
    public static List<FileModel> hint() {
        return EntityUtils.toList(FileEnum.values(), e -> new FileModel(e.getValue(), String.format("%s.", e.desc)));
    }


    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public int getSort() {
        return sort;
    }


}
