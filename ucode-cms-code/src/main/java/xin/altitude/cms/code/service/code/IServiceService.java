package xin.altitude.cms.code.service.code;

import org.apache.velocity.VelocityContext;
import xin.altitude.cms.code.entity.vo.KeyColumnUsageVo;

import java.util.List;

/**
 * @author explore
 * @since 2019/12/03 16:57
 **/
public interface IServiceService {
    void writeToLocalFile(String tableName, String className);
    
    void writeToLocalFile(String tableName, String className, KeyColumnUsageVo keyColumnUsageVo);
    
    String realtimePreview(String tableName);
    
    String realtimePreview(String tableName, KeyColumnUsageVo keyColumnUsageVo);
    
    VelocityContext createContext(String tableName);
    
    VelocityContext createContext(String tableName, KeyColumnUsageVo keyColumnUsageVo);
    
    List<String> getImportList(String tableName);
    
    List<String> getImportList(String tableName, KeyColumnUsageVo keyColumnUsageVo);
}
