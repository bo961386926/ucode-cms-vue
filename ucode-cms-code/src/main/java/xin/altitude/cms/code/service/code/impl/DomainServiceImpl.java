package xin.altitude.cms.code.service.code.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.constant.enums.LayerEnum;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;
import xin.altitude.cms.code.service.code.IDomainService;
import xin.altitude.cms.code.util.CodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;
import xin.altitude.cms.code.util.format.JavaFormat4Domain;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2019/07/07 14:11
 **/
// @Service
public class DomainServiceImpl extends CommonServiceImpl implements IDomainService {
    private final static String TEMPLATE = "vm10/java/domain.java.vm";
    
    /**
     * 代码实时预览
     */
    @Override
    public String realtimePreview(String tableName) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }
    
    /**
     * 写到本地
     *
     * @param tableName
     * @param className
     */
    @Override
    public void writeToLocalFile(String tableName, String className) {
        String fileName = String.format("%s.java", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath(LayerEnum.DOMAIN.getValue());
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }
    
    
    /**
     * 构建VelocityContext
     */
    @Override
    public VelocityContext createContext(String tableName) {
        // AutoCodeProperties configEntity = CodeSpringUtils.getBean(AutoCodeProperties.class);
        VelocityContext context = new VelocityContext();
        context.put("configEntity", config);
        context.put("packageName", config.getPackageName());
        context.put("tableName", tableName);
        context.put("ClassName", CodeUtils.getClassName(tableName));
        context.put("className", CodeUtils.getInstanceName(tableName));
        List<MetaColumnVo> columnVos = getColumnVos(tableName).stream().filter(e -> CodeUtils.notJavaKey(e.getFieldName())).collect(Collectors.toList());
        context.put("columns", columnVos);
        // 添加导包列表
        context.put("importList", getImportList());
        // 添加表备注
        context.put("tableComment", getTableInfo(tableName).getTableComment());
        return context;
    }
    
    /**
     * 获取导包列表
     */
    @Override
    public List<String> getImportList() {
        ArrayList<String> rs = new ArrayList<>();
        // 如果配置需要导包，方才进行真正的导包列表构建
        if (config.getDomain().getAddImportList()) {
            rs.add(String.format("import %s;", LocalDateTime.class.getName()));
            rs.add(String.format("import %s;", LocalDate.class.getName()));
            rs.add(String.format("import %s;", Date.class.getName()));
            if (config.getDomain().getAddConstructionMethod()) {
                rs.add("import java.util.Objects;");
            }
            if (config.getDomain().getDateFormat()) {
                rs.add("import com.fasterxml.jackson.annotation.JsonFormat;");
            }
            if (config.getDomain().getDateSerialize()) {
                rs.add("import com.fasterxml.jackson.databind.annotation.JsonDeserialize;");
                rs.add("import com.fasterxml.jackson.databind.annotation.JsonSerialize;");
                rs.add("import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;");
                rs.add("import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;");
            }
            if (config.getDomain().getOverrideToString()) {
                rs.add("import org.apache.commons.lang3.builder.ToStringBuilder;");
                rs.add("import org.apache.commons.lang3.builder.ToStringStyle;");
                rs.add("import org.apache.commons.text.StringEscapeUtils;");
            }
            if (config.getUseMybatisPlus()) {
                rs.add("import com.baomidou.mybatisplus.annotation.IdType;");
                rs.add("import com.baomidou.mybatisplus.annotation.TableId;");
                rs.add("import com.baomidou.mybatisplus.annotation.TableName;");
                if (config.getDomain().getUseActiveRecord()) {
                    rs.add("import com.baomidou.mybatisplus.extension.activerecord.Model;");
                }
            }
            if (config.getUseLombok()) {
                rs.add("import lombok.AllArgsConstructor;");
                rs.add("import lombok.Builder;");
                rs.add("import lombok.Data;");
                rs.add("import lombok.NoArgsConstructor;");
                rs.add("import lombok.experimental.Accessors;");
            }
            if (config.getUseSwagger()) {
                rs.add("import io.swagger.annotations.ApiModel;");
                rs.add("import io.swagger.annotations.ApiModelProperty;");
            }
            if (config.getUseMybatisPlus() && config.getMapper().getUseCache()) {
                rs.add("import java.io.Serializable;");
            }
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}


