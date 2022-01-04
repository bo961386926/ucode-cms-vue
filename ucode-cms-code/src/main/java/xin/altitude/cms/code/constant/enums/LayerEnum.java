package xin.altitude.cms.code.constant.enums;

import xin.altitude.cms.code.entity.bo.XmlConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author explore
 * @since 2021/07/08 19:32
 **/
public enum LayerEnum {
    /**
     * 实体类
     */
    DOMAIN("domain"),
    /**
     * 实体类VO
     */
    DOMAINVO("entity.vo"),
    /**
     * 实体类BO
     */
    DOMAINBO("entity.bo"),
    /**
     * 控制器
     */
    CONTROLLER("controller"),
    /**
     * 服务接口层
     */
    ISERVICE("iservice"),
    /**
     * 服务实现层
     */
    SERVICEIMPL("serviceimpl"),
    /**
     * MyBatis接口层
     */
    MAPPER("mapper"),
    /**
     * XML文件
     */
    XML("xml");
    private String value;
    
    LayerEnum(String value) {
        this.value = value;
    }
    
    public static List<String> toList(XmlConfig codeProperties) {
        List<String> list = new ArrayList<>();
        list.add(LayerEnum.DOMAIN.getValue());
        list.add(LayerEnum.DOMAINVO.getValue());
        list.add(LayerEnum.DOMAINBO.getValue());
        list.add(LayerEnum.CONTROLLER.getValue());
        list.add(LayerEnum.ISERVICE.getValue());
        list.add(LayerEnum.SERVICEIMPL.getValue());
        list.add(LayerEnum.MAPPER.getValue());
        if (codeProperties.getAddXml()) {
            list.add(LayerEnum.XML.getValue());
        }
        return list;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
}
