package xin.altitude.cms.code.entity.vo;

import com.google.common.base.CaseFormat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import xin.altitude.cms.code.domain.MetaTable;

import java.util.Optional;

/**
 * 由业务层完成数据初始化
 *
 * @author explore
 * @since 2019/06/19 16:49
 **/
public class MetaTableVo extends MetaTable {
    /**
     * 实体类名
     */
    private String entityClassName;
    /**
     * 实体类实例名
     */
    private String entityInstanceName;
    
    /**
     * Mapper类名
     */
    private String mapperClassName;
    /**
     * Mapper实例名
     */
    private String mapperInstanceName;
    
    /**
     * service类名
     */
    private String serviceClassName;
    /**
     * service实现类名
     */
    private String serviceImplClassName;
    /**
     * service实例名
     */
    private String serviceInstanceName;
    
    /**
     * controller类名
     */
    private String controllerClassName;
    
    /**
     * 基础包名
     */
    private String basePackageName;
    /**
     * 模块名称（包名最后一个单词为模块名）
     */
    private String moduleName;
    /**
     * 控制器路由
     */
    private String businessName;
    
    
    public MetaTableVo(MetaTable metaTable) {
        super(metaTable);
        // 初始化Vo参数
        Optional.of(metaTable).ifPresent(tb -> {
            this.setEntityClassName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tb.getTableName()));
            this.setEntityInstanceName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tb.getTableName()));
            this.setMapperClassName(entityClassName + Mapper.class.getSimpleName());
            this.setMapperInstanceName(entityInstanceName + Mapper.class.getSimpleName());
            this.setServiceClassName(entityClassName + Service.class.getSimpleName());
            this.setServiceImplClassName(entityClassName + Service.class.getSimpleName() + "Impl");
            this.setServiceInstanceName(entityInstanceName + Service.class.getSimpleName());
            this.setControllerClassName(entityClassName + Controller.class.getSimpleName());
        });
    }
    
    public String getEntityClassName() {
        return entityClassName;
    }
    
    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }
    
    public String getEntityInstanceName() {
        return entityInstanceName;
    }
    
    public void setEntityInstanceName(String entityInstanceName) {
        this.entityInstanceName = entityInstanceName;
    }
    
    public String getMapperClassName() {
        return mapperClassName;
    }
    
    public void setMapperClassName(String mapperClassName) {
        this.mapperClassName = mapperClassName;
    }
    
    public String getMapperInstanceName() {
        return mapperInstanceName;
    }
    
    public void setMapperInstanceName(String mapperInstanceName) {
        this.mapperInstanceName = mapperInstanceName;
    }
    
    public String getServiceClassName() {
        return serviceClassName;
    }
    
    public void setServiceClassName(String serviceClassName) {
        this.serviceClassName = serviceClassName;
    }
    
    public String getServiceImplClassName() {
        return serviceImplClassName;
    }
    
    public void setServiceImplClassName(String serviceImplClassName) {
        this.serviceImplClassName = serviceImplClassName;
    }
    
    public String getServiceInstanceName() {
        return serviceInstanceName;
    }
    
    public void setServiceInstanceName(String serviceInstanceName) {
        this.serviceInstanceName = serviceInstanceName;
    }
    
    public String getControllerClassName() {
        return controllerClassName;
    }
    
    public void setControllerClassName(String controllerClassName) {
        this.controllerClassName = controllerClassName;
    }
    
    public String getBasePackageName() {
        return basePackageName;
    }
    
    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }
    
    public String getModuleName() {
        return moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    public String getBusinessName() {
        return businessName;
    }
    
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
}
