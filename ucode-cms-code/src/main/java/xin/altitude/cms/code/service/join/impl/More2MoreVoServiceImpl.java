package xin.altitude.cms.code.service.join.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.constant.enums.LayerEnum;
import xin.altitude.cms.code.service.code.impl.CommonServiceImpl;
import xin.altitude.cms.code.util.AutoCodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;
import xin.altitude.cms.code.util.format.JavaFormat4Domain;
import xin.altitude.cms.common.util.ColUtils;

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
public class More2MoreVoServiceImpl extends CommonServiceImpl {
    private final static String TEMPLATE = "vm10/java/more2more/domainVo.java.vm";
    
    public String realtimePreview(VelocityContext context, String... tableNames) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        // VelocityContext context = createContext(tableNames);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }
    
    public void writeToLocalFile(List<String> tableNames, String midClassName) {
        String fileName = String.format("%sVo.java", AutoCodeUtils.getClassName(ColUtils.toObj(tableNames)));
        List<String> importList = getImportList(tableNames, midClassName);
        VelocityContext context = createContext(tableNames, importList, midClassName);
        String value = realtimePreview(context, tableNames.toArray(new String[0]));
        // 文件路径增加关联表关联
        String parentDirPath = AutoCodeUtils.createRelativJavaDirFilePath(FilenameUtils.concat(LayerEnum.DOMAINVO.getValue(), midClassName));
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        AutoCodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }
    
    
    public VelocityContext createContext(List<String> tableNames, List<String> importList, String midClassName) {
        VelocityContext context = createContext();
        context.put("MidClassName", midClassName);
        context.put("leftClassName", AutoCodeUtils.getClassName(tableNames.get(0)));
        context.put("rightClassName", AutoCodeUtils.getClassName(tableNames.get(1)));
        
        // 添加导包列表
        context.put("importList", importList);
        // 添加表备注
        // context.put("tableComment", getTableInfo(tableName).getTableComment());
        return context;
    }
    
    public List<String> getImportList(List<String> tableNames, String midClassName) {
        ArrayList<String> rs = new ArrayList<>();
        // 如果配置需要导包，方才进行真正的导包列表构建
        if (config.getDomain().getAddImportList()) {
            rs.add(String.format("import %s;", List.class.getName()));
            rs.add("import lombok.AllArgsConstructor;");
            rs.add("import lombok.Data;");
            rs.add(String.format("import %s;", LocalDateTime.class.getName()));
            rs.add(String.format("import %s;", LocalDate.class.getName()));
            rs.add(String.format("import %s;", Date.class.getName()));
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), AutoCodeUtils.getClassName(tableNames.get(0))));
            rs.add(String.format("import %s.entity.bo.%s.%sBo;", config.getPackageName(), midClassName, AutoCodeUtils.getClassName(tableNames.get(1))));
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}


