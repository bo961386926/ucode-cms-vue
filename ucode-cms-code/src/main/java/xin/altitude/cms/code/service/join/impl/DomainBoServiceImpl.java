package xin.altitude.cms.code.service.join.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.constant.enums.LayerEnum;
import xin.altitude.cms.code.service.join.IDomainBoService;
import xin.altitude.cms.code.domain.KeyColumnUsage;
import xin.altitude.cms.code.service.code.impl.CommonServiceImpl;
import xin.altitude.cms.code.util.AutoCodeUtils;
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

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2021/07/07 14:11
 **/
public class DomainBoServiceImpl extends CommonServiceImpl implements IDomainBoService {
    private final static String TEMPLATE = "vm10/java/more2more/domainBo.java.vm";
    
    /**
     * 写到本地
     *
     * @param keyColumnUsage
     * @param midClassName
     */
    @Override
    public void writeToLocalFile(KeyColumnUsage keyColumnUsage, String midClassName) {
        String className = AutoCodeUtils.getClassName(keyColumnUsage.getReferencedTableName());
        String fileName = String.format("%sBo.java", className);
        VelocityContext context = createContext(midClassName, keyColumnUsage);
        String value = realtimePreview(className, keyColumnUsage, context);
        String parentDirPath = AutoCodeUtils.createRelativJavaDirFilePath(FilenameUtils.concat(LayerEnum.DOMAINBO.getValue(), midClassName));
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        AutoCodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }
    
    /**
     * 代码实时预览
     */
    @Override
    public String realtimePreview(String tableName, KeyColumnUsage keyColumnUsage, VelocityContext context) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }
    
    
    /**
     * 构建VelocityContext
     */
    @Override
    public VelocityContext createContext(String midClassName, KeyColumnUsage keyColumnUsage) {
        VelocityContext context = createContext();
        context.put("MidClassName", midClassName);
        
        context.put("ClassName", AutoCodeUtils.getClassName(keyColumnUsage.getReferencedTableName()));
        context.put("className", AutoCodeUtils.getInstanceName(keyColumnUsage.getReferencedTableName()));
        
        context.put("columns", getMetaColumnVoList(keyColumnUsage.getTableName(), keyColumnUsage.getReferencedTableName()));
        // 添加导包列表
        context.put("importList", getImportList(keyColumnUsage.getReferencedTableName()));
        // 添加表备注
        context.put("tableComment", getTableInfo(keyColumnUsage.getReferencedTableName()));
        return context;
    }
    
    /**
     * 获取导包列表
     *
     * @param tableName 表名
     */
    @Override
    public List<String> getImportList(String tableName) {
        ArrayList<String> rs = new ArrayList<>();
        // 如果配置需要导包，方才进行真正的导包列表构建
        if (config.getDomain().getAddImportList()) {
            rs.add(String.format("import %s;", LocalDateTime.class.getName()));
            rs.add(String.format("import %s;", LocalDate.class.getName()));
            rs.add(String.format("import %s;", Date.class.getName()));
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), AutoCodeUtils.getClassName(tableName)));
            if (config.getDomain().getDateFormat()) {
                rs.add("import com.fasterxml.jackson.annotation.JsonFormat;");
            }
            if (config.getDomain().getDateSerialize()) {
                rs.add("import com.fasterxml.jackson.databind.annotation.JsonDeserialize;");
                rs.add("import com.fasterxml.jackson.databind.annotation.JsonSerialize;");
                rs.add("import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;");
                rs.add("import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;");
            }
            if (config.getUseLombok()) {
                rs.add("import lombok.AllArgsConstructor;");
                rs.add("import lombok.Builder;");
                rs.add("import lombok.Data;");
                rs.add("import lombok.NoArgsConstructor;");
                rs.add("import lombok.experimental.Accessors;");
            }
            if (config.getUseMybatisPlus() && config.getMapper().getUseCache()) {
                rs.add("import java.io.Serializable;");
            }
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}


