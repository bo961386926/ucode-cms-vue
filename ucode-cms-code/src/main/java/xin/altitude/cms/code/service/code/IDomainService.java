/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.code.service.code;

import org.apache.velocity.VelocityContext;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/03 16:56
 **/
public interface IDomainService {
    String realtimePreview(String tableName);
    
    void writeToLocalFile(String tableName, String className);
    
    VelocityContext createContext(String tableName);
    
    List<String> getImportList();
}
