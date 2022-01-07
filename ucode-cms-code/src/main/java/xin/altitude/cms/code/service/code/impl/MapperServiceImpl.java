package xin.altitude.cms.code.service.code.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.service.code.IMapperService;
import xin.altitude.cms.code.config.property.CodeProperties;
import xin.altitude.cms.code.util.CodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;
import xin.altitude.cms.code.util.format.JavaFormat4Domain;
import xin.altitude.cms.code.util.CodeSpringUtils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2019/07/07 14:11
 **/
// @Service
public class MapperServiceImpl extends CommonServiceImpl implements IMapperService {
    private final static String TEMPLATE = "vm10/java/mapper.java.vm";
    
    @Override
    public void writeToLocalFile(String tableName, String className) {
        String fileName = String.format("%sMapper.java", className);
        String value = realtimePreview(tableName);
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath("mapper");
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }
    
    /**
     * 代码实时预览
     */
    @Override
    public String realtimePreview(String tableName) {
        CodeProperties config = CodeSpringUtils.getBean(CodeProperties.class);
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
        // return sw.toString();
    }
    
    /**
     * 构建VelocityContext
     */
    @Override
    public VelocityContext createContext(String tableName) {
        VelocityContext context = createContext();
        context.put("tableName", tableName);
        context.put("ClassName", CodeUtils.getClassName(tableName));
        context.put("className", CodeUtils.getInstanceName(tableName));
        // 添加导包列表
        context.put("importList", getImportList(tableName));
        // 添加表备注
        // context.put("tableComment", getTableInfo(tableName, configEntity.getDbConnId()).getTableComment());
        return context;
    }
    
    /**
     * 获取导包列表
     */
    @Override
    public List<String> getImportList(String tableName) {
        ArrayList<String> rs = new ArrayList<>();
        // 如果配置需要导包，方才进行真正的导包列表构建
        rs.add("import org.apache.ibatis.annotations.Mapper;");
        if (config.getUseMybatisPlus()) {
            rs.add("import com.baomidou.mybatisplus.core.mapper.BaseMapper;");
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), CodeUtils.getClassName(tableName)));
        }
        if (config.getMapper().getUseCache()) {
            rs.add("import org.apache.ibatis.annotations.CacheNamespace;");
            rs.add("import org.apache.ibatis.cache.decorators.ScheduledCache;");
        }
        rs.add("import org.springframework.transaction.annotation.Transactional;");
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}


