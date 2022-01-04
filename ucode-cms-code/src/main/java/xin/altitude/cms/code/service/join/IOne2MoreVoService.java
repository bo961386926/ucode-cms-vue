package xin.altitude.cms.code.service.join;

import org.apache.velocity.VelocityContext;
import xin.altitude.cms.code.domain.KeyColumnUsage;

import java.util.List;

/**
 * @author explore
 * @since 2021/12/03 16:56
 **/
public interface IOne2MoreVoService {
    String realtimePreview(String tableName, KeyColumnUsage keyColumnUsage, VelocityContext context);
    
    void writeToLocalFile(String tableName, String className, KeyColumnUsage keyColumnUsage);
    
    VelocityContext createContext(String tableName, KeyColumnUsage keyColumnUsage);
    
    List<String> getImportList(String tableName);
}
