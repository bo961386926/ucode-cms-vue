/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.code.impl;

import org.springframework.beans.factory.annotation.Autowired;
import xin.altitude.cms.code.config.property.CodeProperties;
import xin.altitude.cms.code.constant.enums.JoinQueryEnum;
import xin.altitude.cms.code.constant.enums.LayerEnum;
import xin.altitude.cms.code.domain.KeyColumnUsage;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;
import xin.altitude.cms.code.service.core.ICodeHomeService;
import xin.altitude.cms.code.service.join.impl.DomainBoServiceImpl;
import xin.altitude.cms.code.service.join.impl.More2MoreServiceServiceImpl;
import xin.altitude.cms.code.service.join.impl.More2MoreVoServiceImpl;
import xin.altitude.cms.code.service.join.impl.One2OneServiceServiceImpl;
import xin.altitude.cms.code.service.join.impl.One2OneVoServiceImpl;
import xin.altitude.cms.code.util.CodeSpringUtils;
import xin.altitude.cms.code.util.CodeUtils;
import xin.altitude.cms.common.util.ColUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 入口代码
 *
 * @author explore
 * @since 2019/07/08 19:28
 **/
// @Service
// @Import({DomainServiceImpl.class, One2OneVoServiceImpl.class, DomainBoServiceImpl.class, ControllerServiceImpl.class,
//         MapperServiceImpl.class, ServiceServiceImpl.class, ServiceImplServiceImpl.class, XmlServiceImpl.class})
public class CodeHomeServiceImpl extends CommonServiceImpl implements ICodeHomeService {
    @Autowired
    private DomainServiceImpl domainService;
    @Autowired
    private One2OneVoServiceImpl one2OneVoService;
    @Autowired
    private DomainBoServiceImpl domainBoService;
    @Autowired
    private ControllerServiceImpl controllerService;
    @Autowired
    private MapperServiceImpl mapperService;
    @Autowired
    private ServiceServiceImpl serviceService;
    @Autowired
    private ServiceImplServiceImpl serviceImplService;
    @Autowired
    private XmlServiceImpl xmlService;
    
    /**
     * 生成单张表的代码
     *
     * @param tableName 表名
     */
    public void multiTableGen(String tableName) {
        CodeProperties config = CodeSpringUtils.getBean(CodeProperties.class);
        String className = CodeUtils.getClassName(tableName);
        List<KeyColumnUsage> keyColumns = listKeyColumns(tableName);
        for (String layerType : config.getLayerTypes()) {
            if (LayerEnum.DOMAIN.getValue().equals(layerType)) {
                domainService.writeToLocalFile(tableName, className);
            } else if (config.getJoinQuery() && LayerEnum.DOMAINVO.getValue().equals(layerType)) {
                if (keyColumns.size() == 1) {
                    one2OneVoService.writeToLocalFile(tableName, className, ColUtils.toObj(keyColumns));
                } else if (keyColumns.size() == 2) {
                    List<String> tableNames = keyColumns.stream().map(KeyColumnUsage::getReferencedTableName).collect(Collectors.toList());
                    CodeSpringUtils.getBean(More2MoreVoServiceImpl.class).writeToLocalFile(tableNames, className);
                    Collections.reverse(tableNames);
                    CodeSpringUtils.getBean(More2MoreVoServiceImpl.class).writeToLocalFile(tableNames, className);
                }
            } else if (config.getJoinQuery() && LayerEnum.DOMAINBO.getValue().equals(layerType)) {
                if (keyColumns.size() == 2) {
                    keyColumns.forEach(e -> domainBoService.writeToLocalFile(e, className));
                }
            } else if (LayerEnum.MAPPER.getValue().equals(layerType)) {
                mapperService.writeToLocalFile(tableName, className);
            } else if (LayerEnum.ISERVICE.getValue().equals(layerType)) {
                if (keyColumns.size() == 0) {
                    CodeSpringUtils.getBean(ServiceServiceImpl.class).writeToLocalFile(tableName, className);
                } else if (keyColumns.size() == 1) {
                    KeyColumnUsageVo keyColumnUsageVo = toKeyColumnUsageVo(ColUtils.toObj(keyColumns));
                    CodeSpringUtils.getBean(One2OneServiceServiceImpl.class).writeToLocalFile(tableName, className, keyColumnUsageVo);
                } else if (keyColumns.size() == 2) {
                    CodeSpringUtils.getBean(More2MoreServiceServiceImpl.class).writeToLocalFile(tableName, className, keyColumns);
                }
            } else if (LayerEnum.SERVICEIMPL.getValue().equals(layerType)) {
                serviceImplService.writeToLocalFile(tableName, className);
            } else if (LayerEnum.CONTROLLER.getValue().equals(layerType)) {
                Integer flagCode = 0;
                if (keyColumns.size() == 1) {
                    flagCode = JoinQueryEnum.ONE_ONE.getValue();
                } else if (keyColumns.size() == 2) {
                    flagCode = JoinQueryEnum.MORE_MORE.getValue();
                }
                controllerService.writeToLocalFile(tableName, className, flagCode, keyColumns);
            } else if (LayerEnum.XML.getValue().equals(layerType)) {
                if (config.getXml().getAddXml()) {
                    xmlService.writeToLocalFile(tableName, className);
                }
            }
        }
    }
    
    /**
     * 循环生成本地代码
     *
     * @param tableNames 表名数组
     */
    @Override
    public void multiTableGen(String[] tableNames) {
        for (String tableName : tableNames) {
            multiTableGen(tableName);
        }
    }
}
