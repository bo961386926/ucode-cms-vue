package xin.altitude.cms.code.config.property;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import xin.altitude.cms.code.constant.enums.LayerEnum;
import xin.altitude.cms.code.entity.bo.ControllerConfig;
import xin.altitude.cms.code.entity.bo.DomainConfig;
import xin.altitude.cms.code.entity.bo.MapperConfig;
import xin.altitude.cms.code.entity.bo.XmlConfig;
import xin.altitude.cms.code.constant.enums.CodeModeEnum;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * 动态配置实体类
 *
 * @author explore
 * @since 2021/07/07 13:41
 **/
// @Configuration
@ConfigurationProperties("ucode.configuration")
public class AutoCodeProperties {
    /**
     * 项目的相对路径，如果为空则使用默认值
     */
    private String projectDir;
    /**
     * 代码生成模式
     */
    private CodeModeEnum codeMode = CodeModeEnum.LOCAL;
    /**
     * 是否去除表前缀
     */
    private Boolean removeTablePrefix = false;
    
    /**
     * 表前缀
     */
    private String tablePrefix;
    /**
     * 基础包名
     */
    private String packageName = "xin.altitude.front";
    /**
     * 模块名
     */
    private String moduleName;
    /**
     * 作者
     */
    private String functionAuthor = "explore";
    
    /**
     * 是否使用Lombok
     */
    private Boolean useLombok = true;
    /**
     * 是否使用MybatisPlus
     */
    private Boolean useMybatisPlus = true;
    /**
     * 是否使用Swagger
     */
    private Boolean useSwagger = false;
    /**
     * 实体类配置
     */
    @NestedConfigurationProperty
    private DomainConfig domain = new DomainConfig();
    /**
     * 控制器配置
     */
    @NestedConfigurationProperty
    private ControllerConfig controller = new ControllerConfig();
    /**
     * xml文件
     */
    @NestedConfigurationProperty
    private XmlConfig xml = new XmlConfig();
    /**
     * Mapper文件
     */
    @NestedConfigurationProperty
    private MapperConfig mapper = new MapperConfig();
    /**
     * 代码生成层次配置
     */
    @JsonIgnore
    private List<String> layerTypes = LayerEnum.toList(this.getXml());
    
    public Boolean getRemoveTablePrefix() {
        return removeTablePrefix;
    }
    
    public void setRemoveTablePrefix(Boolean removeTablePrefix) {
        this.removeTablePrefix = removeTablePrefix;
    }
    
    public String getTablePrefix() {
        return tablePrefix;
    }
    
    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }
    
    public String getPackageName() {
        return packageName;
    }
    
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
    
    public String getFunctionAuthor() {
        return functionAuthor;
    }
    
    public void setFunctionAuthor(String functionAuthor) {
        this.functionAuthor = functionAuthor;
    }
    
    public Boolean getUseLombok() {
        return useLombok;
    }
    
    public void setUseLombok(Boolean useLombok) {
        this.useLombok = useLombok;
    }
    
    public Boolean getUseMybatisPlus() {
        return useMybatisPlus;
    }
    
    public void setUseMybatisPlus(Boolean useMybatisPlus) {
        this.useMybatisPlus = useMybatisPlus;
    }
    
    public Boolean getUseSwagger() {
        return useSwagger;
    }
    
    public void setUseSwagger(Boolean useSwagger) {
        this.useSwagger = useSwagger;
    }
    
    public DomainConfig getDomain() {
        return domain;
    }
    
    public void setDomain(DomainConfig domain) {
        this.domain = domain;
    }
    
    public ControllerConfig getController() {
        return controller;
    }
    
    public void setController(ControllerConfig controller) {
        this.controller = controller;
    }
    
    public XmlConfig getXml() {
        return xml;
    }
    
    public void setXml(XmlConfig xml) {
        this.xml = xml;
    }
    
    public MapperConfig getMapper() {
        return mapper;
    }
    
    public void setMapper(MapperConfig mapper) {
        this.mapper = mapper;
    }
    
    public List<String> getLayerTypes() {
        return layerTypes;
    }
    
    public void setLayerTypes(List<String> layerTypes) {
        this.layerTypes = layerTypes;
    }
    
    public CodeModeEnum getCodeMode() {
        return codeMode;
    }
    
    public void setCodeMode(CodeModeEnum codeMode) {
        this.codeMode = codeMode;
    }
    
    public String getProjectDir() {
        return projectDir;
    }
    
    public void setProjectDir(String projectDir) {
        this.projectDir = projectDir;
    }
    
    /**
     * 获取模块名
     *
     * @return 模块名
     */
    public String getModuleName() {
        final int index = packageName.lastIndexOf(".");
        if (index > 0) {
            return nonNull(moduleName) ? moduleName : packageName.substring(index + 1);
        }
        return nonNull(moduleName) ? moduleName : "front";
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
