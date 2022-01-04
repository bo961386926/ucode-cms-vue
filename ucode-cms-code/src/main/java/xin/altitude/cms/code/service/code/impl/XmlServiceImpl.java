package xin.altitude.cms.code.service.code.impl;

import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.service.code.IXmlService;
import xin.altitude.cms.code.util.AutoCodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;

import java.io.StringWriter;
import java.nio.charset.Charset;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2021/07/07 14:11
 **/
// @Service
public class XmlServiceImpl extends CommonServiceImpl implements IXmlService {
    private final static String TEMPLATE = "vm10/xml/mapper.xml.vm";
    
    @Override
    public void writeToLocalFile(String tableName, String className) {
        String fileName = String.format("%sMapper.xml", className);
        String value = realtimePreview(tableName);
        String parentDirPath = AutoCodeUtils.createRelativXmlDirFilePath(config.getModuleName());
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        AutoCodeUtils.genDirAndFile(value, parentDirPath, filePath);
    }
    
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
        return sw.toString();
    }
    
    /**
     * 构建VelocityContext
     */
    @Override
    public VelocityContext createContext(String tableName) {
        VelocityContext context = new VelocityContext();
        context.put("packageName", config.getPackageName());
        context.put("ClassName", AutoCodeUtils.getClassName(tableName));
        return context;
    }
}


