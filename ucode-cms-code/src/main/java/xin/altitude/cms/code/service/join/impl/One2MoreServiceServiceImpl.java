/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.join.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.commons.io.FilenameUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import xin.altitude.cms.code.config.property.CodeProperties;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;
import xin.altitude.cms.code.entity.vo.MetaColumnVo;
import xin.altitude.cms.code.service.code.impl.CommonServiceImpl;
import xin.altitude.cms.code.util.CodeUtils;
import xin.altitude.cms.code.util.VelocityInitializer;
import xin.altitude.cms.code.util.format.JavaFormat4Domain;
import xin.altitude.cms.common.util.SpringUtils;

import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 处理domain部分代码生成的业务逻辑
 *
 * @author explore
 * @since 2019/07/07 14:11
 **/
public class One2MoreServiceServiceImpl extends CommonServiceImpl {
    private final static String TEMPLATE = "vm10/java/one2more/service.java.vm";


    public void writeToLocalFile(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        String className = keyColumnUsageVo.getReferencedClassName();
        String fileName = String.format("I%sService.java", className);
        VelocityContext context = createContext(tableName, keyColumnUsageVo);
        String value = realtimePreview(context);
        String parentDirPath = CodeUtils.createRelativJavaDirFilePath("service");
        String filePath = FilenameUtils.concat(parentDirPath, fileName);
        CodeUtils.genDirAndFile(value, parentDirPath, filePath, true);
    }

    /**
     * 代码实时预览
     */

    public String realtimePreview(String tableName) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        VelocityContext context = createContext(tableName);
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }


    public String realtimePreview(VelocityContext context) {
        StringWriter sw = new StringWriter();
        VelocityInitializer.initVelocity();
        Template tpl = Velocity.getTemplate(TEMPLATE, Charset.defaultCharset().displayName());
        tpl.merge(context, sw);
        return JavaFormat4Domain.formJava(sw.toString());
    }


    /**
     * 构建VelocityContext
     */

    public VelocityContext createContext(String tableName) {
        VelocityContext context = createContext();
        MetaColumnVo pkColumn = getPkColumn(tableName);
        context.put("pkFieldType", pkColumn.getFieldType());
        context.put("pkFieldName", pkColumn.getFieldName());
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
     * 交换属性
     *
     * @param keyColumnUsageVo
     * @return
     */
    public KeyColumnUsageVo exchangeKeyColumnUsageVo(KeyColumnUsageVo keyColumnUsageVo) {
        KeyColumnUsageVo vo = new KeyColumnUsageVo();
        String className = keyColumnUsageVo.getClassName();
        String fieldName = keyColumnUsageVo.getFieldName();
        String fieldType = keyColumnUsageVo.getFieldType();
        vo.setClassName(keyColumnUsageVo.getReferencedClassName());
        vo.setFieldName(keyColumnUsageVo.getReferencedFieldName());
        vo.setFieldType(keyColumnUsageVo.getReferencedFieldType());
        vo.setReferencedClassName(className);
        vo.setReferencedFieldName(fieldName);
        vo.setReferencedFieldType(fieldType);
        return vo;
    }


    public VelocityContext createContext(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        VelocityContext context = createContext(keyColumnUsageVo.getReferencedTableName());
        /* 交换属性后传值 */
        context.put("keyColumn", exchangeKeyColumnUsageVo(keyColumnUsageVo));
        context.put("joinQuery", SpringUtils.getBean(CodeProperties.class).getJoinQuery());
        // 添加导包列表
        context.put("importList", getImportList(tableName, keyColumnUsageVo));
        return context;
    }

    /**
     * 获取导包列表
     */

    public List<String> getImportList(String tableName) {
        ArrayList<String> rs = new ArrayList<>();
        if (config.getUseMybatisPlus()) {
            rs.add(String.format("import %s;", IService.class.getName()));
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), CodeUtils.getClassName(tableName)));
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }


    public List<String> getImportList(String tableName, KeyColumnUsageVo keyColumnUsageVo) {
        List<String> rs = getImportList(tableName);
        Boolean joinQuery = SpringUtils.getBean(CodeProperties.class).getJoinQuery();
        if (joinQuery) {
            rs.add("import com.baomidou.mybatisplus.core.metadata.IPage;");
            rs.add("import com.baomidou.mybatisplus.core.toolkit.Wrappers;");
            rs.add("import xin.altitude.cms.common.util.BeanCopyUtils;");
            rs.add("import xin.altitude.cms.common.util.EntityUtils;");
            rs.add("import xin.altitude.cms.common.util.SpringUtils;");
            rs.add(String.format("import %s.domain.%s;", config.getPackageName(), keyColumnUsageVo.getReferencedClassName()));
            rs.add(String.format("import %s.entity.vo.%sVo;", config.getPackageName(), keyColumnUsageVo.getReferencedClassName()));
            rs.add("import java.util.List;");
            rs.add("import java.util.Map;");
            rs.add("import java.util.Set;");
            rs.add(String.format("import %s;", Collectors.class.getName()));
        }
        rs.sort(Comparator.naturalOrder());
        return rs;
    }
}

